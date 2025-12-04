namespace Day3_01
{
    internal class Program
    {
        static void Main(string[] args)
        {

            // Interface Demo...

            Message obj = new Message();
            Console.WriteLine(obj.sayBye("bye bye"));

            Message obj2 = new Message();
            Console.WriteLine(obj2.sayHello("Hello! It's Akshat"));

            Console.ReadLine();
        }
    }

    public interface First
    {
        string sayHi(string msg);
        string sayHello(string msg);
    }

    public interface Second
    {
        string sayHi(string msg);
        string sayBye(string msg);
    }

    public class Message : First, Second
    {
        public string sayBye(string msg)
        {
            return msg;
        }

        public string sayHello(string msg)
        {
            return msg;
        }

        public string sayHi(string msg)
        {
            return msg;
        }
    }
}
