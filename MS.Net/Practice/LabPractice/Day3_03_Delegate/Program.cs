using System.IO;

namespace Day3_03_Delegate
{
    internal class Program
    {
        static void Main(string[] args)
        {
            //Creates an instance of Person. Required because we need object state (Name) and events (BuyCar, BuyBike)
            Person person = new Person();
            person.Name = "Akshat";

            //Creates delegate object linked to CarPrice & BikePrice method.
            MyDelegate car = new MyDelegate(CarPrice);
            MyDelegate bike = new MyDelegate(BikePrice);

            //Append car delegate to BuyCar event.
            person.BuyCar += car;
            person.BuyBike += bike;


            Console.WriteLine("Hello " + person.Name + " , Which vehicle you want to buy...");
            Console.WriteLine("1.Car 2.Bike");

            // Take User choice and pass it to BuyVehicle
            person.BuyVehicle(Convert.ToInt32(Console.ReadLine()));

            Console.ReadLine();
        }


        //Static method because delegate calls it directly without object instance
        public static void CarPrice()
        {
            Console.WriteLine("Price : 95000000");
        }


        //Static method because delegate calls it directly without object instance
        public static void BikePrice()
        {
            Console.WriteLine("Price : 300000");
        }
    }

    //Delegate definition
    public delegate void MyDelegate();
    public class Person
    {
        private String _name;

        public String Name
        {
            get { return _name; }
            set { _name = value; }
        }

        //Event declaration
        public event MyDelegate BuyCar;
        public event MyDelegate BuyBike;

        //Method that trigger event based on choice
        public void BuyVehicle(int choice)
        {
            //if(choice == 1)
            //{
            //    //Event invocation 
            //    BuyCar();
            //}
            //else
            //{
            //    //Event invocation 
            //    BuyBike();
            //}

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
