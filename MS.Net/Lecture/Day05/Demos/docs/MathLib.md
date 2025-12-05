# MathLib

This document contains the full code and detailed line-by-line explanations for the `MathLib` project. Examples were removed; comments and reasoning are expanded per line.

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

Line-by-line explanation and comments
- `namespace MathLib`:
  - What: Groups the `Maths` class under `MathLib` namespace.
  - Why: Prevents naming conflicts and denotes library purpose.
  - How: Types inside use fully-qualified names `MathLib.Maths`.

- `public class Maths`:
  - What: Class that contains arithmetic operations.
  - Why: Provide reusable arithmetic methods that can be referenced by other projects.
  - How: Public visibility allows other assemblies to instantiate `Maths` if referenced.

- `public int Add(int x, int y)` method:
  - What: Returns the sum of two integers.
  - Why: Encapsulates addition logic inside a library method for reuse and testing.
  - How: Performs `x + y` using 32-bit integer arithmetic; note overflow behavior.
  - Comment guidance: Document that overflow wraps in unchecked contexts; use `checked` to throw on overflow.

- `public int Sub(int x, int y)` method:
  - What: Returns the difference `x - y`.
  - Why: Provides subtraction logic.
  - How: Performs `x - y` on 32-bit integers; consider range and overflow as with `Add`.
  - Comment guidance: State the expected domain and any overflow expectations.

Flow of execution
- When a caller invokes `Add` or `Sub`:
  1. The call is resolved by the CLR to the method implementation.
  2. The method executes the arithmetic operation and returns the result.
  3. The caller receives the integer and continues execution.

End of `MathLib` documentation.
