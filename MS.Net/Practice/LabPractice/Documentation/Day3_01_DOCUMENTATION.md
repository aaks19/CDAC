# Day3_01 — Project Documentation

## Overview
Demonstrates interfaces in C#. The project defines two interfaces `First` and `Second` and a class `Message` implementing both. The `Main` method creates `Message` objects and calls their methods.

Files:
- `Program.cs` — contains interfaces `First`, `Second` and class `Message`.

## Run
- Build: `dotnet build`
- Run: `dotnet run --project Day3_01\Day3_01.csproj`

## Source (`Program.cs`)
```csharp
namespace Day3_01
{
    internal class Program
    {
        static void Main(string[] args)
        {

            // Interface Demo...

            Message obj = new Message();
            Console.WriteLine(obj.sayBye("bye bye"));

            Message obj2 = new Message();
            Console.WriteLine(obj2.sayHello("Hello! It's Akshat"));

            Console.ReadLine();
        }
    }

    public interface First
    {
        string sayHi(string msg);
        string sayHello(string msg);
    }

    public interface Second
    {
        string sayHi(string msg);
        string sayBye(string msg);
    }

    public class Message : First, Second
    {
        public string sayBye(string msg)
        {
            return msg;
        }

        public string sayHello(string msg)
        {
            return msg;
        }

        public string sayHi(string msg)
        {
            return msg;
        }
    }
}
```

## High-level flow
1. `Message` implements methods required by both `First` and `Second`.
2. `Main` demonstrates calling those methods and printing results.

---

## Detailed line-by-line explanation

1. `namespace Day3_01`
   - What: Declares a namespace to contain types for this example.
   - Why: Namespaces isolate code and avoid collisions with other libraries.
   - How: All types within braces belong to `Day3_01`.

2. `internal class Program`
   - What: Declares the `Program` class which contains the `Main` method.
   - Why: The runtime locates `Main` to start the application.
   - How: `internal` makes the class visible only within the assembly.

3. `static void Main(string[] args)`
   - What: Entry point for the program.
   - Why: The CLR executes this method to run the app.
   - How: `args` provides command-line arguments; method is static so runtime can call it without instantiation.

4. `Message obj = new Message();`
   - What: Creates a new `Message` object and assigns it to `obj`.
   - Why: To call interface-implemented methods.
   - How: Uses the default constructor (compiler-provided if none defined).

5. `Console.WriteLine(obj.sayBye("bye bye"));`
   - What: Calls `sayBye` on `obj` and prints the returned string.
   - Why: Demonstrates `Message`'s `sayBye` implementation from `Second` interface.
   - How: `sayBye` simply returns the provided string which `Console.WriteLine` prints.

6. `Message obj2 = new Message();`
   - What: Creates another `Message` object assigned to `obj2`.
   - Why: Example uses a second instance (could reuse the first).
   - How: Same as step 4.

7. `Console.WriteLine(obj2.sayHello("Hello! It's Akshat"));`
   - What: Calls `sayHello` on `obj2` and prints the result.
   - Why: Demonstrates `sayHello` implementation from `First` interface.
   - How: Method returns input string; `Console.WriteLine` prints it.

8. `Console.ReadLine();`
   - What: Waits for user input to keep console open.
   - Why: Prevents console window from closing immediately after output.
   - How: Blocks until Enter key is pressed.

9. `public interface First` and methods
   - What: Declares interface `First` with `sayHi` and `sayHello`.
   - Why: Define a contract that implementing classes must provide these methods.
   - How: Interfaces only declare signatures, no implementation.

10. `public interface Second` and methods
    - What: Declares interface `Second` with `sayHi` and `sayBye`.
    - Why: Different contract that overlaps `sayHi` signature.
    - How: Allows classes to implement multiple behaviors.

11. `public class Message : First, Second`
    - What: Declares `Message` implementing both interfaces.
    - Why: Show how a class can fulfill multiple contracts.
    - How: Provides concrete implementations for `sayHi`, `sayHello`, and `sayBye`.

12. `public string sayBye(string msg) { return msg; }` (and similar methods)
    - What: Implementations of the interface methods that simply return the input.
    - Why: Minimal implementation for demonstration.
    - How: Methods return the `msg` parameter unchanged.

---

## Layman explanation & real-world analogy
Think of an interface as a job description. `First` and `Second` are two job descriptions listing tasks (methods). `Message` is a worker who can perform tasks from both job descriptions. The `Main` function hires a worker (`new Message()`) and asks them to perform tasks like "sayHello" or "sayBye", and prints what the worker says.

Real-world example:
- `First` could be "CustomerService" with methods to greet and assist.
- `Second` could be "Sales" with methods to greet and say farewell.
- `Message` is an employee trained for both roles; calling methods returns scripted responses.

## Example usage (plain language)
1. The program creates a `Message` worker.
2. It asks the worker to say "bye bye" and prints the response.
3. It asks another worker to say a greeting and prints it.
4. It waits so you can see the output.

This demonstrates that one object can fulfill multiple roles defined by interfaces.

---

## Suggested improvements
- Reuse the same `Message` instance unless separate state is required.
- Add meaningful behavior to methods (e.g., formatting, logging).
- Consider documenting which interface each method implements for clarity.

---
