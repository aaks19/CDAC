namespace HandsOnLab.Logger
{
    public class FileLogger
    {
        private static FileLogger logger = new FileLogger();
        private FileLogger() { }

        public static FileLogger CurrentLogger
        {
            get { return logger; }
        }

        public void Log(string message)
        {

            string filePath = "F:\\CDAC\\MS.Net\\Practice\\LabPractice\\log.txt";
            FileStream fileStream = null;

            if (File.Exists(filePath))
            {
                fileStream = new FileStream(filePath, FileMode.Append, FileAccess.Write);
            }
            else
            {
                fileStream = new FileStream(filePath, FileMode.Create, FileAccess.Write);
            }

            StreamWriter writer = new StreamWriter(fileStream);
            writer.WriteLine("Logged at: " + DateTime.Now.ToString() + " - " + message);

            writer.Close();
            fileStream.Close();
        }
    }
}
