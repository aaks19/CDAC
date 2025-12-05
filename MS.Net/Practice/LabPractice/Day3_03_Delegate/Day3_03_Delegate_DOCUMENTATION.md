# Day3_03_Delegate — Project Documentation

## Overview
Demonstrates delegates and events with a `Person` class that raises events for buying vehicles. The `Main` method subscribes static methods to events and triggers them based on user input.

Files:
- `Program.cs` — contains delegate definition, `Person` class, and main program.

## Run
- Build: `dotnet build`
- Run: `dotnet run --project Day3_03_Delegate\Day3_03_Delegate.csproj`

## Source (`Program.cs`)
```csharp
using System.IO;

namespace Day3_03_Delegate
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Person person = new Person();
            person.Name = "Akshat";

            MyDelegate car = new MyDelegate(CarPrice);
            MyDelegate bike = new MyDelegate(BikePrice);

            person.BuyCar += car;
            person.BuyBike += bike;

            Console.WriteLine("Hello " + person.Name + " , Which vehicle you want to buy...");
            Console.WriteLine("1.Car 2.Bike");

            person.BuyVehicle(Convert.ToInt32(Console.ReadLine()));

            Console.ReadLine();
        }

        public static void CarPrice()
        {
            Console.WriteLine("Price : 95000000");
        }

        public static void BikePrice()
        {
            Console.WriteLine("Price : 300000");
        }
    }

    public delegate void MyDelegate();
    public class Person
    {
        private String _name;

        public String Name
        {
            get { return _name; }
            set { _name = value; }
        }

        public event MyDelegate BuyCar;
        public event MyDelegate BuyBike;

        public void BuyVehicle(int choice)
        {
            switch (choice)
            {
                case 1:
                    BuyBike();
                    break;
                case 2:
                    BuyBike();
                    break;
                default:
                    Console.WriteLine("Please choose valid option");
                    break;
            }
        }

    }
}
```

## Layman explanation & analogy
Imagine a small dealership where a customer tells the salesperson whether they want information about a car or a bike. The salesperson asks a specialist (the event handler) who then announces the price. The delegate is the specialist's script; the event is the salesperson's call to the specialist.

## Suggested improvements
- Fix the bug: `case 1` should call `BuyCar()`.
- Use null-safe invocation `BuyCar?.Invoke()` and `BuyBike?.Invoke()` to avoid exceptions if no subscribers.
- Validate input using `int.TryParse`.
- Consider passing additional data (e.g., selected model) via delegate parameters.

---
