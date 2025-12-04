namespace Day4_02_FileIO
{
    internal class Program
    {
        static void Main(string[] args)
        {
            FileStream stream = null;
            string path = "F:\\CDAC\\MS.Net\\Practice\\LabPractice\\myText";
            //string file_name = "myText";
            if (File.Exists(path))
            {
                //if file exists open the file and write in the file
                stream = new FileStream(path, FileMode.Open, FileAccess.Write);
            }
            else
            {
                // Create file if not exist and then write in the file
                stream = new FileStream(path, FileMode.Create, FileAccess.Write);
            }

            Console.WriteLine("Start Writing");
            string data = Console.ReadLine();

            StreamWriter writer = new StreamWriter(stream);
            writer.WriteLine(data);

            writer.Close();
            stream.Close();
            Console.WriteLine("Writing Completed");
        }
    }
}
