using DBOpsLib;
namespace Day4_01_SQLServer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            SQLServer server = new SQLServer();
            myDBDelegate pointer = new myDBDelegate(sendEmail);
            server.OnInsert += pointer;
            server.Insert();
        }

        public static void sendEmail(string msg)
        {
            Console.WriteLine("Email sent with message: " + msg);
        }
    }
}
