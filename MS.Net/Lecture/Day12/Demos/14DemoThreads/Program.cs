using System.Diagnostics;

namespace _14DemoThreads
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region Simple Single Threaded Code
            //Stopwatch stopwatch = new Stopwatch();
            //stopwatch.Start();

            //for (int i = 0; i < 10; i++)
            //{
            //    DoSomething();
            //}

            //stopwatch.Stop();
            //Console.WriteLine("Time Taken = {0}",stopwatch.ElapsedMilliseconds ); 
            #endregion

            //#region Simple Multi Threaded Code


            //Console.WriteLine("Main Thread ID = {0}", Thread.CurrentThread.ManagedThreadId);
            //List<Thread> threads = new List<Thread>();

            //Stopwatch stopwatch = new Stopwatch();
            //stopwatch.Start();


            //for (int i = 0; i < 10; i++)
            //{
            //    //ThreadStart pointer = new ThreadStart(DoSomething);
            //    //Thread thread = new Thread(pointer);

            //    Thread thread = new Thread(DoSomething);
            //    thread.Start();
            //    threads.Add(thread);
            //}
            //foreach (Thread thread in threads)
            //{
            //    thread.Join();
            //}


            //stopwatch.Stop();
            //Console.WriteLine("Time Taken = {0}", stopwatch.ElapsedMilliseconds);

            //#endregion

            //#region Explicit Parallel Programming :  (TPL) Code 

            ////List<Task> tasks = new List<Task>();

            ////Stopwatch stopwatch = new Stopwatch();
            ////stopwatch.Start();


            ////for (int i = 0; i < 10; i++)
            ////{
            ////    Task task = new Task(DoSomething);
            ////    task.Start();
            ////    tasks.Add(task);
            ////}

            ////Task.WaitAll(tasks.ToArray());

            ////stopwatch.Stop();
            ////Console.WriteLine("Time Taken = {0}", stopwatch.ElapsedMilliseconds);


            //#endregion

            //#region Implicit Parallel Programming 

            //string[] allFiles = Directory.GetFiles("C:\\Windows\\System32\\");

            ////Console.WriteLine(allFiles.Count());

            //Stopwatch stopwatch = new Stopwatch();
            //stopwatch.Start();

            //Console.WriteLine("------------------------------------");

            //#region ForEach Vs Parallel For Each
            //#region For Each
            ////foreach (string file in allFiles) //4592 times the loop is running
            ////{
            ////    if (file.Contains(".exe"))
            ////    {
            ////        Console.WriteLine(file);
            ////    }
            ////}
            //#endregion

            //#region Parallel ForEach

            ////Parallel.ForEach(allFiles, (file) => {
            ////    if (file.Contains(".exe"))
            ////    {
            ////        Console.WriteLine(file);
            ////    }
            ////});
            //#endregion

            //#endregion

            //var emps = new List<Emp>()
            //{
            //  new Emp(){ No = 1, Name = "Hardik", Address = "Ghaziabad" },
            //  new Emp(){ No = 2, Name = "Adarsh", Address = "Patna" },
            //  new Emp(){ No = 3, Name = "Rahul", Address = "Jaipur" },
            //  new Emp(){ No = 4, Name = "Atul", Address = "Gorakhpur" },
            //  new Emp(){ No = 5, Name = "Nilay", Address = "Raipur" },
            //  new Emp(){ No = 6, Name = "Bharat", Address = "Pune" },
            //  new Emp(){ No = 7, Name = "Abhishek", Address = "Kolhapur" },
            //  new Emp(){ No = 8, Name = "Abhay", Address = "Kolhapur" }
            //};

            //Console.WriteLine("Enter Character to find address:");
            //string filter = Console.ReadLine();

            //#region Simple For Each  : Time : 4000 + MS
            ////List<Emp> result = new List<Emp>();
            ////foreach (Emp emp in emps)
            ////{
            ////    if (emp.Address.ToLower().Contains(filter.ToLower()))
            ////    {
            ////        result.Add(emp);
            ////    }
            ////}
            //#endregion

            //#region Simple LINQ Instead of For Each : Time : 2000 + MS
            ////var result = (from emp in emps
            ////              where emp.Address.ToLower().Contains(filter.ToLower())
            ////              select emp).ToList();

            //#endregion

            //#region PLINQ Instead of LINQ : Time : 1900+ MS
            //var result = (from emp in emps.AsParallel()
            //              where emp.Address.ToLower().Contains(filter.ToLower())
            //              select emp).ToList();

            //#endregion

            //Console.WriteLine("--------------------------");
            //Console.WriteLine("Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (var emp in result)
            //{
            //    Console.WriteLine(emp.ToString());
            //}


            //Console.WriteLine("------------------------------------");
            //stopwatch.Stop();
            //Console.WriteLine("Time Taken = {0}", stopwatch.ElapsedMilliseconds);
            //#endregion

            Console.ReadLine();
        }

        public static void DoSomething()
        {
            Console.WriteLine(Thread.CurrentThread.ManagedThreadId);
            for (int i = 0; i < 100000; i++)
            {
                for (int j = 0; j < 10000; j++)
                {
                    //Do nothing 
                }
            }
        }
    }
    public class Emp
    {
        private int _No;
        private string _Name;
        private string _Address;

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

        public override string ToString()
        {
            return string.Format("No = {0}, Name = {1}, Address = {2}", No, Name, Address);
        }

    }

}
