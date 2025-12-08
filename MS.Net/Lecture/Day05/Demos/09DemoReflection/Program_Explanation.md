# Program.cs — Line-by-line explanation for `09DemoReflection\Program.cs`

Below is a concise, line-by-line explanation of `09DemoReflection\Program.cs`. Each entry states what the code does, why it’s there, and how it affects runtime behavior (important caveats included).

1. `using System.Reflection;`
- What: Imports reflection types (`Assembly`, `Type`, `MethodInfo`, `ParameterInfo`, `BindingFlags`, etc.).
- Why: Needed to load assemblies and inspect/invoke types and members at runtime.
- How: Without it the compiler cannot resolve reflection symbols.

2. `namespace _09DemoReflection`
- What: Declares the namespace for the program.
- Why: Organizes types and affects fully-qualified names shown via reflection.
- How: `type.FullName` will include this namespace if types come from here.

3. `internal class Program`
- What: Declares the `Program` class with `internal` visibility.
- Why: Container for the `Main` entry point.
- How: Class scope encloses `Main`.

4. `static void Main(string[] args)`
- What: The console app entry point. `args` receives command-line arguments.
- Why: Execution starts here.
- How: All subsequent reflection logic runs inside this method.

5. `string path = "D:\\IACSD\\IACSDDemos\\MathLib\\bin\\Debug\\net8.0\\MathLib.dll";`
- What: Hard-coded file path to a DLL to inspect.
- Why: Provides a specific assembly to load for the demo.
- How: Hard-coding is brittle (path may not exist on other machines); consider prompting or using a relative path.

6. `Assembly assembly = Assembly.LoadFrom(path);`
- What: Loads the assembly file at `path` into the current process.
- Why: Obtain an `Assembly` object to enumerate types and create instances.
- How: Throws `FileNotFoundException`, `BadImageFormatException`, or security exceptions on failure. The assembly is loaded into the current AppDomain.

7. `Type []types = assembly.GetTypes();`
- What: Gets all `Type` objects defined in the loaded assembly.
- Why: To iterate every type (classes, structs, enums, interfaces) in the assembly.
- How: Can throw `ReflectionTypeLoadException` if some types fail to load; returned array includes types that were successfully loaded.

8. `foreach (Type type in types)`
- What: Loop that processes each discovered type.
- Why: To create instances and inspect/invoke methods for every type.
- How: Iteration is sequential; every `type` variable used in the loop refers to one type.

9. `object dynamicallyCreatedObject = assembly.CreateInstance(type.FullName);`
- What: Attempts to construct an instance of the current `type` by its full name.
- Why: Provides an instance to call instance methods on.
- How: `CreateInstance` uses the assembly’s default activation (public parameterless constructor). If no public parameterless ctor exists it returns `null` (or may throw depending on overload); types without such ctor cannot be instantiated this way.

10. `Console.WriteLine("Created Object of Type " + type.FullName);`
- What: Prints the fully-qualified type name to console.
- Why: Informational: shows which type instance was requested.
- How: If `dynamicallyCreatedObject` is `null`, the message still prints (but subsequent instance method invocations will fail).

11. `Console.WriteLine("-------------------");`
- What: Prints a separator.
- Why: Formatting for readability.
- How: No runtime effect other than visual separation.

12. `MethodInfo[] allMethods = type.GetMethods();`
- What: Retrieves public `MethodInfo` objects for the type (includes inherited public methods).
- Why: To enumerate methods available to call.
- How: Does not include non-public methods unless different binding flags are used.

13. `foreach (MethodInfo method in allMethods)`
- What: Iterates each method discovered.
- Why: To prompt for parameters and invoke each method.
- How: Processes methods sequentially; includes methods like `ToString`, `GetHashCode` if not filtered.

14. `Console.WriteLine("-- calling "  + method.Name + " method");`
- What: Prints which method will be invoked.
- Why: Informational for user interaction.
- How: Helps user know what input corresponds to which method call.

15. `ParameterInfo[] allParams = method.GetParameters();`
- What: Obtains metadata for the method’s parameters.
- Why: To prompt the user for values to pass to the method.
- How: Empty array if the method has no parameters.

16. `object[] arguments = new object[allParams.Length];`
- What: Allocates an object array to hold parameter values for invocation.
- Why: `Type.InvokeMember` expects an `object[]` of arguments.
- How: Sized to match the parameter count.

17. `for (int i = 0; i < allParams.Length; i++) { ... }`
- What: Loop that collects user input for each parameter.
- Why: Build the `arguments` array by converting user input to the parameter types.
- How: Iterates over parameters by index to preserve argument order.

18. `ParameterInfo parameter = allParams[i];`
- What: Local variable referencing the current parameter metadata.
- Why: Makes the code clearer when accessing `parameter` properties.
- How: Used to read the parameter name and type for prompting and conversion.

19. `Console.WriteLine("Enter data for " + parameter.Name + " of type " + parameter.ParameterType.ToString());`
- What: Prompts user to enter a value for this parameter.
- Why: User input is required to supply the method call arguments.
- How: `ParameterType.ToString()` typically yields the type name (may be full name); not user-friendly for complex types.

20. `string valueOfParameter = Console.ReadLine();`
- What: Reads the user-entered string for the parameter.
- Why: Source text to convert into the parameter’s required type.
- How: Returns `null` if input stream closed; user must type a textual representation that can be converted.

