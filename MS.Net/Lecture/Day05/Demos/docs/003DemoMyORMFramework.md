# 003DemoMyORMFramework

This document contains complete code and extended step-by-step explanations for the small ORM demo that reads attribute metadata and generates SQL. It also contains suggested improvements, flow of execution, and a clearer walk-through of the SQL generation algorithm.

---

## Projects to be generated
- 003DemoMyORMFramework

---

## `Program.cs` (complete code)
```csharp
using _001DemoMyAttributes; // What: imports Table/Column attribute definitions.
using System.Reflection; // What: imports reflection APIs for assembly/type inspection.
using System.Text; // What: imports StringBuilder.

namespace _003DemoMyORMFramework
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter path of assembly:"); // What: prompt for assembly path. Why: user provides assembly to inspect.
            string path = Console.ReadLine(); // What: reads input. How: returns string or null.

            if (string.IsNullOrWhiteSpace(path) || !File.Exists(path)) // What: validates path. Why: prevents exceptions. How: checks whitespace and file existence.
            {
                Console.WriteLine("Invalid assembly path");
                return; // What: exit early on invalid input.
            }

            Assembly assembly = Assembly.LoadFrom(path); // What: loads assembly. Why: to inspect types.

            Type[] allTypes = assembly.GetTypes(); // What: enumerates types.

            var sb = new StringBuilder(); // What: accumulates SQL output.

            foreach (Type type in allTypes)
            {
                // Step 1: find Table attribute
                var tableAttr = (Table?)type.GetCustomAttributes(typeof(Table), inherit: false).FirstOrDefault(); // What: obtains Table attribute. Why: decide whether to generate SQL.
                if (tableAttr == null)
                {
                    // Skip types that are not annotated with Table
                    continue;
                }

                sb.Append("create table ");
                sb.Append(tableAttr.Name);
                sb.Append(" (");

                // Step 2: iterate properties and collect column definitions
                var props = type.GetProperties();
                var columnDefs = new List<string>();

                foreach (var prop in props)
                {
                    var colAttr = (Column?)prop.GetCustomAttributes(typeof(Column), inherit: false).FirstOrDefault(); // What: get Column attribute. Why: build column definition.
                    if (colAttr == null)
                        continue; // skip properties not mapped

                    // Use attribute values to build a column definition
                    columnDefs.Add($"{colAttr.ColumnName} {colAttr.ColumnType}"); // What: create definition string.
                }

                sb.Append(string.Join(", ", columnDefs));
                sb.AppendLine(");");
            }

            var query = sb.ToString();
            Console.WriteLine(query); // What: print generated SQL.
            Console.ReadLine();
            //LoggerLib.FileLogger.CurrentLogger.Log(query);
        }
    }
}
```

Step-by-step SQL generation explanation
1. Load assembly and enumerate types.
2. For each type, check for a `Table` attribute. If not present, skip the type.
3. Read properties of the type and for each property look for a `Column` attribute. Skip properties without `Column`.
4. Collect column definitions as `ColumnName ColumnType` strings.
5. Join column definitions with commas and finalize the `CREATE TABLE` statement.
6. Accumulate result for all annotated types and print or persist the output.

Flow of execution
- When the generator runs:
  1. User provides the path to an assembly and the program validates the path.
  2. The assembly is loaded into the AppDomain via `Assembly.LoadFrom`.
  3. The program enumerates all types and filters those with the `Table` attribute.
  4. For each filtered type, it inspects properties for `Column` attributes and collects metadata.
  5. SQL statements are composed in a `StringBuilder` and printed or written to a log.

Improvements included in the suggested version
- Validation of assembly path before loading.
- Skip types without `Table` attribute to avoid generating invalid SQL.
- Use `StringBuilder` for performance and clarity.
- Build column definitions in a list and `string.Join` them to avoid trailing commas.

Edge cases and recommendations
- If no properties are annotated for a type, the tool will generate an empty column list: handle by emitting a warning.
- Consider supporting additional `Column` metadata like `IsNullable`, `IsPrimaryKey`, or default values.
- Validate column types to match your target database syntax.

Layman example
- The tool reads the sticky labels on classes and fields and writes a set of `CREATE TABLE` lines describing how to store each class in a database.

## Detailed line-by-line expansions (Program.cs generator)

- `if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))`:
  - What: immediate validation. Why: prevents later exceptions. How: provide helpful error messages and an option to re-prompt.

- `var tableAttr = (Table?)type.GetCustomAttributes(typeof(Table), inherit: false).FirstOrDefault();`:
  - What: checks for Table attribute. Why: filter types for generation. How: prefer `GetCustomAttribute<Table>()` for expressiveness.

- `columnDefs.Add($"{colAttr.ColumnName} {colAttr.ColumnType}");`:
  - What: build SQL column string. Why: produce the CREATE TABLE statement. How: escape identifiers, validate types, and support nullability and key constraints.

---

Diagnostics and error reporting:
- When a type has no column attributes, warn instead of generating an empty CREATE TABLE. Provide line numbers or type names in diagnostic output.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- The docs outline how to safely and deterministically convert attribute metadata into SQL statements.

Why (expanded):
- Provide a clear teaching example for understanding metadata-driven code generation.

How (expanded):
- Recommend building validation steps into the generator and supporting multiple output targets or dialects.

---

## If a C# keyword is accidentally removed (what happens)

- Missing key tokens cause compiler diagnostics; inspect the exact CS error code to identify the missing element. Common ones: `CS1022`, `CS1002`, `CS1513`.
- For attribute classes: ensure `: Attribute` is present, otherwise callers using attribute APIs will not find your metadata.

---

End of `003DemoMyORMFramework` documentation.
