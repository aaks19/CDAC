# 09DemoReflection

This document provides the full code and expanded explanations for `09DemoReflection` project which demonstrates runtime instantiation and method invocation using reflection. It adds safety suggestions, input conversion safeguards, a flow of execution, and practical usage notes. Example improvement snippets were removed; commentary is provided as what/why/how for comments instead.

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

---

## Detailed line-by-line expansions (Program.cs)

- `var instance = assembly.CreateInstance(type.FullName);`
  - What: create object instance. Why: needed to invoke instance methods. How: check for null and use constructors with parameters via `Activator.CreateInstance` if needed.

- `object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType);`
  - What: convert string to parameter type. Why: enable typed invocation. How: for primitives use `TryParse` to validate and provide helpful error messages; for enums use `Enum.Parse` with validation.

- `method.Invoke(instance, args);`
  - What: call method reflectively. Why: execute API without compile-time dependency. How: guard invocation in try/catch and consider method return types and exceptions when printing results.

Security and policy:
- Never run this tool against untrusted code without sandboxing; invoked methods may modify files, network, or engage in other side effects.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- The docs describe how to safely perform interactive invocations and provide conversion guidance.

Why (expanded):
- Encourages robust input handling and careful consideration of side-effects when invoking methods interactively.

How (expanded):
- Use `TargetInvocationException` handling to surface the root cause of exceptions thrown by invoked methods.
- Provide a `--dry-run` or `--skip-side-effects` option to avoid executing destructive methods when exploring APIs.

---

## If a C# keyword is accidentally removed (what happens)

- Reflection examples rely on correct `using` directives and `MethodInfo`/`ParameterInfo` types; missing these will produce compile errors like `CS0246`.
- If `static` is removed in helper methods expected to be static, calls will fail with `CS0120` or similar errors because an instance reference is required.

End of `09DemoReflection` documentation.
