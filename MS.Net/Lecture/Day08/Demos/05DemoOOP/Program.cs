using DBOpsLib;
namespace _05DemoOOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            SQLServer obj = new SQLServer();
            MyDBDelegate pointer = new MyDBDelegate(SendEMail);
            obj.OnInsert += pointer;

            obj.Insert();
        }

        public static void SendEMail(string message)
        {
            Console.WriteLine("EMail sent with detail like: ");
            Console.WriteLine(message);
        }
    }

   
}
