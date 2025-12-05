namespace _04DemoOOP
{
    public delegate bool MyDelegate(int i);

    internal class Program
    {
        static void Main(string[] args)
        {
            #region Normal Check Function Call
            //bool result = Check(200);
            //Console.WriteLine(result);

            #endregion

            #region Check Function Call Via MyDelegate
            //MyDelegate pointer = new MyDelegate(Check);
            //bool result = pointer(200);
            //Console.WriteLine(result);


            #endregion

            #region Handling Own Events , Delegates
            //Button button1 = new Button();
            Person person = new Person();

            //button1.Text  = "Click Me";
            person.Name = "Prince";

            //EventHandler pointer = new EventHandler(SayHi)
            MyFriendDelegate Kaustubh =
                new MyFriendDelegate(HappyParty);

            MyFriendDelegate Chetan =
                new MyFriendDelegate(SadParty);

            //button1.Click += pointer;
            person.ProposalAccepted += Kaustubh;
            person.ProposalRejected += Chetan;

            Console.WriteLine("Bolo");
            string message = Console.ReadLine();

            person.Propose(message);
            //this is as good as button is clicked!

            #endregion

            Console.ReadLine();
        }

        //public static void SayHi(Object sender, EventArgs e)
        //{
        //    //MessageBox.Show("Hi)
        //}
        public static void HappyParty()
        {
            Console.WriteLine("PARTY at JW!!!!!  :)  ");
        }

        public static void SadParty()
        {
            Console.WriteLine("PARTY at Pyasaa :( :( :(");
        }


        public static bool Check(int i)
        {
            return (i > 10);
        }
    }

    public delegate void MyFriendDelegate();
    public delegate void EventHandler(object obj, EventArgs e);

    public class Person //public class Button
    {
        private string _Name; //private string _Text
        public string Name    //public string Text
        {
            get { return _Name; }
            set { _Name = value; }
        }


        //public event EventHandler Click;
        public event MyFriendDelegate ProposalRejected;

        public event MyFriendDelegate ProposalAccepted;

        public void Propose(string message)
        {
            if (message.Contains("iacsd")) //is button clicked?
            {
                ProposalRejected();  
                //Click(this, new EventArgs())
                //Event raised //CLR is notified
            }
            else
            {
                ProposalAccepted();
                //Event raised //CLR is notified
            }
        }

    }
}
