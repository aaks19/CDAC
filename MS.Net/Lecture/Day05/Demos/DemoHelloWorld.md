# DemoHelloWorld

This markdown documents the `DemoHelloWorld` project. It includes the full source code for `Program.cs`, detailed line-by-line explanations, safer input handling examples, flow of execution, what/why/how details, and a real-life layman example.

---

## Projects to be generated
- DemoHelloWorld

---

## `Program.cs` (original code)
```csharp
using MathLib;
namespace DemoHelloWorld
{
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("Enter value of X");
            string xValue = Console.ReadLine();

            Console.WriteLine("Enter value of Y");
            string yValue = Console.ReadLine();

            int x = Convert.ToInt32(xValue);
            int y = Convert.ToInt32(yValue);

            Maths obj =  new Maths();

            int result = obj.Add(x, y);

            Console.WriteLine(result);
            Console.ReadLine();
        }
    }
}
```

Expanded explanation (line-by-line and reasoning):
- `using MathLib;` — imports the `Maths` class so `Program` can create and use it. This decouples arithmetic logic into a reusable library.
- `public static void Main(string[] args)` — the application's entry point. The runtime calls this method to start execution.
- `Console.WriteLine("Enter value of X");` and `Console.ReadLine()` — prompt the user and read input as a string. Console I/O is simple but brittle; users may enter invalid values.
- `Convert.ToInt32(xValue)` — converts the string to an integer. If `xValue` is not a valid integer, `FormatException` will be thrown and the program will crash.
- `Maths obj = new Maths();` — demonstration of using another project in the solution (MathLib) that provides the `Add` method.
- `obj.Add(x,y)` — uses library method; keeps `Program` focused on input/output, not arithmetic implementation.
- `Console.ReadLine();` at the end keeps the console open so users can see results.

Safer version with input validation (recommended)
```csharp
using MathLib;
namespace DemoHelloWorld
{
    public class Program
    {
        public static void Main(string[] args)
        {
            int x = ReadIntFromConsole("Enter value of X: ");
            int y = ReadIntFromConsole("Enter value of Y: ");

            Maths obj = new Maths();
            int result = obj.Add(x, y);

            Console.WriteLine("Result: " + result);
            Console.WriteLine("Press Enter to exit...");
            Console.ReadLine();
        }

        private static int ReadIntFromConsole(string prompt)
        {
            while (true)
            {
                Console.Write(prompt);
                string? input = Console.ReadLine();

                if (int.TryParse(input, out int value))
                {
                    return value;
                }

                Console.WriteLine("Invalid number, please try again.");
            }
        }
    }
}
```

Why this change helps:
- `int.TryParse` avoids exceptions by returning a boolean success flag.
- `ReadIntFromConsole` loops until valid input is provided: better user experience and robust program.

Flow of execution
- Program start:
  1. CLR loads the executable and invokes `Main`.
  2. `Main` calls `ReadIntFromConsole` twice to obtain `x` and `y`. Each call prompts the user and waits for valid input.
  3. `Main` constructs a `Maths` instance from `MathLib` and calls `Add(x, y)`.
  4. The result is printed and the program waits for the user to press Enter before exiting.

## Detailed What / Why / How (summary)

What: A simple console application that obtains two integers from the user and prints their sum using `MathLib.Maths`.

Why: Demonstrates clean separation of I/O and business logic, and highlights safe input handling concerns for beginners.

How: Read input using `ReadIntFromConsole` helper (recommended) that uses `int.TryParse` to validate input and avoids exceptions. Instantiate `Maths` from `MathLib` and call `Add` to obtain the result. Print the result and wait for user acknowledgement before exiting.

Layman example:
- Instead of forcing the user to type perfectly every time (which crashes the app on mistakes), the program politely asks again until a valid number is entered — like a cashier confirming a price if the handwriting is unclear.

End of `DemoHelloWorld` documentation.

---

## Detailed line-by-line expansions (Program.cs)

- `Console.WriteLine("Enter value of X"); string xValue = Console.ReadLine();`
  - What: Prompt and read raw input. Why: Accept user data. How: Use `int.TryParse` to validate.
  - Failure mode: If the user types non-numeric input, `Convert.ToInt32` will throw.

- `int x = Convert.ToInt32(xValue);` and `int y = Convert.ToInt32(yValue);`
  - What: Convert strings to integers. Why: Feed typed values to `Maths.Add`. How: Prefer `int.TryParse` and loop to re-prompt on invalid input.

- `Maths obj = new Maths(); int result = obj.Add(x, y);`
  - What: Invoke library method. Why: Demonstrate use of a separate assembly. How: Keep logic separate from I/O for testability.

---

Testing and robustness tips:
- Add unit tests for `Maths` methods and for the parsing logic.
- Avoid `Console.ReadLine()` blocking calls in code used by automated systems; separate interactive and non-interactive modes.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- A simple console application that demonstrates separation between I/O and logic by using `MathLib`.

Why (expanded):
- Highlights input validation and the importance of separating concerns for maintainable code and testability.

How (expanded):
- Provide helper methods for parsing with `TryParse` loops and expose non-interactive modes (command-line args) for automated testing.
- Unit-test core logic in `MathLib` and keep `Program` thin. For integration tests, simulate console I/O streams.

---

## If a C# keyword is accidentally removed (what happens)

- Example: removing `using MathLib;` will produce `CS0246` when `Maths` is referenced. Add the using or fully qualify `MathLib.Maths`.
- Removing `static` from `Main` may prevent the runtime from finding the entry point if there's no other suitable `Main` overload.
