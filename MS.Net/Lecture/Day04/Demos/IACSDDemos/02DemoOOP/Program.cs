namespace _02DemoOOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            IX obj = new Maths();
            Console.WriteLine(obj.Add(10, 20));

            IY obj2 = new Maths();
            Console.WriteLine(obj2.Add(10,20));

            Console.ReadLine();
        }
    }

    public interface IX
    {
        int Add (int x, int y);
        int Sub(int x, int y);
    }

    public interface IY
    {
        int Add(int x, int y);
        int Mult(int x, int y);
    }


    public class Maths : IX, IY
    {
        int IX.Add(int x, int y)
        {
            return x + y;
        }

        int IY.Add(int x, int y)
        {
            return x + y + 100;
        }

        int IY.Mult(int x, int y)
        {
            return x * y;
        }

        int IX.Sub(int x, int y)
        {
            return x - y;
        }
    }


}
