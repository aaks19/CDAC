# LoggerLib

This document contains the full code, expanded explanations, suggested improvements for safety, configurability, and examples for `LoggerLib`. It also includes a flow of execution section describing how logging occurs at runtime.

---

## Projects to be generated
- LoggerLib

---

## `Class1.cs` (original code)
```csharp
namespace LoggerLib
{
    public class FileLogger
    {
        private static FileLogger logger = new FileLogger();
        private FileLogger()
        {
        }

        public static FileLogger CurrentLogger
        {
            get { return logger; }
        }
        public void Log(string message)
        {
            FileStream stream = null;
            string filePath = "D:\\IACSD\\IACSDDemos\\log.txt";

            if (File.Exists(filePath))
            {

                stream = new FileStream(filePath, FileMode.Append, FileAccess.Write);
            }
            else
            {
                stream = new FileStream(filePath, FileMode.Create, FileAccess.Write);
            }


            string messageData = "Logged at " + DateTime.Now.ToString() + " - " + message;

            StreamWriter writer = new StreamWriter(stream);

            writer.WriteLine(messageData);

            writer.Close();
            stream.Close();
        }
    }
}
```

Why change the implementation
- The current implementation works but has shortcomings:
  - Hard-coded file path is brittle and not portable.
  - Streams are manually closed; exceptions may prevent closure and leak resources.
  - Not thread-safe: concurrent callers can conflict when opening/appending.

Suggested improved implementation (concept)
```csharp
public sealed class FileLogger
{
    private static readonly Lazy<FileLogger> _instance = new Lazy<FileLogger>(() => new FileLogger());
    private readonly string _filePath;

    private FileLogger(string? filePath = null)
    {
        _filePath = filePath ?? Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "log.txt");
    }

    public static FileLogger Current => _instance.Value;

    public void Log(string message)
    {
        string messageData = $"Logged at {DateTime.Now:O} - {message}";
        try
        {
            lock (_instance)
            {
                using (var stream = new FileStream(_filePath, FileMode.Append, FileAccess.Write, FileShare.Read))
                using (var writer = new StreamWriter(stream))
                {
                    writer.WriteLine(messageData);
                }
            }
        }
        catch
        {
            // swallow or handle logging failures appropriately
        }
    }
}
```

Key improvements explained
- `Lazy<T>` ensures thread-safe lazy initialization.
- `Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "log.txt")` creates a relative, portable default path.
- `using` ensures streams/writers are disposed even on exceptions.
- `lock` prevents concurrent writes interfering; alternative: use `ConcurrentQueue<string>` with background flushing for high-performance scenarios.
- `FileShare.Read` allows other processes to read the log while being written.
- Timestamp format `:O` (round-trip) is unambiguous and sortable.

Usage example
```csharp
FileLogger.Current.Log("Application started");
```

Flow of execution
- When `Log` is called:
  1. The `Log` method formats the message with a timestamp.
  2. The logger acquires a lock (if implemented) to ensure only one writer writes at a time.
  3. A `FileStream` and `StreamWriter` are created and used within `using` blocks so they are disposed automatically.
  4. The message is written; resources are released and the method returns.
  5. If an exception occurs while writing, the catch block should handle or report it according to policy (here we suggest swallowing or handling appropriately).

When to adopt a logging framework
- For production applications, prefer structured logging frameworks such as `Serilog`, `NLog`, or `Microsoft.Extensions.Logging` which provide sinks, levels, and robust configuration.

---

## Detailed What / Why / How (summary)

What: A simple file-based logger implemented as a singleton that writes timestamped messages to disk.

Why: Demonstrates logging mechanics and common pitfalls (resource leaks, concurrency, configuration). In small demos a file logger is useful for tracing, but production apps should use a robust logging framework.

How: Use a thread-safe singleton (Lazy<T>) and `using` statements to ensure streams are disposed. Prefer configurable file paths and background flushing for high-throughput scenarios. Consider integrating `Microsoft.Extensions.Logging` or `Serilog` for structured logging and richer features.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- A demo logger that writes timestamped messages to a file, implemented as a singleton in the example.

Why (expanded):
- Logging is essential for diagnosing runtime behavior and should be reliable, non-blocking, and configurable.

How (expanded):
- Prefer `Lazy<T>` for thread-safe singleton initialization.
- Use `using` blocks for streams and a lock or background queue to prevent concurrent write conflicts.
- Provide configuration for the file path, rotation policy (size-based or time-based), and retention.

Production advice:
- Use a logging framework for features like structured logs, sinks, and levels (INFO, WARN, ERROR).
- Use standard timestamp formats (ISO 8601) and include context (correlation IDs) where appropriate.

---

## Detailed line-by-line expansions (FileLogger)

- `private static FileLogger logger = new FileLogger();`
  - What: Eager singleton instance. Why: Provide a globally accessible logger. How: Use `Lazy<T>` to avoid eager allocation and to support thread-safe lazy initialization.

- `StreamWriter writer = new StreamWriter(stream); writer.WriteLine(messageData); writer.Close(); stream.Close();`
  - What: Writes message and closes resources. Why: Persist log. How: Wrap in `using` so disposal occurs even on exceptions; consider `FileShare.Read` and buffered writes.

- `string filePath = "D:\\IACSD\\IACSDDemos\\log.txt";`
  - What: Hard-coded path. Why: For demo convenience. How: Make path configurable; use `Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "logs")` for relative default.

---

## If a C# keyword is accidentally removed (what happens)

- Removing `using System.IO;` would cause file-related APIs to be unresolved, producing `CS0246`.
- Removing `using` or forgetting `using` around streams can cause resource leaks if `using` statements are removed; watch for `IDisposable` misuses.

Reliability and performance tips:
- For high-volume logging, buffer messages concurrently and write on a single background thread.
- For multi-process scenarios, use an IPC-friendly sink or a logging framework that supports locking and rotation.

End of `LoggerLib` documentation.
