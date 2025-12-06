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
		private string _Name; // What: backing field for Name property. Why: stores the value.

		public string Name // What: exposes table name. Why: attribute payload for mapping. How: get/set property.
		{
			get { return _Name; } // What: getter. Why: returns stored name.
			set { _Name = value; } // What: setter. Why: allows assignment in attribute usage.
		}

	}

	public class Column: Attribute // What: Declares Column attribute. Why: used to mark properties. How: inherits Attribute.
	{
		private string _ColumnName; // What: backing field for ColumnName.
		private string _ColumnType; // What: backing field for ColumnType.

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

## Detailed What / Why / How (summary)

What: This document defines two custom attribute types, `Table` and `Column`, which encode mapping metadata that can be attached to classes and properties. The code shows simple backing fields and properties that carry the attribute payload.

Why: Attributes let you attach structured metadata to types and members at compile time. That makes it possible to write generic tooling (ORM generators, serializers, validators) that discovers mapping information via reflection rather than hard-coding behavior. Using attributes centralizes mapping concerns and keeps domain classes free of persistence logic.

How: Apply these attributes to POCOs (e.g., `[Table(Name = "Employee")]`) and read them at runtime using reflection (`Type.GetCustomAttributes`, `PropertyInfo.GetCustomAttributes`). For production use:
- Prefer constructor-based initialization for immutable attribute payloads (e.g., `public Table(string name)`), or mark properties `init` where appropriate.
- Add `[AttributeUsage]` to restrict valid targets (class vs property) and to control `Inherited` and `AllowMultiple` behavior.
- Validate attribute values when consumed and keep attribute payloads small. Avoid storing large objects in attributes since they are embedded in assembly metadata.

---

## Detailed line-by-line expansions (MyAttributes.cs)

- `public class Table: Attribute`:
  - What: Declares an attribute type that can be applied to classes. Why: Encapsulates table mapping metadata such as `Name`. How: You may add `[AttributeUsage(AttributeTargets.Class, Inherited = false, AllowMultiple = false)]` to restrict usage.
  - Failure mode: Without `AttributeUsage` developers might misuse the attribute on properties or methods; adding `AttributeUsage` improves intent.

- `private string _Name; public string Name { get; set; }`:
  - What: Backing field and public property to hold the table name. Why: Exposes attribute payload to reflection consumers. How: Consider using an `init` property or constructor argument for immutability: `public Table(string name) { Name = name; }`.

- `public class Column: Attribute` and its properties:
  - What: Attribute used on properties to carry `ColumnName` and `ColumnType`. Why: Provide explicit mapping metadata for generators. How: Consider validating `ColumnType` against a small set of allowed SQL types when generating SQL to catch errors early.

---

Consumption notes:
- When reading attributes, prefer `type.GetCustomAttribute<Table>()` or `prop.GetCustomAttribute<Column>()` to fetch the attribute instance directly.
- Remember that attributes are metadata embedded in assemblies; their constructors should be simple and not perform expensive work.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- This module defines two small attribute types, `Table` and `Column`, used to attach schema-related metadata to classes and properties. They are simple container types that carry values the runtime or tooling can read via reflection.

Why (expanded):
- Use attributes when you want declarative metadata that travels with the compiled type. They are ideal for cross-cutting concerns (mapping, validation hints, serialization flags) because they are embedded in assembly metadata and are discoverable at runtime by any consumer that references the assembly.
- Attributes make intent explicit in source code (e.g., `class Emp` annotated with `[Table(Name="Employee")]`) which improves maintainability and documents mapping decisions at the point of definition.

How (expanded):
- Authoring: keep attribute constructors lightweight and avoid heavy initialization logic. Prefer exposing simple properties or constructor parameters that describe the metadata. Example: `public Table(string name) { Name = name; }` makes the attribute immutable and concise at usage: `[Table("Employee")]`.
- Consumption: at runtime, use `type.GetCustomAttribute<Table>(false)` to fetch the attribute instance and read its properties. For performance, only request attributes you need rather than enumerating all attributes.
- Validation: tools reading these attributes should validate payloads. For example, `ColumnType` strings should be validated against a whitelist of supported SQL types for the target DB engine. Emit a clear diagnostic if the value is invalid.
- Tooling: to build generators, follow these steps: (1) load assembly, (2) enumerate types, (3) find `Table` attributes, (4) enumerate properties and read `Column` attributes, (5) map attribute payloads to SQL or mapping configuration.

Best practices and pitfalls:
- Use `AttributeUsage` to restrict where an attribute can be applied and whether multiple instances are allowed.
- Prefer `init` or constructor parameters to prevent accidental runtime mutation of attribute payloads.
- Keep the attribute API surface small and serializable-friendly (simple primitive types, strings, enums).

Testing guidance:
- Unit-test your attribute consumers (generators, mappers) by constructing types at compile time in test assemblies and verifying the generated outputs.

---

## If a C# keyword is accidentally removed (what happens)

- What: Removing an essential C# keyword (for example `class`, `namespace`, `using`, `static`, `void`, or `return`) changes the program syntax or meaning.
- Why it matters: The C# compiler requires correct keywords to parse and understand code; missing keywords typically break compilation or change accessibility/behavior.
- Typical outcomes:
  - The compiler reports syntax or semantic errors (e.g., "identifier expected", "invalid token", "type or namespace name could not be found").
  - Missing `using` causes types to be unresolved unless fully qualified.
  - Missing `static` on `Main` in an executable can prevent the runtime from finding a valid entry point.
  - Missing `return` in a non-void method triggers a "not all code paths return a value" error.
- How to recover: Inspect the compiler error message, restore the missing keyword from version control or the original source, and recompile. Use the IDE quick-fixes to locate and fix syntax errors.
