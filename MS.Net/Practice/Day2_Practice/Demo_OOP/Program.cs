namespace Demo_OOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            ReportGenerator reportGenerator = new ReportGenerator();
            Report report = new PdfReport();
            report.GenerateReport();
        }

        public class ReportGenerator
        {
            Report report = new PdfReport();
        }

        public abstract class Report
        {
            protected abstract void Create();
            protected abstract void Validate();
            protected abstract void Parse();
            protected abstract void Save();

            public virtual void GenerateReport()
            {
                Create();
                Validate();
                Parse();
                Save();
            }
        }

        public class PdfReport : Report
        {
            protected override void Create()
            {
                Console.WriteLine("PDF Created");
            }

            protected override void Validate()
            {
                Console.WriteLine("PDF Validated");
            }

            protected override void Parse()
            {
                Console.WriteLine("PDF Parsed");
            }

            protected override void Save()
            {
                Console.WriteLine("PDF Saved");
            }
        }

        public class DOCX : Report
        {
            protected override void Create()
            {
                Console.WriteLine("DOCX Created");
            }
            protected override void Validate()
            {
                Console.WriteLine("DOCX Validated");
            }
            protected override void Parse()
            {
                Console.WriteLine("DOCX Parsed");
            }
            protected override void Save()
            {
                Console.WriteLine("DOCX Saved");
            }
        }

        
    }
}
