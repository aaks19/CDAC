namespace HandsOnLab.Logger
{
    public class FileLogger
    {
        private static FileLogger logger = new FileLogger();
        private FileLogger() { }
        public static FileLogger CurrentLogger { get { return logger; } }
        public void Log(string message)
        {
            string path = "C:\\MVCLog\\log.txt";
            FileStream fileStream = null;
            if (File.Exists(path))
            {
                fileStream = new FileStream(path, FileMode.Append, FileAccess.Write);
            }
            else
            {
                fileStream = new FileStream(path, FileMode.Create, FileAccess.Write);
            }
            StreamWriter writer = new StreamWriter(fileStream);
            writer.WriteLine("Logged at: " +
                              DateTime.Now.ToString() +
                              " - " +
                              message);
            writer.Close(); fileStream.Close();
        }
    }
}
