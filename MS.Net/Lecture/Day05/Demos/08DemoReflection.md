# 08DemoReflection

This document contains complete code and deeper explanations for the `08DemoReflection` project. It covers safety, common reflection pitfalls, flow of execution, and suggestions for clearer output.

---

## Projects to be generated
- 08DemoReflection

---

## `Program.cs` (complete code)
```csharp
using System.Reflection; // What: imports reflection APIs. Why: Assembly/Type/MethodInfo used. How: allows direct type use.
using System.Runtime.Serialization; // What: imports SerializableAttribute and related types.

namespace _08DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter the assembly path: "); // What: prompt user for assembly path.
            string path = Console.ReadLine(); // What: read user input; How: may be null.

            Console.WriteLine(); // What: blank line for readability.

            Assembly assembly = Assembly.LoadFrom(path); // What: load assembly from path. Why: inspect metadata. How: throws on invalid path.

            Type[] allTypes = assembly.GetTypes(); // What: get all types defined in assembly.
            foreach (Type type in allTypes)
            {
                Console.WriteLine("-------------------------------------");
                Console.WriteLine(type.FullName); // What: print namespace-qualified type name.

                Attribute[] allAttributesOfType =
                    type.GetCustomAttributes().ToArray(); // What: collect attributes applied to type.

                bool isTypeSerializable = false; // What: flag to indicate serializability.
            
                foreach (Attribute attribute in allAttributesOfType)
                {
                    if (attribute is SerializableAttribute) // What: check attribute type. Why: detect serializable marker.
                    {
                        isTypeSerializable = true; // What: set flag.
                        break; // What: stop iterating attributes.
                    }
                }

                if (isTypeSerializable)
                {
                    Console.WriteLine(type.Name + " is serializable!");
                }
                else
                {
                    Console.WriteLine(type.Name + " is NOT serializable!!");
                }

                Console.WriteLine("-------------------------------------");


                MethodInfo[] methods = type.GetMethods(); // What: get public methods of the type.

                Console.WriteLine();
                foreach (MethodInfo method in methods)
                {
                    Console.Write( " - " + method.Name); // What: write method name without newline.

                    ParameterInfo[] allParams = method.GetParameters(); // What: get parameter info for the method.

                    Console.Write(" ( ");
                    foreach (ParameterInfo parameter in allParams)
                    {
                        Console.Write(" " + parameter.ParameterType); // What: write parameter type; prefer .Name for clarity.
                        Console.Write(" " + parameter.Name + " "); // What: write parameter name.
                    }
                    Console.Write(" ) ");
                    Console.WriteLine();
                }
            }

            Console.ReadLine(); // What: pause to allow user to read output.

        }
    }
}
```

Deeper explanation and safety notes
- Validate input path:
  - `Assembly.LoadFrom` will throw if the path is null, empty, or the file does not exist. Wrap loading in `try/catch` and show a user-friendly message.
  - Example: `if (string.IsNullOrWhiteSpace(path) || !File.Exists(path)) { Console.WriteLine("Invalid path"); return; }`.

- Use more readable parameter output:
  - `parameter.ParameterType` prints a `Type` object; prefer `parameter.ParameterType.Name` or `parameter.ParameterType.FullName` for clarity.

- Attribute checks:
  - Instead of iterating attributes and using `is SerializableAttribute`, consider `type.IsSerializable` which directly reports serializability for the type.

- Silent reflection risks:
  - Some types may trigger static constructor side-effects when accessed. Use `type.IsAbstract` or other filters if you only want certain kinds of types.

Improved snippet showing validation and clearer output
```csharp
if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))
{
    Console.WriteLine("Assembly path is invalid or file not found.");
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

foreach (Type type in assembly.GetTypes())
{
    Console.WriteLine("Type: " + type.FullName);
    Console.WriteLine("Serializable: " + type.IsSerializable);

    foreach (var method in type.GetMethods())
    {
        Console.Write(" - " + method.Name + "(");
        Console.Write(string.Join(", ", method.GetParameters().Select(p => p.ParameterType.Name + " " + p.Name)));
        Console.WriteLine(")");
    }
}
```

Flow of execution
- Runtime steps:
  1. Program prompts user for an assembly path and reads input.
  2. Input is validated; `Assembly.LoadFrom` loads the assembly from disk.
  3. `assembly.GetTypes()` obtains all types in the assembly.
  4. For each `Type`, the program enumerates attributes and methods and prints readable information.
  5. The program ends after user input or when finished enumerating.

When to use reflection carefully
- Use reflection for tools, plugins, serializers, or when you genuinely need runtime discovery.
- Avoid reflection for performance-critical inner loops: it is slower than direct calls.

---

## Detailed What / Why / How (summary)

What: Interactive console utility that inspects an assembly's types, attributes, and public method signatures using reflection.

Why: Helps developers discover the API surface and metadata of compiled assemblies at runtime without source code. Useful for debugging, plugin discovery, and lightweight exploration.

