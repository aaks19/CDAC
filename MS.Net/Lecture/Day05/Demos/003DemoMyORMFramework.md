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

## Detailed What / Why / How (summary)

What: A small ORM-like generator that reads `Table` and `Column` attributes from compiled types and composes SQL `CREATE TABLE` statements.

Why: Automates schema generation and keeps mapping logic close to the domain model. Useful for demos, prototyping, and tooling where conventions are insufficient and explicit mapping is desired.

How: The program validates the supplied assembly path, loads it via `Assembly.LoadFrom`, enumerates types, filters those annotated with `Table`, and collects `Column` attribute data from properties to build column definitions. To improve this pattern in production:
- Use robust input validation and error handling around assembly loading and type reflection.
- Handle edge cases like types with no column attributes (warn or skip) and duplicate column names.
- Consider extending attributes with primary key and nullability metadata, and validate SQL type strings before emitting statements.

End of `003DemoMyORMFramework` documentation.

---

## Detailed line-by-line expansions (Program.cs generator)

- `if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))`:
  - What: Validate path input. Why: Prevent loading errors and provide fast feedback to the user. How: Print a clear error message and return or re-prompt.

- `var tableAttr = (Table?)type.GetCustomAttributes(typeof(Table), inherit: false).FirstOrDefault();`:
  - What: Locate the Table attribute on the type. Why: Filter only annotated types to generate SQL for. How: Prefer generic helper: `type.GetCustomAttribute<Table>(false)`.

- `columnDefs.Add($"{colAttr.ColumnName} {colAttr.ColumnType}");`:
  - What: Compose a column definition string used in CREATE TABLE. Why: Build SQL that maps attribute values to schema. How: Escape or validate names and types to avoid SQL injection or invalid SQL syntax.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- A generator that reads `Table` and `Column` attributes to produce SQL `CREATE TABLE` statements. It demonstrates the end-to-end consumption of attribute metadata.

Why (expanded):
- Useful when you want a quick code-first schema generator tied to explicit attribute annotations. It avoids the complexity of full ORMs while providing deterministic outputs.

How (expanded):
- Steps: validate assembly -> enumerate types -> select annotated types -> gather column metadata -> validate column metadata -> produce SQL statements.
- Error reporting: if column metadata is missing or invalid, emit diagnostics rather than silently generating incorrect SQL.
- Extensibility: support additional attribute fields (primary key, auto-increment, nullability) and custom type mapping tables for multiple DB engines.

Security and correctness notes:
- Sanitize identifier names and escape properly for the chosen SQL dialect.
- Validate generated SQL by parsing or executing in a validation-only mode against a test DB instance.

Testing:
- Create sample annotated classes in test assemblies and verify the generator outputs expected SQL strings.

---

## If a C# keyword is accidentally removed (what happens)

- Example failure modes:
  - Removing `return` from a non-void method -> `CS0161` "not all code paths return a value".
  - Removing `static` from `Main` in a console project -> runtime will not find the entry point (compiler may still succeed if an alternative `Main` exists, but runtime entry will fail otherwise).
  - Removing `: Attribute` from an attribute class -> it will no longer be treated as an attribute by the compiler and reflection will not find it with attribute-specific helpers.
- Recovery: fix the source by restoring the keyword and rebuild. If you use source control, revert the offending commit.
