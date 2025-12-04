namespace Day3_03._1_Delegate
{
    internal class Program
    {

        #region Problem Statement
        //Shopping Cart Discount System:
        //   -> A customer chooses between different types of discounts(Summer Sale, Festival Sale).

        //   -> Apply the selected discount using delegates and events.

        #endregion

        static void Main(string[] args)
        {
            Customer customer = new Customer();
            customer.CustomerName = "Akshat";

            myDiscountDelegate summerDiscount = new myDiscountDelegate(SummerSale);
            myDiscountDelegate winterDiscount = new myDiscountDelegate(WinterSale);

            customer.ApplySummerDiscount += summerDiscount;
            customer.ApplyWinterDiscount += winterDiscount;

            Console.WriteLine("Hello, " + customer.CustomerName + " , please choose form this...");
            Console.WriteLine("1. Summer Sale   2. Winter Sale");

            customer.ApplyDiscount(Convert.ToInt32(Console.ReadLine()));

            Console.ReadLine();
        }

        public static void SummerSale()
        {
            Console.WriteLine("Summer Sale : 80% Discount");
        }

        public static void WinterSale()
        {
            Console.WriteLine("Winter Sale : 75% Discount");
        }
    }

    public delegate void myDiscountDelegate();

    public class Customer
    {
        private string CustName;

        public string CustomerName
        {
            get { return CustName; }
            set { CustName = value; }
        }

        public event myDiscountDelegate ApplySummerDiscount;
        public event myDiscountDelegate ApplyWinterDiscount;

        public void ApplyDiscount(int choice)
        {
            switch (choice)
            {
                case 1: 
                    ApplySummerDiscount();
                    break;

                case 2:
                    ApplyWinterDiscount();
                    break;
            }
                
        }

    }
}
