# Day3_02 — Project Documentation

## Overview
Demonstrates a simple singleton pattern for a `Logger` class and a `SqlServer` class with basic methods. The `Main` method obtains the singleton logger and logs a message.

Files:
- `Program.cs` — contains `SqlServer`, `Logger`, and `Main`.

## Run
- Build: `dotnet build`
- Run: `dotnet run --project Day3_02\Day3_02.csproj`

## Source (`Program.cs`)
```csharp
namespace Day3_02
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Logger.CurrentLogger.Log("Main");
            
        }
    }

    public class SqlServer
    {
        public void Insert()
        {
            Console.WriteLine("Inserted in sql server");
        }

        public void Delete()
        {
            Console.WriteLine("Deleted from sql server");
        }
    }

    public class Logger
    {
        public static Logger logger = new Logger();

        private Logger()
        {
            Console.WriteLine("Logger is created");
        }

        public static Logger CurrentLogger
        {
            get { return logger; }
        }
        public void Log(String msg)
        {
            Console.WriteLine("Logged at " + DateTime.Now.ToString() + " -> " + msg);
        }

    }
}
```

## High-level flow
1. `Logger` uses a static field and private constructor to enforce singleton.
2. `Main` calls `Logger.CurrentLogger.Log("Main")` which writes a timestamped message.

---

## Detailed line-by-line explanation

1. `namespace Day3_02`
   - What: Encapsulates all types in this example under a namespace.
   - Why: Prevents name conflicts and groups related types.
   - How: Types defined inside the braces belong to `Day3_02`.

2. `internal class Program`
   - What: Declares the `Program` class with `Main`.
   - Why: Holds program entry point.
   - How: `internal` restricts visibility to the assembly.

3. `static void Main(string[] args)`
   - What: Application entry point called by runtime.
   - Why: Execution starts here.
   - How: `args` receives command-line arguments.

4. `Logger.CurrentLogger.Log("Main");`
   - What: Accesses the singleton `Logger` instance and logs the message "Main".
   - Why: Demonstrates calling a method on singleton instance.
   - How: `CurrentLogger` returns static `logger` field; `Log` writes to console with timestamp.

5. `public class SqlServer` ...
   - What: Defines a simple `SqlServer` class with `Insert` and `Delete` methods.
   - Why: Example placeholder for DB operations.
   - How: Methods write messages to console.

6. `public class Logger` ...
   - What: Implements a basic singleton logger.
   - Why: Ensure a single shared logger instance across application.
   - How: A `public static Logger logger` field initialized with `new Logger()` and a private constructor prevent external instantiation.

7. `private Logger()`
   - What: Private constructor outputs a message when invoked.
   - Why: Prevent external classes from creating additional instances.
   - How: Only this class can call `new Logger()` (it does so in the static field initializer).

8. `public static Logger CurrentLogger { get { return logger; } }`
   - What: Public accessor to singleton instance.
   - Why: Provides a named property to obtain the singleton.
   - How: Getter returns the static field.

9. `public void Log(String msg)`
   - What: Writes a timestamped log message to console.
   - Why: Demonstrate logging functionality.
   - How: Uses `DateTime.Now.ToString()` and `Console.WriteLine`.

---

## Layman explanation & real-world analogy

Imagine a small office with many employees but only one central noticeboard where all important messages must be posted so everyone reads the same announcements. The `Logger` is that single noticeboard shared by the whole app. No matter which employee (part of the program) writes a log, they all post to the same board.

- The `Logger` class creates one noticeboard (`logger`) when the program starts.
- Any part of the program asks for `CurrentLogger` to write a message; it always gets the same noticeboard.

Plain-language example:
- When the program runs, it posts "Main" with a timestamp to the noticeboard. Later, other parts of the program could post messages like "Connected to DB" or "User logged in" and they'd all appear on the same board.

## Plain-language usage flow
1. Program starts.
2. It grabs the universal logger (the single noticeboard).
3. It posts a message "Main" with current time.
4. The console shows who wrote the message and when.

---

## Practical improvements and considerations (detailed)
- Thread-safety: Current static field `logger` is created during type initialization which is thread-safe in .NET, but if initialization logic becomes heavy prefer `Lazy<Logger>`.
- Readonly: Mark the static field `public static readonly Logger logger` to prevent accidental reassignment.
- Logging features: Add log levels (Info, Warn, Error), write to files, rotate logs, or expose sinks like console and file. Consider using `Microsoft.Extensions.Logging`.
- Testability: For unit tests, abstract logging via an interface (`ILogger`) and inject implementations.

---
