# `Program.cs` — Complete Code, Detailed Explanation, and Flow

This file documents the `Program.cs` file in the `Day4_02_FileIO` project. It contains the full source, an explanation of the program flow, and a detailed line-by-line explanation in a What / Why / How format.

- C# language version: `12.0`
- Target framework: `.NET 8`

---

## Source Code (`Program.cs`)

```csharp
namespace Day4_02_FileIO
{
    internal class Program
    {
        static void Main(string[] args)
        {
            FileStream stream = null;
            string path = "F:\\CDAC\\MS.Net\\Practice\\LabPractice\\myText";
            //string file_name = "myText";
            if (File.Exists(path))
            {
                //if file exists open the file and write in the file
                stream = new FileStream(path, FileMode.Open, FileAccess.Write);
            }
            else
            {
                // Create file if not exist and then write in the file
                stream = new FileStream(path, FileMode.Create, FileAccess.Write);
            }

            Console.WriteLine("Start Writing");
            string data = Console.ReadLine();

            StreamWriter writer = new StreamWriter(stream);
            writer.WriteLine(data);

            writer.Close();
            stream.Close();
            Console.WriteLine("Writing Completed");
        }
    }
}
```

---

## High-level Summary

This console application writes a single line of text read from standard input to a file located at a hard-coded path. It checks whether the file exists; if it does, it opens the file for writing (using `FileMode.Open`), otherwise it creates a new file (using `FileMode.Create`). It uses a `FileStream` together with a `StreamWriter` to write the text, then closes both the writer and the stream.

The program is synchronous and simple — it does not append by default, and it overwrites from the start when opening in write mode without seeking.

---

## How to use / Run

1. Ensure you have .NET 8 SDK installed.
2. Open a terminal in the project folder that contains the project file, e.g. `Day4_02_FileIO`.
3. Build and run:
   - `dotnet build`
   - `dotnet run --project Day4_02_FileIO.csproj`
4. When the console shows `Start Writing`, type the text you want to write and press Enter. The program writes the text to the file at the configured path and ends.

Note: The program uses an absolute Windows path. If running on another machine, update the `path` variable to a valid location.

---

## Program Flow (step-by-step)

1. Initialize a `FileStream` variable to null.
2. Define the file path to write to.
3. Check if the file exists at that path using `File.Exists`.
4. If the file exists, open it using `FileMode.Open` with `FileAccess.Write`.
5. If the file does not exist, create it using `FileMode.Create` with `FileAccess.Write`.
6. Prompt the user by printing `Start Writing` and read one line from the console.
7. Create a `StreamWriter` wrapping the `FileStream` and write the user input as a new line.
8. Close the `StreamWriter` and the underlying `FileStream`.
9. Print `Writing Completed` and exit.

---

## Detailed line-by-line explanation (what / why / how)

Below the program is broken down by logical lines or small groups of related lines. Each item shows: What it does, Why it is there, and How it works.

1. `namespace Day4_02_FileIO`
   - What: Declares a namespace for grouping related types.
   - Why: Namespaces avoid type name collisions and logically organize code.
   - How: All types declared inside belong to the `Day4_02_FileIO` namespace.

2. `internal class Program`
   - What: Declares an internal class named `Program`.
   - Why: The `Program` class contains application entry point; `internal` limits visibility to the assembly.
   - How: The class acts as a container for `Main` method.

3. `static void Main(string[] args)`
   - What: The application entry point — where execution starts for a console app.
   - Why: The runtime looks for a `Main` method to begin execution.
   - How: `args` receives command-line arguments; this method runs synchronously on start.

4. `FileStream stream = null;`
   - What: Declares a variable named `stream` of type `FileStream` and initializes it to `null`.
   - Why: Prepare a variable to hold the file stream that will be opened or created later.
   - How: The variable is assigned later depending on whether the file exists.

