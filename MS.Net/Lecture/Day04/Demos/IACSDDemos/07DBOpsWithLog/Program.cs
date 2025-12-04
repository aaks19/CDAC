using DBOpsLib;
using LoggerLib;

namespace _07DBOpsWithLog
{
    internal class Program
    {
        static void Main(string[] args)
        {
            SQLServer obj = new SQLServer();
            MyDBDelegate pointer = new MyDBDelegate(FileLogger.CurrentLogger.Log);
            obj.OnInsert += pointer;

            obj.Insert();
        }
    }
}
