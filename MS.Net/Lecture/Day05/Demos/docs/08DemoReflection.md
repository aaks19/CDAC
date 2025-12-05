# 08DemoReflection

This document contains complete code and deeper explanations for the `08DemoReflection` project. It covers safety, common reflection pitfalls, flow of execution, and suggestions for clearer output. Example improvement snippets have been removed; explanations provide what/why/how guidance for comments.

---

## Projects to be generated
- 08DemoReflection

---

## `Program.cs` (complete code)
```csharp
using System.Reflection;
using System.Runtime.Serialization;

namespace _08DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter the assembly path: ");
            string path = Console.ReadLine();

            Console.WriteLine();

            Assembly assembly = Assembly.LoadFrom(path);

            Type[] allTypes = assembly.GetTypes();
            foreach (Type type in allTypes)
            {
                Console.WriteLine("-------------------------------------");
                Console.WriteLine(type.FullName);

                Attribute[] allAttributesOfType =
                    type.GetCustomAttributes().ToArray();

                bool isTypeSerializable = false;
            
                foreach (Attribute attribute in allAttributesOfType)
                {
                    if (attribute is SerializableAttribute)
                    {
                        isTypeSerializable = true;
                        break;
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


                MethodInfo[] methods = type.GetMethods();

                Console.WriteLine();
                foreach (MethodInfo method in methods)
                {
                    Console.Write( " - " + method.Name);

                    ParameterInfo[] allParams = method.GetParameters();

                    Console.Write(" ( ");
                    foreach (ParameterInfo parameter in allParams)
                    {
                        Console.Write(" " + parameter.ParameterType);
                        Console.Write(" " + parameter.Name + " ");
                    }
                    Console.Write(" ) ");
                    Console.WriteLine();
                }
            }

            Console.ReadLine();

        }
    }
}
```

Deeper explanation and safety notes
- Validate input path:
  - What: The path passed to `Assembly.LoadFrom` must be non-null and point to an existing file.
  - Why: Loading an invalid path raises exceptions that should be handled to provide a graceful user experience.
  - How: Check for empty or whitespace paths and verify file existence before calling `Assembly.LoadFrom`; handle exceptions that `LoadFrom` may throw.

- Use readable parameter output:
  - What: `ParameterInfo.ParameterType` yields a `Type`; printing `Name` or `FullName` is clearer for humans than relying on `ToString()`.
  - Why: Improves diagnostic output when listing method parameters.
  - How: When generating string output prefer `parameter.ParameterType.Name` or `FullName` as appropriate.

- Attribute checks:
  - What: The code enumerates `GetCustomAttributes()` and matches against `SerializableAttribute`.
  - Why: Determining serializability via attributes can be done more directly using `Type.IsSerializable` when available.
  - How: Use the most direct API for clarity and reduced iteration when possible.

- Silent reflection risks:
  - What: Accessing certain members or types can cause static constructors or other side-effects to run.
  - Why: Unintended side-effects may alter program behavior or trigger expensive operations.
  - How: Filter types to those of interest or use reflection-safe patterns when you must avoid side-effects.

Comments explained
- The code contains implicit comments about validation and output clarity. These map to the following what/why/how guidance for a consumer:
  - What: Prompt for an assembly path, load the assembly, list types, attributes, and method signatures.
  - Why: Provide a developer-focused exploration tool to inspect assemblies at runtime.
  - How: Ensure input validation and exception handling are present; prefer human-readable type names in output and avoid triggering side-effects when inspecting types.

Flow of execution
- Runtime steps:
  1. Program prompts the user for an assembly path and reads input.
  2. The program loads the assembly via `Assembly.LoadFrom`.
  3. The program enumerates all `Type` objects and prints type and method metadata.
  4. The program ends when the user presses Enter or inspection completes.

When to use reflection carefully
- What: Reflection is appropriate for tooling, test helpers, and plugin systems.
- Why: Reflection provides flexibility but can be slower and risk side-effects.
- How: Avoid reflection in performance-critical loops and verify inputs and permissions before loading external assemblies.

End of `08DemoReflection` documentation.