21. `object paramaterValue = Convert.ChangeType(valueOfParameter, parameter.ParameterType);`
- What: Attempts to convert the input string to the parameter’s runtime type.
- Why: Reflection invocation requires values of the correct type.
- How & caveats:
  - `Convert.ChangeType` works for types implementing `IConvertible` (primitive types, `DateTime`, etc.).
  - It will throw `InvalidCastException` or `FormatException` for incompatible formats.
  - It does not handle enums, complex types, or custom parsing automatically (enums need `Enum.Parse`, nullable types require unwrapping).
  - Culture-specific parsing (e.g., decimal separators) may affect results.

22. `arguments[i] = paramaterValue;`
- What: Stores converted parameter value in the arguments array.
- Why: Prepares the `arguments` array for invocation.
- How: Maintains parameter order.

23. `object result = type.InvokeMember(method.Name, BindingFlags.Public | BindingFlags.Instance | BindingFlags.InvokeMethod, null, dynamicallyCreatedObject, arguments);`
- What: Invokes the method named `method.Name` on the `dynamicallyCreatedObject` using the provided arguments and binding flags, returning the method result.
- Why: Dynamically execute methods discovered via reflection.
- How & details:
  - `BindingFlags.Public` restricts to public members.
  - `BindingFlags.Instance` targets instance methods (static methods are ignored by this flag combination unless invoked differently; static can be invoked by using `BindingFlags.Static` and passing `null` instance).
  - `BindingFlags.InvokeMethod` tells the binder to invoke a method.
  - The `binder` parameter is `null` (uses default binder).
  - If `dynamicallyCreatedObject` is `null` and the method is instance (not static) the call will throw.
  - `InvokeMember` performs method lookup by name and argument types at runtime and may throw `MissingMethodException`, `TargetInvocationException` (wraps exceptions thrown inside the invoked method), or `ArgumentException` on mismatch.

24. `Console.WriteLine( "Method " + method.Name + " Result is = " +  result.ToString());`
- What: Prints the invocation result by calling `ToString()` on `result`.
- Why: Show returned value to the user.
- How & caveats:
  - If the method returns `void`, `InvokeMember` returns `null` and `result.ToString()` will throw `NullReferenceException`. A safe pattern is to check `result == null` before calling `ToString()`.
  - If the invoked method returns a complex object, `ToString()` may not show useful details unless overridden.

25. `Console.WriteLine("------------------------");` and `Console.WriteLine();`
- What: Visual separators and spacing between invocations.
- Why: Formatting for readability.
- How: No runtime logic impact.

26. Closing braces `}` for loops and method/class
- What: End of `for`, `foreach`, `Main`, `Program`, `namespace`.
- Why: Close code blocks.
- How: Control flow returns to caller or process exits after `Main` finishes.

27. `Console.ReadLine();` (final line)
- What: Blocks until the user presses Enter before exiting the program.
- Why: Keeps console open to read outputs when running outside a debugger.
- How: Prevents immediate console window closure.

## What the full code is doing

In summary, the program performs these high-level steps:

- Loads a compiled .NET assembly from a hard-coded file path using `Assembly.LoadFrom`.
- Retrieves every `Type` declared in that assembly with `GetTypes()`.
- For each `Type`, attempts to create an instance using the assembly's default parameterless constructor (`CreateInstance(type.FullName)`).
- Lists all public methods of the type (`GetMethods()`), and for each method:
  - Prompts the user to enter values for each parameter (shows parameter name and type).
  - Converts the entered string values to the parameter types using `Convert.ChangeType` and builds an `object[]` of arguments.
  - Invokes the method on the created instance using `InvokeMember` with `BindingFlags.Public | BindingFlags.Instance | BindingFlags.InvokeMethod`.
  - Prints the returned value's `ToString()` representation.
- Waits for final user input before exiting (`Console.ReadLine()`), keeping the console visible.

The overall purpose is to demonstrate runtime reflection: discovering types and methods dynamically, creating objects, collecting input for parameters, and invoking methods without compile-time knowledge of the types. It is primarily an educational/demo tool showing how reflection can be used to call code in an external assembly interactively.

Important runtime caveats and failure modes (summary):

- `Assembly.LoadFrom` and `GetTypes()` can throw; there is no `try/catch`. Add error handling for robustness.
- `CreateInstance` may return `null` (no public parameterless ctor) — invoking instance methods on a `null` instance will fail.
- `GetMethods()` returns many inherited methods; you may want to filter (`BindingFlags.DeclaredOnly`, `method.IsPublic`, `!method.IsSpecialName`).
- `Convert.ChangeType` cannot convert every type (enums, complex types, nullable) and can throw format/cast exceptions. Use `Enum.Parse`, custom parsers, or `TypeConverter` when needed.
- `InvokeMember` may throw `TargetInvocationException` which wraps exceptions thrown by the target method — unwrap to see the inner exception.
- Code assumes `result` is non-null and calls `ToString()`; add null checks.
- Hard-coded absolute path reduces portability; prefer configuration, relative paths, or user input.

If you want, I can add a short "improvements" section with a safe version of this program that adds null checks, proper exception handling, and better parameter parsing.