using MathLib;
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

            Maths obj =  new Maths();

            int result = obj.Add(x, y);

            Console.WriteLine(result);
            Console.ReadLine();
        }
    }

   
}
