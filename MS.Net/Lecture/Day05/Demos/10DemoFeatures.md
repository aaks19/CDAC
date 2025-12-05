# 10DemoFeatures

This document contains full code extracts and expanded explanations for the `10DemoFeatures` project, which demonstrates many C# language features: generics, delegates, collections, nullable types, and more. Each region is annotated with examples, flow of execution, and suggested small exercises.

---

## Projects to be generated
- 10DemoFeatures

---

## `Program.cs` (selected important sections shown and explained)
The project `Program.cs` is long and contains multiple demo regions. Below are the key active parts, expanded explanations, flow of execution, and small runnable snippets to try.

### Active code (Collections and Emp class)
```csharp
using System.Collections;
using System.Collections.Generic;

namespace _10DemoFeatures
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Emp emp1 = new Emp { No = 1, Name = "Mahesh", Address = "Pune" };
            Emp emp2 = new Emp { No = 2, Name = "Nilesh", Address = "Panji" };
            Emp emp3 = new Emp { No = 3, Name = "Sujit", Address = "Chennai" };

            // Example: use List<Emp>
            var list = new List<Emp> { emp1, emp2, emp3 };
            foreach (var emp in list)
            {
                Console.WriteLine(emp.No + " - " + emp.Name + " (" + emp.Address + ")");
            }

            // Example: use Stack<Emp>
            var stack = new Stack<Emp>();
            stack.Push(emp1); stack.Push(emp2); stack.Push(emp3);
            Console.WriteLine("Popped: " + stack.Pop().Name);

            // Example: use Queue<Emp>
            var queue = new Queue<Emp>();
            queue.Enqueue(emp1); queue.Enqueue(emp2); queue.Enqueue(emp3);
            Console.WriteLine("Dequeued: " + queue.Dequeue().Name);

            Console.ReadLine();
        }

        public static bool Check(int i)
        {
            return i > 0;
        }
    }

    public class Emp
    {
        private int _No;
        private string _Name;
        private string _Address;

        public string Address
        {
            get { return _Address; }
            set { _Address = value; }
        }

        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        public int No
        {
            get { return _No; }
            set { _No = value; }
        }

    }
}
```

Detailed explanations and small exercises
- Generics and Collections:
  - `List<T>` is type-safe: it accepts only `Emp` objects, avoiding runtime casting errors common with `ArrayList`.
  - `Stack<T>` follows LIFO (last-in-first-out) semantics. Exercise: push three items and pop them; observe order.
  - `Queue<T>` follows FIFO (first-in-first-out). Exercise: enqueue three items and dequeue them; observe order.

- Delegates and Func/Action examples to try:
  - Replace `Check` with `Func<int, bool> f = Check;` and call `f(10)`.
  - Use `Func<int, bool> inline = i => i > 5;` to test lambda syntax.

- Nullable types and safe handling:
  - Try `int? maybe = null; Console.WriteLine(maybe.HasValue);` to see how nullable value types behave.

Flow of execution
- Typical runtime sequence for the active examples:
  1. `Main` constructs three `Emp` instances with sample data.
  2. `Main` populates a `List<Emp>`, iterates it and prints each entry.
  3. `Main` demonstrates `Stack<Emp>` by pushing three items and popping one.
  4. `Main` demonstrates `Queue<Emp>` by enqueuing three items and dequeuing one.
  5. `Main` then waits on `Console.ReadLine()` before exiting.

- For delegate usage:
  1. Define or assign a delegate (`Func<int,bool>`).
  2. Invoke the delegate like a method to get a boolean result.

Small exercises for students:
  1. Replace `List<Emp>` with `List<string>` storing only employee names.
  2. Implement a `Find` method to locate an `Emp` by `No` using `List<T>.Find` or LINQ.

What / Why / How summary
- What: Demonstrates language features and typical collection usage.
- Why: Hands-on examples help students learn idiomatic C# patterns.
- How: Try uncommenting regions or copying small examples into fresh projects to run them.

End of `10DemoFeatures` documentation.
