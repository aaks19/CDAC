namespace LabProject.Logger
{

    public class FileLogger
    {

        private static FileLogger fileLogger = new FileLogger();
        private FileLogger() { }

        public static FileLogger currentLogger
        {
            get { return fileLogger; }
        }
        public void Log(string message)
        {
            string path = "F:\\CDAC\\MS.Net\\Practice\\LabPractice\\LabProject\\log.txt";
            FileStream stream = null;
            if (File.Exists(path))
            {
                stream = new FileStream(path, FileMode.Append, FileAccess.Write);
            }
            else
            {
                stream = new FileStream(path, FileMode.Create, FileAccess.Write);
            }

            StreamWriter writer = new StreamWriter(stream);
            writer.WriteLine("Logged at " + DateTime.Now.ToString() + " - " + message);

            writer.Close();
            stream.Close();
        }
    }
}
