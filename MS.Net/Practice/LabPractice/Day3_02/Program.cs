namespace Day3_02
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Logger.CurrentLogger.Log("Main");
            
        }
    }

    public class SqlServer
    {
        public void Insert()
        {
            Console.WriteLine("Inserted in sql server");
        }

        public void Delete()
        {
            Console.WriteLine("Deleted from sql server");
        }
    }

    public class Logger
    {
        public static Logger logger = new Logger();

        private Logger()
        {
            Console.WriteLine("Logger is created");
        }

        public static Logger CurrentLogger
        {
            get { return logger; }
        }
        public void Log(String msg)
        {
            Console.WriteLine("Logged at " + DateTime.Now.ToString() + " -> " + msg);
        }

    }
}
