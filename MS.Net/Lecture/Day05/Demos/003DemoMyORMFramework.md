# 003DemoMyORMFramework

This document contains complete code and extended step-by-step explanations for the small ORM demo that reads attribute metadata and generates SQL. It also contains suggested improvements, flow of execution, and a clearer walk-through of the SQL generation algorithm.

---

## Projects to be generated
- 003DemoMyORMFramework

---

## `Program.cs` (complete code)
```csharp
using _001DemoMyAttributes;
using System.Reflection;
using System.Text;

namespace _003DemoMyORMFramework
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter path of assembly:");
            string path = Console.ReadLine();

            if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))
            {
                Console.WriteLine("Invalid assembly path");
                return;
            }

            Assembly assembly = Assembly.LoadFrom(path);

            Type[] allTypes = assembly.GetTypes();

            var sb = new StringBuilder();

            foreach (Type type in allTypes)
            {
                // Step 1: find Table attribute
                var tableAttr = (Table?)type.GetCustomAttributes(typeof(Table), inherit: false).FirstOrDefault();
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
                    var colAttr = (Column?)prop.GetCustomAttributes(typeof(Column), inherit: false).FirstOrDefault();
                    if (colAttr == null)
                        continue; // skip properties not mapped

                    // Use attribute values to build a column definition
                    columnDefs.Add($"{colAttr.ColumnName} {colAttr.ColumnType}");
                }

                sb.Append(string.Join(", ", columnDefs));
                sb.AppendLine(");");
            }

            var query = sb.ToString();
            Console.WriteLine(query);
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

End of `003DemoMyORMFramework` documentation.
