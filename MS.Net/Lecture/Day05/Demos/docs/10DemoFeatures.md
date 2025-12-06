# 10DemoFeatures

This document contains full code extracts and expanded explanations for the `10DemoFeatures` project, which demonstrates many C# language features: generics, delegates, collections, nullable types, and more. Example runnable code blocks were removed; explanations and small exercises are preserved in descriptive form and comments are explained with what/why/how guidance.

---

## Projects to be generated
- 10DemoFeatures

---

## `Program.cs` (selected important sections shown and explained)
The project `Program.cs` is long and contains multiple demo regions. Below are the key active parts, expanded explanations, flow of execution, and suggested exercises described textually.

### Active code (Collections and Emp class) - described
- What: The program demonstrates creating three `Emp` objects with properties `No`, `Name`, and `Address`, using a `List<Emp>`, `Stack<Emp>`, and `Queue<Emp>` to illustrate collection semantics.
- Why: Shows idiomatic usage of generic collections and the differences between LIFO and FIFO containers.
- How: Construct instances, add them to the collections, and enumerate or pop/dequeue items to observe behavior.

`Emp` class structure explanation
- Private backing fields `_No`, `_Name`, `_Address` and public properties `No`, `Name`, `Address`:
  - What: Encapsulate fields and expose them via properties.
  - Why: Allow future logic in getters/setters if needed and maintain binary compatibility with auto-properties if later refactored.
  - How: Properties map to fields and are publicly accessible for reading and writing.

Detailed explanations and small exercises (textual)
- Generics and Collections:
  - What: `List<T>` is a type-safe, resizable array-like collection that stores items of a single type `T`.
  - Why: Prevents runtime casting errors and provides LINQ and collection operations.
  - How: Use `List<Emp>` to store `Emp` instances and iterate to display fields.

  - Stack:
    - What: `Stack<T>` is a LIFO collection where `Push` adds items and `Pop` removes the most recently added item.
    - Why: Useful for undo stacks and nested operations.
    - How: Push items and then Pop to observe reversed order.

  - Queue:
    - What: `Queue<T>` is a FIFO collection; `Enqueue` adds and `Dequeue` removes the earliest added item.
    - Why: Useful for task scheduling and breadth-first traversals.
    - How: Enqueue items and then Dequeue to observe preserved order.

- Delegates and Func/Action:
  - What: Demonstrates how to assign method group conversions to `Func<int,bool>` or `Action<T>` delegates.
  - Why: Encourages separating behavior from consumers and using higher-order functions.
  - How: Replace method references with `Func<int,bool>` instances and call them like regular methods.

- Nullable types and safe handling:
  - What: `int?` allows an integer to be null.
  - Why: Useful when modeling optional numeric values without resorting to sentinel values.
  - How: Use `HasValue` and `GetValueOrDefault` or the null-coalescing operator to manage nullable values.

Flow of execution
- Typical runtime sequence described:
  1. `Main` constructs three `Emp` instances with sample data.
  2. `Main` demonstrates `List<Emp>` by adding and iterating items.
  3. `Main` demonstrates `Stack<Emp>` and `Queue<Emp>` by pushing/enqueuing and popping/dequeuing.
  4. `Main` waits on user input before exiting.

Comments explained
- The file contains example comments and suggested exercises. For each comment the what/why/how is:
  - What: The comment indicates an exercise or a region demonstrating a language feature.
  - Why: Provide learners with short hands-on tasks to solidify concepts.
  - How: Follow the textual exercise instructions rather than running the removed code blocks; copy small snippets into new projects to execute them.

What / Why / How summary
- What: Demonstrates language features and typical collection usage.
- Why: Hands-on examples help students learn idiomatic C# patterns.
- How: Recreate small snippets in new projects or uncomment regions in the original solution to run them locally.

---

## Expanded What / Why / How (detailed guidance)

What (expanded):
- The docs present teaching examples and explain how to practice language features.

Why (expanded):
- Reinforce learning by suggesting hands-on exercises and test-driven tasks.

How (expanded):
- Include small, runnable snippets for students and explain expected behaviors, edge cases, and pitfalls.

---

## Detailed line-by-line expansions (selected snippets)

- `Console.WriteLine(emp.No + " - " + emp.Name + " (" + emp.Address + ")");`
  - What: prints concatenated string. Why: demo output. How: use string interpolation for clarity: `$"{emp.No} - {emp.Name} ({emp.Address})"`.

- `stack.Pop()` and `queue.Dequeue()` behaviors:
  - What: remove items from collection. Why: demonstrates LIFO/FIFO. How: guard with `Count` to avoid `InvalidOperationException`.

- `public static bool Check(int i)` used with `Func<int,bool>`:
  - What: an example method to assign to delegates. Why: teaches delegates and method group conversion. How: use lambdas for concise inline behavior.

Teaching tips:
- Encourage students to write unit tests for behaviors and explore LINQ to manipulate collections.

---

## If a C# keyword is accidentally removed (what happens)

- Students may accidentally remove `foreach` or `using`; these produce errors that the compiler surfaces. Use IDE features to find and restore keywords.

End of `10DemoFeatures` documentation.
