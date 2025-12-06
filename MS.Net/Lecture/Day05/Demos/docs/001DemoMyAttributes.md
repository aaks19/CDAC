# 001DemoMyAttributes

This document contains the full code and expanded, line-by-line explanations for the custom attribute definitions used in the demos. It also includes usage examples (how to apply the attributes and how to read them with reflection), common pitfalls, and a simple ASCII diagram to clarify relationships.

---

## Projects to be generated
- 001DemoMyAttributes

---

## `MyAttributes.cs` (complete code)
```csharp
namespace _001DemoMyAttributes
{
    public class Table: Attribute // What: Declares Table attribute. Why: Used to mark classes with table metadata. How: Inherits Attribute.
    {
		private string _Name; // What: backing field for Name property. Why: stores value. How: standard field.

		public string Name // What: exposes table name. Why: attribute payload for mapping. How: get/set property.
		{
			get { return _Name; } // What: getter. Why: returns stored name. How: returns field.
			set { _Name = value; } // What: setter. Why: allows assignment in attribute usage. How: sets field.
		}

	}

	public class Column: Attribute // What: Declares Column attribute. Why: used to mark properties. How: inherits Attribute.
	{
		private string _ColumnName; // What: backing field for ColumnName. How: stores column name.
		private string _ColumnType; // What: backing field for ColumnType. How: stores SQL type.

		public string ColumnType // What: exposes SQL type. Why: used by generator. How: get/set.
		{
			get { return _ColumnType; }
			set { _ColumnType = value; }
		}

		public string ColumnName // What: exposes column name. Why: used by generator. How: get/set.
		{
			get { return _ColumnName; }
			set { _ColumnName = value; }
		}

	}
}
```

Expanded line-by-line explanation
- `namespace _001DemoMyAttributes`:
  - Organizes the attribute types under a distinct namespace. Keeps attribute names from colliding with other types.

- `public class Table: Attribute`:
  - Declares a new attribute type named `Table` that inherits from `System.Attribute`.
  - In C#, any class that extends `Attribute` can be used as an attribute in source code, e.g., `[Table(Name = "Employee")]`.
  - Why inherit from `Attribute`: the runtime recognizes instances of these classes as metadata when using reflection.

- `private string _Name;` and the `Name` property:
  - Backing field `_Name` stores the value. The public property `Name` exposes it.
  - This pattern allows the attribute to carry the table name as metadata.
  - Example usage: `[Table(Name = "Employee")]` applied to a class tells other code that this class maps to the `Employee` table.

- `public class Column: Attribute`:
  - Declares a `Column` attribute for property-level metadata.
  - It carries two pieces of information: `ColumnName` and `ColumnType`.

- `private string _ColumnName; private string _ColumnType;` and corresponding properties:
  - Backing fields and public properties provide storage and access.
  - Example usage: `[Column(ColumnName = "Name", ColumnType = "varchar(50)")]` applied to a property tells mapping code what SQL column name and type to use.

Detailed What / Why / How
- What:
  - These classes define metadata that can be attached to classes and properties at compile-time and read at runtime using reflection.

- Why:
  - Useful for simple, attribute-driven ORM-like tooling where you annotate POCOs with the desired database structure.
  - Keeps persistence details out of the core model logic and centralizes mapping concerns in reflection-based code.

- How:
  - Apply attributes in your POCO project (see example below).
  - Use reflection to read `Type.GetCustomAttributes()` and `PropertyInfo.GetCustomAttributes()` and interpret the attribute instances.

Usage example (how to apply attributes)
```csharp
[Table(Name = "Employee")]
public class Emp
{
    [Column(ColumnName = "No", ColumnType = "int")]
    public int No { get; set; }

    [Column(ColumnName = "Name", ColumnType = "varchar(50)")]
    public string Name { get; set; }

    [Column(ColumnName = "Address", ColumnType = "varchar(50)")]
    public string Address { get; set; }
}
```

