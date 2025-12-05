# 09DemoReflection

This document provides the full code and expanded explanations for `09DemoReflection` project which demonstrates runtime instantiation and method invocation using reflection. It adds safety suggestions, input conversion safeguards, a flow of execution, and practical usage notes.

---

## Projects to be generated
- 09DemoReflection

---

## `Program.cs` (complete code)
```csharp
using System.Reflection;

namespace _09DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string path = "D:\\IACSD\\IACSDDemos\\MathLib\\bin\\Debug\\net8.0\\MathLib.dll";

            Assembly assembly = Assembly.LoadFrom(path);

            Type []types = assembly.GetTypes();
           
            foreach (Type type in types)
            {
                
                object dynamicallyCreatedObject =
                    assembly.CreateInstance(type.FullName);

                Console.WriteLine("Created Object of Type " + type.FullName);
                Console.WriteLine("-------------------");


                MethodInfo[] allMethods = type.GetMethods();

                foreach (MethodInfo method in allMethods)
                {
                    Console.WriteLine("-- calling "  + method.Name + " method");

                    ParameterInfo[] allParams = method.GetParameters();

                    object[]arguments = new object[allParams.Length];

                    for (int i = 0; i < allParams.Length; i++)
                    {
                        ParameterInfo parameter = allParams[i];

                        Console.WriteLine("Enter data for " + 
                            parameter.Name + " of type " +
                            parameter.ParameterType.ToString());

                        string valueOfParameter = Console.ReadLine();

                        object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType);

                        arguments[i] = paramaterValue;

                    }


                    object result = type.InvokeMember(method.Name,
                                      BindingFlags.Public |
                                      BindingFlags.Instance |
                                      BindingFlags.InvokeMethod,
                                      null,
                                      dynamicallyCreatedObject,
                                      arguments);

                    Console.WriteLine( "Method " + method.Name + " Result is = " +  result.ToString());
                    Console.WriteLine("------------------------");
                    Console.WriteLine();
                }
            }
            Console.ReadLine();
        }
    }
}
```

Expanded safety and input handling notes
- Null and conversion safety:
  - `Convert.ChangeType` will throw if the input cannot be converted to the target parameter type. Prefer `TryParse`-style logic when possible and validate user input before conversion.
  - If a parameter type is a value type (e.g., `int`) and user input is empty, conversion will fail. Consider default values or skipping method invocation for invalid input.

- Null results and `ToString()` calls:
  - The code calls `result.ToString()` without checking for `null`. If method returns `null`, this will throw. Use `result?.ToString() ?? "(null)"`.


- Instance creation:
  - `assembly.CreateInstance(type.FullName)` requires a parameterless constructor and will return `null` if none exists. Check for `null` before invoking methods.

Improved invocation pseudo-code
```csharp
var instance = assembly.CreateInstance(type.FullName);
if (instance == null) { Console.WriteLine("Could not create instance"); continue; }

foreach (var method in type.GetMethods())
{
    var parameters = method.GetParameters();
    var args = new object[parameters.Length];
    bool ok = true;
    for (int i = 0; i < parameters.Length; i++)
    {
        var param = parameters[i];
        Console.WriteLine($"Enter value for {param.Name} ({param.ParameterType.Name}):");
        var input = Console.ReadLine();
        try
        {
            args[i] = Convert.ChangeType(input, param.ParameterType);
        }
        catch
        {
            Console.WriteLine("Invalid input, skipping method");
            ok = false;
            break;
        }
    }

    if (!ok) continue;

    var result = method.Invoke(instance, args);
    Console.WriteLine("Result: " + (result?.ToString() ?? "(null)"));
}
```

Flow of execution
- Runtime sequence:
  1. The program loads the target assembly (hard-coded or user-provided path).
  2. It enumerates types from the assembly via `assembly.GetTypes()`.
  3. For each type it attempts to create an instance (requires parameterless constructor).
  4. For each method on the instance it prompts for parameter values, converts them, and invokes the method using reflection.
  5. Method results are printed; errors in conversion or invocation are handled according to implemented safeguards.

When to use this pattern
- Useful for building test tools or dynamic explorers where a developer intends to exercise methods interactively.
- Avoid running unknown or privileged methods in production: reflection can invoke any public method including ones with side effects (file I/O, database changes).

End of `09DemoReflection` documentation.
