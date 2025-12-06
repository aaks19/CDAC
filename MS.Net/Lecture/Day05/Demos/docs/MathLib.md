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
        public int Add(int x, int y) // What: returns sum of x and y. Why: reusable arithmetic. How: uses 32-bit addition.
        {
            return x + y;
        }

        public int Sub(int x, int y) // What: returns difference x - y. Why: reusable subtraction. How: uses 32-bit subtraction.
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

---

## Detailed line-by-line expansions (Maths.cs)

- `public int Add(int x, int y)`:
  - What: compute x + y. Why: core operation for examples. How: consider `checked` context to detect overflow if correctness is critical: `checked { return x + y; }`.

- `public int Sub(int x, int y)`:
  - What: compute x - y. Why: core subtraction. How: handle range considerations and tests.

Testing guidance:
- Write unit tests for boundary values (int.MinValue, int.MaxValue) and normal cases.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- The docs describe the `Maths` class and detail considerations for integer arithmetic and overflow.

Why (expanded):
- To highlight design choices, e.g., when to use `checked` and when to choose larger numeric types.

How (expanded):
- Suggest tests and mention `checked` semantics where appropriate.

---

## If a C# keyword is accidentally removed (what happens)

- Missing keywords produce compiler diagnostics; the IDE points to the exact offending line so you can restore the token from VCS or undo.