How: The tool prompts for an assembly file path, validates and loads the assembly (recommend adding validation), enumerates types, checks attributes (e.g., `SerializableAttribute`), and lists public methods along with parameter types. For production use:
- Validate user input and wrap `Assembly.LoadFrom` in try/catch to handle `FileNotFoundException`, `BadImageFormatException`, and `ReflectionTypeLoadException`.
- Use `type.IsSerializable` to check serializability more directly.
- Print `parameter.ParameterType.Name` for human-friendly type names.
- Consider loading untrusted assemblies in an isolated context (AssemblyLoadContext in .NET 5+/Core or a separate AppDomain in .NET Framework) to avoid side-effects from static constructors.

End of `08DemoReflection` documentation.

---

## Detailed line-by-line expansions (Program.cs)

Below are more granular expansions for the most important lines in `Program.cs`. These focus on intent, failure modes, and alternatives you should consider when using the patterns in the sample.

- `Console.WriteLine("Enter the assembly path: ");`
  - What: Prompt for input. Why: Signals to the user what to supply. How: Consider accepting this as a command-line argument for scripted scenarios or adding a default path for demos.
  - Failure mode: If run non-interactively (CI), this will block; document behavior or avoid interactive prompts.

- `string path = Console.ReadLine();`
  - What: Reads input. Why: Captures path string used by loader. How: Validate immediately: `if (string.IsNullOrWhiteSpace(path) || !File.Exists(path))` then print error and return.
  - Failure mode: Null or whitespace causes exceptions in `LoadFrom`; use early return to prevent that.

- `Assembly assembly = Assembly.LoadFrom(path);`
  - What: Loads the assembly file into the current context. Why: Reflection requires an Assembly instance. How: Wrap with try/catch for `FileNotFoundException`, `BadImageFormatException`, `SecurityException`, and `ReflectionTypeLoadException`.
  - Alternative: Use `AssemblyLoadContext` (in .NET Core/.NET 5+) when you need unloadability or isolation; in .NET Framework consider separate AppDomain for isolation.

- `Type[] allTypes = assembly.GetTypes();`
  - What: Enumerate all types. Why: To inspect attributes and members. How: This call may throw `ReflectionTypeLoadException` when some types fail to load; catch it and inspect its `Types` and `LoaderExceptions` properties to present partial results and detailed failure causes.

- `Attribute[] allAttributesOfType = type.GetCustomAttributes().ToArray();`
  - What: Materialize attributes. Why: The sample iterates attributes to detect `SerializableAttribute`. How: Prefer `type.IsSerializable` for serializability checks; otherwise use `GetCustomAttribute<T>()` to fetch a single attribute of interest.

- `if (attribute is SerializableAttribute)`
  - What: Runtime type check for attribute instance. Why: Verifies presence of the marker. How: `is` is correct for runtime checks but `GetCustomAttribute<SerializableAttribute>() != null` is clearer and directly expresses intent.

- `MethodInfo[] methods = type.GetMethods();`
  - What: Returns public methods (including inherited). Why: The tool lists method signatures for discovery. How: Use binding flags (e.g., `BindingFlags.DeclaredOnly | BindingFlags.Public | BindingFlags.Instance | BindingFlags.Static`) if you'd like declared-only methods.

- `Console.Write(" " + parameter.ParameterType);`
  - What: Writes the Type value for a parameter. Why: Shows the parameter's type. How: Prefer `parameter.ParameterType.Name` or `FullName` to present more readable output; for generic types consider a helper that formats generics (e.g., `List<int>` rather than `System.Collections.Generic.List`1[System.Int32]`).

- `Console.ReadLine();`
  - What: Blocks until Enter. Why: Keeps the console open. How: Use `Console.ReadKey()` for a single key press; avoid blocking in automated runs.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- An interactive console-based inspector that enumerates types, attributes, and method signatures inside a supplied assembly.

Why (expanded):
- Facilitates quick discovery and manual auditing of compiled assemblies, which is helpful for debugging, documenting APIs, and building lightweight developer tools.

How (expanded):
- Input strategies: accept command-line args for scriptability, interactive prompts for ad-hoc exploration, and optional default test paths for demos.
- Loading: wrap `Assembly.LoadFrom` in try/catch; report clear errors and, where possible, show partial results if some types fail to load (catch `ReflectionTypeLoadException`).
- Presentation: show a compact summary at the top (`Assembly: X, Types: N`) and provide toggles for more or less detail (e.g., `--methods`, `--attributes`).
- Filtering: allow namespace filters and attribute filters to limit output for large assemblies.

Edge cases and advanced topics:
- Untrusted assemblies: use isolation mechanisms (AssemblyLoadContext or AppDomain) to mitigate side-effects from static constructors.
- Generic types and nested types: add formatting helpers to make names human-friendly.
- Performance: avoid calling expensive reflection APIs repeatedly; cache results where it makes sense for iterative exploration.

Examples:
- `dotnet run -- --assembly path/to/lib.dll --namespace MyApp.Services --attributes Serializable` would show only types in `MyApp.Services` that have the `SerializableAttribute`.

---

## If a C# keyword is accidentally removed (what happens)

- Removing `using System.Reflection;` or similar will cause `CS0246` or `CS0103` errors for types like `Assembly`, `Type`, and `MethodInfo` unless the types are fully qualified.
- Removing `namespace` leads to syntax errors and may change type scope; the compiler emits errors like `CS0116` or `CS0120` depending on context.
- Practical tip: run `dotnet build` to show the exact compiler errors and follow the error messages to the offending line.
