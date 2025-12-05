# 09DemoReflection

This document provides the full code and expanded explanations for `09DemoReflection` project which demonstrates runtime instantiation and method invocation using reflection. It adds safety suggestions, input conversion safeguards, a flow of execution, and practical usage notes. Example improvement snippets were removed; commentary is provided as what/why/how for comments instead.

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
  - What: `Convert.ChangeType` and `ToString()` may throw or produce `NullReferenceException` when inputs or results are invalid or `null`.
  - Why: Interactive conversion depends on user input which may be missing or malformed.
  - How: Validate inputs before conversion, use try/catch to handle conversion errors, and protect `ToString()` calls against `null` results by using null-coalescing patterns.

- Instance creation:
  - What: `assembly.CreateInstance(type.FullName)` requires a parameterless constructor and may return `null` when not available.
  - Why: Attempting to invoke methods on a `null` instance will throw exceptions.
  - How: Check for `null` and only invoke instance methods when an object is successfully created; optionally skip types without parameterless constructors or create instances with appropriate constructors via `Activator.CreateInstance` overloads.

- Result handling:
  - What: Methods may return `null` and `ToString()` should be guarded.
  - Why: Avoid unhandled exceptions and provide clear output when result is `null`.
  - How: Print a placeholder such as `(null)` when the result is `null`, or serialize objects appropriately for display.

Comments explained
- The source demonstrates an interactive reflection-based invoker and includes implicit concerns which are explained as follows:
  - What: The program loads an assembly, creates instances of types, enumerates and invokes methods interactively by asking the user for parameter values.
  - Why: Useful as a developer utility to explore APIs and test methods at runtime.
  - How: Ensure robust input validation, handle missing constructors, guard against `null` results, and consider security implications before invoking arbitrary methods from unknown assemblies.

Flow of execution
- Runtime sequence:
  1. The program loads the target assembly using `Assembly.LoadFrom`.
  2. It enumerates types via `assembly.GetTypes()`.
  3. For each type it attempts to create an instance and then iterates through its public methods.
  4. For each method it prompts for parameter values, converts them to the parameter types, and invokes the method via reflection.
  5. Results are printed and the program waits on user input before exiting.

When to use this pattern
- What: Use for debugging, test tools, or interactive exploration of APIs.
- Why: Provides flexibility to exercise code without compiling new test harnesses.
- How: Do not use to execute untrusted code in production; validate inputs and consider sandboxing or limiting loaded assemblies.

End of `09DemoReflection` documentation.
