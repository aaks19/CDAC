# 08DemoReflection

This document contains complete code and deeper explanations for the `08DemoReflection` project. It covers safety, common reflection pitfalls, flow of execution, and suggestions for clearer output.

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

End of `08DemoReflection` documentation.
