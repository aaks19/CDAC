# 09DemoReflection

This document provides the full code and expanded explanations for `09DemoReflection` project which demonstrates runtime instantiation and method invocation using reflection. It adds safety suggestions, input conversion safeguards, a flow of execution, and practical usage notes.

---

## Projects to be generated
- 09DemoReflection

---

## `Program.cs` (complete code)
```csharp
using System.Reflection; // What: imports reflection APIs.

namespace _09DemoReflection
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string path = "D:\\IACSD\\IACSDDemos\\MathLib\\bin\\Debug\\net8.0\\MathLib.dll"; // What: hard-coded path in sample. Why: demo convenience. How: replace with user input in real tool.

            Assembly assembly = Assembly.LoadFrom(path); // What: load assembly from path.

            Type []types = assembly.GetTypes(); // What: get types defined in assembly.
           
            foreach (Type type in types)
            {
                
                object dynamicallyCreatedObject =
                    assembly.CreateInstance(type.FullName); // What: instantiate type using parameterless ctor; may return null.

                Console.WriteLine("Created Object of Type " + type.FullName);
                Console.WriteLine("-------------------");


                MethodInfo[] allMethods = type.GetMethods(); // What: get the methods to invoke.

                foreach (MethodInfo method in allMethods)
                {
                    Console.WriteLine("-- calling "  + method.Name + " method");

                    ParameterInfo[] allParams = method.GetParameters(); // What: get parameters for the method.

                    object[]arguments = new object[allParams.Length]; // What: prepare argument array.

                    for (int i = 0; i < allParams.Length; i++)
                    {
                        ParameterInfo parameter = allParams[i];

                        Console.WriteLine("Enter data for " + 
                            parameter.Name + " of type " +
                            parameter.ParameterType.ToString()); // What: prompt for value and type.

                        string valueOfParameter = Console.ReadLine(); // What: read user input.

                        object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType); // What: convert to required type; may throw.

                        arguments[i] = paramaterValue; // What: store in array.

                    }


                    object result = type.InvokeMember(method.Name,
                                      BindingFlags.Public |
                                      BindingFlags.Instance |
                                      BindingFlags.InvokeMethod,
                                      null,
                                      dynamicallyCreatedObject,
                                      arguments); // What: invoke method reflectively.

                    Console.WriteLine( "Method " + method.Name + " Result is = " +  result.ToString()); // What: print result; guard against null in production.
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

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- An interactive reflection-based invoker that creates instances and invokes methods with user-supplied parameters for exploratory testing.

Why (expanded):
- Lets developers quickly test APIs exposed in assemblies without writing a formal test harness; especially useful for prototyping and debugging.

How (expanded):
- Input conversion: prefer using `TryParse` for standard primitive types and validate enum values explicitly. Provide helpful error messages and a re-prompt loop for invalid inputs.
- Safety: do not call untrusted code without sandboxing. Implement method filters (skip methods that take complex types, have side effects, or are flagged unsafe).
- Invocation: use `MethodInfo.Invoke` for clarity and handle `TargetInvocationException` to unwrap inner exceptions thrown by invoked methods.

Examples and guidance:
- If a method takes a non-primitive type, consider constructing a minimal instance for testing or skipping it and reporting why it was skipped.
- Show method return types and handle `void` differently from methods that return values.

---

## Detailed line-by-line expansions (Program.cs)

- `string path = "...MathLib.dll";`
  - What: Example hard-coded path used in demos. Why: Simplifies testing for instructor. How: Replace with interactive input or command-line args for general use.
  - Failure mode: File may not exist on other machines; avoid hard-coded absolute paths in shared repositories.

- `object dynamicallyCreatedObject = assembly.CreateInstance(type.FullName);`
  - What: Creates an instance of the type using a parameterless constructor. Why: The code wants to invoke instance methods. How: Check for `null` return and use `Activator.CreateInstance` overloads to supply constructor parameters if necessary.

- `object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType);`
  - What: Converts the user-supplied string to the method parameter type. Why: Allows interactive invocation with typed parameters. How: Wrap in try/catch and use `TryParse` for known primitives to give better feedback to users.

- `object result = type.InvokeMember(method.Name, BindingFlags.Public | ...);`
  - What: Invoke the named method reflectively. Why: Allows calling methods without compile-time binding. How: Prefer `method.Invoke(instance, arguments)` when you already have a MethodInfo (it's clearer) or use `InvokeMember` with caution for overload resolution differences.

---

## If a C# keyword is accidentally removed (what happens)

- Removing `public`/`private` can change accessibility and cause `CS0122` (is inaccessible due to its protection level) if callers expect a different visibility.
- Removing `new` in object creation is not applicable; but removing `new` in `new GameObject()` doesn't make sense; ensure object instantiation remains valid.
- For reflection code: removing `using System.Reflection;` will yield unresolved type errors for reflection types.

---

UX and safety recommendations:
- Validate `dynamicallyCreatedObject` for null and skip types lacking parameterless constructors.
- Do not `ToString()` on `result` without guarding against null. Use `result?.ToString() ?? "(null)"`.
- Limit invocation to safe subsets of methods (e.g., skip methods with external side effects or `void` that perform I/O) if you are exploring untrusted assemblies.

End of `09DemoReflection` documentation.
