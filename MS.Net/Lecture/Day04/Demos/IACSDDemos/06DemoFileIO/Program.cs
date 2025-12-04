using System.Runtime.Serialization.Formatters.Binary;

namespace _06DemoFileIO
{
    internal class Program
    {
        static void Main(string[] args)
         {

            #region Simple File Write
            //FileStream stream = null;
            //string filePath = "D:\\IACSD\\IACSDDemos\\somedata.txt";

            //if(File.Exists(filePath))
            //{

            //    stream = new FileStream(filePath, FileMode.Append, FileAccess.Write);
            //}
            //else
            //{
            //    stream = new FileStream(filePath, FileMode.Create, FileAccess.Write);
            //}

            //Console.WriteLine(  "Enter something: ");
            //string somedata = Console.ReadLine();

            //StreamWriter writer = new StreamWriter(stream);

            //writer.WriteLine( somedata );

            //writer.Close();
            //stream.Close();

            //Console.WriteLine("Done with writing!");
            //Console.ReadLine(); 
            #endregion

            #region Simple File Read

            //FileStream stream = null;
            //string filePath = "D:\\IACSD\\IACSDDemos\\somedata.txt";

            //if (File.Exists(filePath))
            //{

            //    stream = new FileStream(filePath, FileMode.Open, FileAccess.Read);
            //    StreamReader reader = new StreamReader(stream);
            //    string allData =  reader.ReadToEnd();
            //    Console.WriteLine(allData);

            //    reader.Close();
            //    stream.Close();
            //}
            //else
            //{
            //    Console.WriteLine("File does not exist!");
            //}

            //Console.WriteLine("Done with reading!");
            //Console.ReadLine();

            #endregion

            #region Simple Object Write
            //FileStream stream = null;
            //string filePath = "D:\\IACSD\\IACSDDemos\\SomeDataObjectFromUser.txt";

            //if (File.Exists(filePath))
            //{

            //    stream = new FileStream(filePath, FileMode.Append, FileAccess.Write);
            //}
            //else
            //{
            //    stream = new FileStream(filePath, FileMode.Create, FileAccess.Write);
            //}

            //Emp emp = new Emp();

            //Console.WriteLine(  "Enter No: ");
            //emp.No = Convert.ToInt32(Console.ReadLine());

            //Console.WriteLine("Enter Name:");
            //emp.Name = Console.ReadLine();

            //Console.WriteLine("Enter Address");
            //emp.Address = Console.ReadLine();

            //BinaryFormatter writer = new  BinaryFormatter();

            //writer.Serialize(stream, emp);

            //writer = null;
            //stream.Close();

            //Console.WriteLine("Done with writing!");
            //Console.ReadLine();
            #endregion

            #region Simple Object Read
            //FileStream stream = null;
            //string filePath = "D:\\IACSD\\IACSDDemos\\SomeDataObjectFromUser.txt";

            //if (File.Exists(filePath))
            //{
            //    stream = new FileStream(filePath, FileMode.Open, FileAccess.Read);
            //    BinaryFormatter reader = new BinaryFormatter();
            //    object obj =  reader.Deserialize(stream);

            //    if (obj is Emp)
            //    {
            //        Emp emp = (Emp)obj;
            //        Console.WriteLine(emp.No);
            //        Console.WriteLine(  emp.Name);
            //        Console.WriteLine(  emp.Address);
            //    }
            //    else
            //    {
            //        Console.WriteLine("Unknown type of data found!");
            //    }
                
            //}
            //else
            //{
            //    Console.WriteLine(  "File does not exist!");
            //}

            //stream.Close();

            //Console.WriteLine("Done with reading!");
            //Console.ReadLine();
            #endregion
        }
    }

    [Serializable]
    public class Emp
    {
        private int _No;
        private string _Name;
        private string _Address;

        [NonSerialized]
        private string _Password = "abc#123";

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

    }
}




