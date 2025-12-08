namespace _03DemoOOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region Property Concept

            //Emp emp = new Emp();
            //emp.Name = "Test";
            //Console.WriteLine(emp.Name);

            #endregion

            Logger.CurrentLogger.Log("Main Called.");

            SQLServer obj = new SQLServer();
            obj.Insert();
            obj.Update();

            Console.ReadLine();

        }
    }

    public class SQLServer
    {
        public void Insert()
        {
            Console.WriteLine("SQL Server Insert Done!");
            Logger.CurrentLogger.Log("Audited : Insert in SQLServer");
        }
        public void Update()
        {
            Console.WriteLine("SQL Server Update Done!");
            Logger.CurrentLogger.Log("Audited : Update in SQLServer");
        }
    }

    public class Logger
    {
        private static Logger logger = new Logger();
        private Logger()
        {
            Console.WriteLine("Logger Object Created..");
        }

        public static Logger CurrentLogger
        {
            get { return logger; }
        }
        public void Log(string message)
        {
            Console.WriteLine("Logged at " + DateTime.Now.ToString() + " - " + message);
        }
    }



    public class Emp
    {

        private int _No;
        private string _Name;
        private string _Address;

        public string Address
        {
            get { return _Address; }
            set { _Address = value; }
        }

        public string Name
        {
            get { return _Name; }
            set { _Name = value; }
        }

        public int No
        {
            get { return _No; }
            set { _No = value; }
        }



        //private string _Name;

        //public string Name
        //{
        //    get 
        //    {
        //        return _Name; 
        //    }
        //    set 
        //    {
        //        _Name = value; 
        //    }
        //}



        //private string _Name;

        //public void Set_Name(string value)
        //{
        //    _Name = value;
        //}

        //public string Get_Name()
        //{
        //    return _Name;
        //}
    }

}
