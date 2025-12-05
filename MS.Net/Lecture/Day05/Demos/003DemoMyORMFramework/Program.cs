using _001DemoMyAttributes;
using System.Reflection;

namespace _003DemoMyORMFramework
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter path of assembly:");
            
            //string path = "D:\\IACSD\\IACSDDemos\\002DemoPOCOLib\\bin\\Debug\\net8.0\\002DemoPOCOLib.dll";

            string path = Console.ReadLine();



            Assembly assembly = Assembly.LoadFrom(path);

            Type []allTypes =  assembly.GetTypes();

            string query = "";

            foreach (Type type in allTypes) 
            {
                Attribute[] allAttributesOnType = 
                    type.GetCustomAttributes().ToArray();

                foreach (Attribute attribute in allAttributesOnType)
                {
                    if (attribute is Table)
                    {
                        Table table = (Table)attribute;
                        query = query + "create table " + table.Name;
                        break;
                    }
                }


                //Let us find out column details via properties info

                PropertyInfo[] allProperties = type.GetProperties();

                query = query + " ( ";
                foreach (PropertyInfo property in allProperties)
                {
                    Attribute[]allAttributesOnProperty =  
                    property.GetCustomAttributes().ToArray();

                    

                    foreach (Attribute attr in allAttributesOnProperty)
                    {
                        if (attr is Column)
                        {
                            Column column = (Column)attr;
                            query = query + column.ColumnName + " ";
                            query = query + column.ColumnType + ",";
                        }
                    }
                }

                query = query.TrimEnd(',');
                query = query + ");";
            }

            Console.WriteLine();
            Console.WriteLine(query);
            Console.ReadLine();
            //LoggerLib.FileLogger.CurrentLogger.Log(query);
        }
    }
}
