namespace _01DemoOOP
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("1: Eng, 2: Hindi, 3: French");
            int choice = Convert.ToInt32(Console.ReadLine());

            SpellCheckerFactory spellCheckerFactory = new SpellCheckerFactory();
            ISpellChecker spellChecker =
                spellCheckerFactory.GetSpellChecker(choice);

            //Editor editor1 = new Editor();
            //editor1.SpellCheck("abc");

            Editor editor2 = new Editor(spellChecker);
            editor2.SpellCheck("abc");


            Console.ReadLine();
        }
    }

    public class Editor
    {
        ISpellChecker _spellchecker = null;

        public Editor()
        {
            _spellchecker = new EnglishSpellChecker();
        }

        public Editor(ISpellChecker spellchecker)
        {
            _spellchecker = spellchecker;
        }


        public void Copy() { Console.WriteLine("Data Copy Done!"); }
        public void Paste() { Console.WriteLine("Data Paste Done!"); }

        public void SpellCheck(string word)
        {
            _spellchecker.DoCheck(word);
        }

    }

    public interface ISpellChecker
    {
        void DoCheck(string word);
    }

    public class EnglishSpellChecker : ISpellChecker
    {
        public void DoCheck(string word)
        {
            //word check logic
            Console.WriteLine("Valid English Word");
        }
    }

    public class FrenchSpellChecker : ISpellChecker 
    {
        public void DoCheck(string word)
        {
            //word check logic
            Console.WriteLine("Valid French Word");
        }
    }

    public class HindiSpellChecker : ISpellChecker
    {
        public void DoCheck(string word)
        {
            //word check logic
            Console.WriteLine("Valid Hindi Word");
        }
    }


    //Inversion Of Control [IOC]
    public class SpellCheckerFactory 
    {
        public ISpellChecker GetSpellChecker(int choice)
        {
            if (choice == 1)
            {
                return new EnglishSpellChecker();
            }
            else if (choice ==2 )
            {
                return new HindiSpellChecker();
            }
            else
            {
                return new FrenchSpellChecker();
            }
        }
    }
}
