namespace DBOpsLib
{
    public delegate void MyDBDelegate(string message);
    public class SQLServer
    {
        public event MyDBDelegate OnInsert;
        public event MyDBDelegate OnUpdate;
        public void Insert()
        {
            Console.WriteLine("SQL Server Insert Done!");
            OnInsert("Audited : Insert in SQLServer");
        }
        public void Update()
        {
            Console.WriteLine("SQL Server Update Done!");
            OnUpdate("Audited : Update in SQLServer");
        }
    }
}
