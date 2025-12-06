# DemoHelloWorld

This markdown documents the `DemoHelloWorld` project. It includes the full source code for `Program.cs`, detailed line-by-line explanations for the code and inline comments, and what/why/how guidance. Example code blocks have been removed; comments in source are explained below.

---

## Projects to be generated
- DemoHelloWorld

---

Line-by-line explanation and comments
- `using MathLib;`
  - What: Imports the `Maths` class from the `MathLib` namespace into scope.
  - Why: So `Program` can instantiate and call `Maths` methods without fully-qualified names.
  - How: The compiler requires a project reference to `MathLib` for this to resolve.

- `namespace DemoHelloWorld`:
  - What: Declares the namespace for the program.
  - Why: Organizes code and prevents name collisions.
  - How: Affects the full name of types (e.g., `DemoHelloWorld.Program`).

- `public class Program` and `public static void Main(string[] args)`:
  - What: Declares the program class and its entry point method.
  - Why: The CLR looks for `Main` to start execution.
  - How: `Main` is static so the runtime can call it without instantiating `Program`.

- Input prompts and reads (`Console.WriteLine`, `Console.ReadLine`):
  - What: Interact with the user via the console to obtain string input.
  - Why: The program expects numeric input from the user.
  - How: `Console.ReadLine` returns a string or null in some environments.

- `int x = Convert.ToInt32(xValue);` and `int y = Convert.ToInt32(yValue);`
  - What: Convert strings to integers.
  - Why: `Maths.Add` expects integers.
  - How: `Convert.ToInt32` throws exceptions on invalid input; no validation present in this code.
  - Comment guidance: Replace with `int.TryParse` for robust programs. Comments in code should highlight the exception risk.

- `Maths obj = new Maths();` and `int result = obj.Add(x, y);`
  - What: Instantiate `Maths` and call `Add`.
  - Why: Demonstrates separation of concerns (I/O vs logic).
  - How: `Maths.Add` executes and returns an int.

- `Console.WriteLine(result);` and `Console.ReadLine();`
  - What: Print the result and keep the console open until Enter is pressed.
  - Why: Allows user to view result in environments that close the console on exit.
  - How: Blocks on `Console.ReadLine()`.

Comments explained
- The original source contains no inline comments besides typical prompts. Any comment placed near `Convert.ToInt32` should explain the failure modes (throws on invalid input) and recommend `int.TryParse`.

Flow of execution
1. CLR invokes `Main`.
2. Program prompts for X and Y and reads strings.
3. Program converts strings to integers using `Convert.ToInt32`.
4. Program constructs `Maths` and calls `Add`.
5. Program prints the result and waits for final Enter before exiting.

---

## Detailed line-by-line expansions (Program.cs)

- `int x = ReadIntFromConsole("Enter value of X: ");`
  - What: robust input. Why: avoid exceptions from invalid input. How: loop until `int.TryParse` succeeds.

- `Maths obj = new Maths(); int result = obj.Add(x, y);`
  - What: call library method. Why: demonstrates separation of responsibilities. How: write unit tests for `Maths` and for input parsing helper.

Testing note:
- Add unit tests asserting `ReadIntFromConsole` behavior using input injection in tests (e.g., using `TextReader` replacement).

End of `DemoHelloWorld` documentation.

The file contains descriptive explanations; no code blocks were modified in this batch.

---

## If a C# keyword is accidentally removed (what happens)

- The typical resolution is to read the compiler error and restore the keyword or revert the change from source control.
