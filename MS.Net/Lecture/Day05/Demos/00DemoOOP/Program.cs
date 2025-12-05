namespace _00DemoOOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            ReportFactory reportFactory = new ReportFactory();

            Console.WriteLine("1: PDF, 2: DOCX, 3: TXT, 4: PPTX, 5: XML");
            int choice = Convert.ToInt32(Console.ReadLine());


            Report report = reportFactory.GetReport(choice);
            report.GenerateReport();

            Console.ReadLine();
        }

    }

    public class ReportFactory
    {
        public Report GetReport(int choice)
        {
            if (choice == 1)
            {
                return new PDF();
            }
            else if (choice == 2)
            {
                return new DOCX();
            }
            else if (choice == 3)
            {
                return new TXTReport();
            }
            else if(choice == 4)
            {
                return new PPTX();
            }
            else
            {
                return new XML();
            }
        }
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

    //Dev 1
    public class PDF : Report
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

    //Dev 2
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

    //Dev 3
    public class TXTReport : Report
    {
        protected override void Create()
        {
            Console.WriteLine("TXT Created");
        }

        protected override void Parse()
        {
            Console.WriteLine("TXT Parsed");
        }

        protected override void Save()
        {
            Console.WriteLine("TXT Saved");
        }

        protected override void Validate()
        {
            Console.WriteLine("TXT Validated");
        }
    }


    public abstract class SpecialReport: Report
    {
        protected abstract void ReValidate();
        public override void GenerateReport()
        {
            Create();
            Validate();
            ReValidate();
            Parse();
            Save();
        }
    }


    //Dev 4

    public class PPTX : SpecialReport
    {
        protected override void Create()
        {
            Console.WriteLine("PPT Created");
        }

        protected override void Parse()
        {
            Console.WriteLine("PPT Parsed");
        }

        protected override void Save()
        {
            Console.WriteLine("PPT Saved");
        }

        protected override void Validate()
        {
            Console.WriteLine("PPT Valdiated");
        }
        protected override void ReValidate()
        {
            Console.WriteLine("PPT Re-Valdiated");
        }

     

    }


    //Dev 5

    public class XML : SpecialReport
    {
        protected override void Create()
        {
            Console.WriteLine("XML Created");
        }

        protected override void Parse()
        {
            Console.WriteLine("XML Parsed");
        }

        protected override void Save()
        {
            Console.WriteLine("XML Saved");
        }

        protected override void Validate()
        {
            Console.WriteLine("XML Valdiated");
        }
        protected override void ReValidate()
        {
            Console.WriteLine("XML Re-Valdiated");
        }

    }


}
