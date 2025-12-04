namespace DBOpsLib
{
    public delegate void myDBDelegate(string msg);
    public class SQLServer
    {
        public event myDBDelegate OnInsert;
        public event myDBDelegate OnUpdate;
        public void Insert()
        {
            Console.WriteLine("Inserted into DB");
            OnInsert("Audited : insert in sql server");
        }

        public void Update()
        {
            Console.WriteLine("Updated DB");
            OnUpdate("Audited : update in sql server");
        }
    }
}