5. `string path = "F:\\CDAC\\MS.Net\\Practice\\LabPractice\\myText";`
   - What: Declares and initializes a `string` variable named `path` with an absolute file path.
   - Why: The program needs the path to determine where to write the file.
   - How: This is a Windows-style absolute path. Note the escape sequences for backslashes (each `\\` is a single `\` inside the string literal).

6. `//string file_name = "myText";`
   - What: A commented-out line that previously declared a filename.
   - Why: Possibly left in as a note or for future refactor.
   - How: Comments are ignored by the compiler.

7. `if (File.Exists(path))` ... block
   - What: Tests whether a file exists at the specified `path`.
   - Why: The program wants to open the file in a specific way depending on existence (Open vs Create).
   - How: `File.Exists` returns `true` if the file exists and the caller has sufficient permissions; `false` otherwise.

8. `stream = new FileStream(path, FileMode.Open, FileAccess.Write);`
   - What: Creates a `FileStream` object that opens the existing file for writing.
   - Why: To obtain a writable stream to the file so `StreamWriter` can write data.
   - How: `FileMode.Open` opens an existing file; if the file does not exist, an exception would be thrown (but this branch only executes when `File.Exists(path)` is true). `FileAccess.Write` requests write permission; reading is not requested.

9. `stream = new FileStream(path, FileMode.Create, FileAccess.Write);`
   - What: Creates a `FileStream` that creates a new file (or overwrites an existing file).
   - Why: If the file does not exist, we must create it before writing.
   - How: `FileMode.Create` creates a new file; if a file already exists, it is overwritten. `FileAccess.Write` allows writes.

10. `Console.WriteLine("Start Writing");`
    - What: Writes the message `Start Writing` to the console.
    - Why: Signals to the user that the program is ready to accept input.
    - How: `Console.WriteLine` writes text followed by a newline to standard output.

11. `string data = Console.ReadLine();`
    - What: Reads a single line of text from standard input and stores it in `data`.
    - Why: The program uses this input as the content to write into the file.
    - How: `Console.ReadLine` blocks until the user presses Enter and returns the entered text (or `null` on end-of-stream).

12. `StreamWriter writer = new StreamWriter(stream);`
    - What: Instantiates a `StreamWriter` that wraps the previously opened `FileStream`.
    - Why: `StreamWriter` provides convenient text-based write methods (like `WriteLine`) and handles text encoding.
    - How: The constructor takes a `Stream` instance and by default uses `UTF8Encoding` without BOM. Note: the `StreamWriter` does not own the stream fully in all constructors, but calling `writer.Close()` will flush and close the underlying stream by default.

13. `writer.WriteLine(data);`
    - What: Writes the content of `data` followed by a newline to the file via the `StreamWriter`.
    - Why: Persist the user-provided text into the file.
    - How: `WriteLine` converts the string to bytes using the writer's encoding and writes them to the underlying stream.

14. `writer.Close();`
    - What: Closes the `StreamWriter`, flushing buffers and releasing resources.
    - Why: Ensures text is flushed to disk and stream resources are released.
    - How: `Close` calls `Flush` and then `Dispose`. Best practice is to use `using` statements (or `await using` / `using var`) to guarantee disposal, even on exceptions.

15. `stream.Close();`
    - What: Closes the underlying `FileStream` explicitly.
    - Why: To release the OS file handle.
    - How: After closing, further operations on `stream` will throw an exception.

16. `Console.WriteLine("Writing Completed");`
    - What: Writes a completion message to standard output.
    - Why: Inform the user the operation finished.
    - How: `Console.WriteLine` prints text followed by a newline.

---

## Important notes, edge cases and recommended improvements

1. Resource management (use `using`):
   - Current code manually calls `Close()` on writer and stream. If an exception occurs between opening and closing, resources could leak. Prefer `using` statements, for example:

```csharp
using var stream = File.Exists(path)
    ? new FileStream(path, FileMode.Open, FileAccess.Write)
    : new FileStream(path, FileMode.Create, FileAccess.Write);
using var writer = new StreamWriter(stream);
writer.WriteLine(data);
```

This ensures deterministic disposal even on exceptions.

2. Writing mode / appending:
   - The current behavior opens the file for write but does not append; opening with `FileMode.Open` + `FileAccess.Write` will start writing at the beginning of the file (overwriting). If the intent is to append, use `FileMode.Append` or `new StreamWriter(path, append: true)`.

3. Path handling:
   - The program uses a hard-coded absolute path, which reduces portability. Use `Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments)` or a relative path and configuration.

4. Encoding and culture:
   - `StreamWriter` defaults to UTF-8 without BOM on .NET Core/.NET 5+. If you need a specific encoding, pass it explicitly to the `StreamWriter` constructor.

5. Error handling:
   - The program lacks `try/catch` blocks to report file I/O errors (permission denied, path not found, disk full, etc.). Consider catching `IOException` and reporting meaningful messages.

6. Null input handling:
   - `Console.ReadLine()` may return `null` if input is closed. Consider validating `data` before calling `WriteLine`.

7. Security:
   - If this program is adapted to accept paths from users, validate and sanitize inputs to prevent path traversal.

---

## Minimal improved version (recommended)

Below is a compact safer variant that uses `using` and appends text.

```csharp
// Example improved snippet (not replacing original file automatically)
using System;
using System.IO;

namespace Day4_02_FileIO
{
    internal class Program
    {
        static void Main()
        {
            string path = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments), "myText.txt");
            Console.WriteLine("Start Writing");
            string? data = Console.ReadLine();
            if (string.IsNullOrEmpty(data))
            {
                Console.WriteLine("No input provided.");
                return;
            }

            try
            {
                // Append to file safely and ensure disposal
                using var writer = new StreamWriter(path, append: true, encoding: System.Text.Encoding.UTF8);
                writer.WriteLine(data);
                Console.WriteLine("Writing Completed");
            }
            catch (IOException ex)
            {
                Console.WriteLine($"I/O error: {ex.Message}");
            }
        }
    }
}
```

This version is more robust, portable, and appends rather than overwrites.

---

## File created

A downloadable markdown file with this documentation was created at:

`Day4_02_FileIO/Program_Documentation.md`

You can open or download it from your workspace.

---

If you want, I can:
- Replace the original `Program.cs` with the improved version, or
- Add unit tests or a small wrapper to accept a relative path argument.

Tell me which action to take next.
