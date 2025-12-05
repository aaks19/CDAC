# MathLib

This document contains the full code and expanded explanations for the `MathLib` project. It includes method behavior, edge cases, a flow of execution, and simple usage examples to help learners understand expected results.

---

## Projects to be generated
- MathLib

---

## `Maths.cs` (complete code)
```csharp
namespace MathLib
{
    public class Maths
    {
        public int Add(int x, int y)
        {
            return x + y;
        }

        public int Sub(int x, int y)
        {
            return x - y;
        }
    }
}
```

Expanded explanations
- `public int Add(int x, int y)`:
  - Adds two 32-bit signed integers and returns the result.
  - Edge case: adding two large integers may overflow. In C#, integer overflow for `int` wraps around by default in unchecked contexts. Consider using `checked` if you want an exception when overflow occurs or use `long` as return type to reduce risk.
  - Example: `Add(10, 20)` returns `30`.

- `public int Sub(int x, int y)`:
  - Subtracts `y` from `x` and returns the difference.
  - Edge case: subtraction may underflow/overflow like addition. Think about ranges when designing APIs.
  - Example: `Sub(20, 5)` returns `15`.

Usage examples
```csharp
var m = new Maths();
int a = m.Add(5, 6); // 11
int b = m.Sub(10, 4); // 6

// Handling potential overflow
try
{
    checked
    {
        int overflow = m.Add(int.MaxValue, 1);
    }
}
catch (OverflowException)
{
    Console.WriteLine("Integer overflow detected");
}
```

Flow of execution
- When a caller invokes a method on `Maths`:
  1. The CLR resolves the method call to `Maths.Add` or `Maths.Sub`.
  2. Parameters `x` and `y` are evaluated by the caller and passed to the method.
  3. The method body executes the arithmetic operation and returns the result to the caller.
  4. The caller receives and uses the returned integer.

When to extend this library
- Add methods for multiplication/division if needed.
- Provide `long` or `decimal` overloads for larger ranges or fractional numbers.
- Add unit tests that validate behavior with typical and edge-case inputs.

Layman example
- The library is a tiny pocket calculator that always returns the sum or difference of two whole numbers. If you try to store a number bigger than the screen can show (overflow), the calculator may wrap or report an error if in checked mode.

End of `MathLib` documentation.
