using System.Reflection;
using System.Text;
using _001DemoMyAttributes;

Console.WriteLine("Enter path of assembly to inspect (e.g., ../002DemoPOCOLib/bin/Debug/net8.0/002DemoPOCOLib.dll):");
string? path = Console.ReadLine();

if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))
{
    Console.WriteLine("Invalid path");
    return;
}

Assembly assembly;
try
{
    assembly = Assembly.LoadFrom(path);
}
catch (Exception ex)
{
    Console.WriteLine("Failed to load assembly: " + ex.Message);
    return;
}

var sb = new StringBuilder();

foreach (var type in assembly.GetTypes())
{
    var tableAttr = (Table?)type.GetCustomAttributes(typeof(Table), inherit: false).FirstOrDefault();
    if (tableAttr == null) continue;

    var columns = new List<string>();
    foreach (var prop in type.GetProperties())
    {
        var col = (Column?)prop.GetCustomAttributes(typeof(Column), inherit: false).FirstOrDefault();
        if (col == null) continue;
        columns.Add($"{col.ColumnName} {col.ColumnType}");
    }

    sb.AppendLine($"create table {tableAttr.Name} ({string.Join(", ", columns)});");
}

var output = sb.ToString();
Console.WriteLine("Generated SQL:\n");
Console.WriteLine(output);

// Optionally save
var outPath = "generated.sql";
File.WriteAllText(outPath, output);
Console.WriteLine($"Saved SQL to {outPath}");