Reading attributes via reflection (example)
```csharp
using System;
using System.Reflection;
using _001DemoMyAttributes;

Type t = typeof(Emp);
var tableAttrs = t.GetCustomAttributes(typeof(Table), inherit: false);
if (tableAttrs.Length > 0)
{
    var table = (Table)tableAttrs[0];
    Console.WriteLine("Table Name: " + table.Name);
}

foreach (var prop in t.GetProperties())
{
    var colAttrs = prop.GetCustomAttributes(typeof(Column), inherit: false);
    if (colAttrs.Length > 0)
    {
        var col = (Column)colAttrs[0];
        Console.WriteLine($"Property {prop.Name} -> Column: {col.ColumnName} Type: {col.ColumnType}");
    }
}
```

Simple ASCII diagram (conceptual)

Class (Emp)  -------------------->  Attribute [Table(Name)]
Property (Emp.Name)  ----------->  Attribute [Column(ColumnName, ColumnType)]

Notes, best practices and improvements
- Prefer immutable attribute properties where possible. Attributes are typically configured at compile-time; consider exposing a constructor instead of public setters if you want immutability:
  - `public Table(string name) { Name = name; }` and then use `[Table("Employee")]`.
- Consider using `AttributeUsage` to restrict where attributes can be applied and whether they allow multiple instances. E.g.:
  - `[AttributeUsage(AttributeTargets.Class, Inherited = false, AllowMultiple = false)]` on `Table`.
  - `[AttributeUsage(AttributeTargets.Property, Inherited = false, AllowMultiple = false)]` on `Column`.
- Keep attribute payloads small. Attributes are embedded in metadata; avoid large objects or complex types.
- Validate attribute values when reading them (e.g., ensure `ColumnType` is a supported SQL type for your generator).

Common pitfalls
- Missing `AttributeUsage` can allow misuse (e.g., applying `Table` to a property) which will complicate reflection logic.
- Relying on public setters allows accidental runtime mutation; constructors + readonly properties can be safer.

Layman example (reminder)
- Think of attributes as sticky labels you put on boxes (classes) and items (properties) that say how they should be stored: "This box is books" (`Table`) and "This item is title, string(50)" (`Column`). A loader reads those labels to decide how to store them.

Flow of execution
- Compile time: Attributes are applied to types and properties as metadata and embedded into assembly metadata.
- Runtime discovery:
  1. The assembly is loaded (e.g., via `Assembly.LoadFrom` or referenced at compile time).
  2. Reflection code obtains a `Type` (e.g., `typeof(Emp)` or `assembly.GetTypes()`).
  3. The code calls `type.GetCustomAttributes()` to retrieve `Table` instances and `property.GetCustomAttributes()` for `Column` instances.
  4. The reflection consumer (ORM or generator) reads attribute properties (`Name`, `ColumnName`, `ColumnType`) and uses them to produce SQL or mapping configuration.

End of `001DemoMyAttributes` documentation.

---

## Detailed line-by-line expansions (MyAttributes.cs)

- `public class Table: Attribute`:
  - What: Declares an attribute type for classes. Why: Encapsulates table mapping metadata. How: Add `AttributeUsage` to restrict targets and set `Inherited`/`AllowMultiple`.

- `public string Name { get; set; }`:
  - What: Table name payload. Why: Mapper needs the target database table name. How: Consider using constructor parameter to make attribute immutable at compile time.

- `public class Column: Attribute`:
  - What: Attribute to annotate properties with column metadata. Why: Maps to SQL schema. How: Provide optional flags for `IsNullable`, `IsPrimaryKey`.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- `Table` and `Column` attributes are declarative metadata carriers placed on types and properties to describe persistence mappings.

Why (expanded):
- They document mapping decisions at the source and enable decoupled tooling that reads these annotations without changing domain logic.

How (expanded):
- Consumption: prefer `type.GetCustomAttribute<Table>()` and `prop.GetCustomAttribute<Column>()` for clarity.
- Validation: consumers should validate attribute contents, and generators should provide warnings for missing metadata.

Best practices:
- Use `AttributeUsage` and prefer immutable attribute payloads.

---

## If a C# keyword is accidentally removed (what happens)

- Missing keywords cause compiler errors like `CS1002` (semicolon expected), `CS1513` (right curly brace expected), or `CS0116` (a namespace cannot directly contain members such as fields or methods).
- Missing `using` results in `CS0246` or `CS0103` (type/namespace not found). Resolve by adding the using or fully qualify the type.
- Missing `class` or `namespace` typically yields syntax errors and prevents the file from compiling until corrected.
