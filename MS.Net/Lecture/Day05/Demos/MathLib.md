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

---

## Detailed What / Why / How (summary)

**What**: A minimal math utility library exposing `Add` and `Sub` operations for integers.

**Why**: Keeps arithmetic logic in a reusable library so other projects can depend on it without duplicating code.

**How**: Methods are straightforward; watch for overflow semantics on 32-bit integers and consider `checked` or `long` return types for critical use cases.

---

## Detailed line-by-line expansions (Maths.cs)

- `public int Add(int x, int y)`:
  - What: Adds two integers. Why: Provide basic arithmetic operation for demos. How: Remember to consider overflow and possibly change to `long` for safety when inputs might exceed 32-bit ranges.

- `public int Sub(int x, int y)`:
  - What: Subtracts two integers. Why: Provide basic subtract operation. How: Consider input validation and overflow semantics where necessary.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- A minimal arithmetic library exposing integer operations used by demo apps.

Why (expanded):
- Provides a simple, testable API separate from user interaction logic; good for teaching separation of concerns.

How (expanded):
- Consider overflow handling and document behavior; optionally provide overloads for `long` and `decimal` for larger ranges or fractional math.
- Add unit tests for boundary conditions and common inputs.

---

Testing guidance:
- Add unit tests for typical and edge inputs (zero, negative values, large values, overflow scenarios).

---

## If a C# keyword is accidentally removed (what happens)

- Removing `public` from the `Maths` class makes it internal by default, which may lead to `CS0122` if external assemblies try to access it.
- Removing `return` in `Add` or `Sub` triggers `CS0161` or similar errors about missing return values.

End of `MathLib` documentation.
