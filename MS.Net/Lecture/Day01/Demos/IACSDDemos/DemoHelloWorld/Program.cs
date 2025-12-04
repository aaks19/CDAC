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

            int result = Add(x, y);

            Console.WriteLine(result);
            Console.ReadLine();
        }
        private static int Add(int x, int y)
        {
            return x + y;
        }
    }
}
