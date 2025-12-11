using System.Collections;
using System.Diagnostics;
using System.Linq.Expressions;
using System.Reflection.PortableExecutable;

namespace _10DemoFeatures
{
    public delegate bool MyDelegate(int i);

    //public delegate P MyDelegate<P,Q>(Q i);
    internal class Program
    {
        static void Main(string[] args)
        {
            #region Partial Class
            //Maths obj = new Maths();
            //int result1 = obj.Add(10, 20);
            //int result2 = obj.Sub(10, 20);
            //Console.WriteLine(result1);
            //Console.WriteLine(result2);
            #endregion

            #region Nullable Type

            ////Nullable<int> i = null; //this line and next line are same
            //int? i = null;

            //Console.WriteLine(i.HasValue);

            //object obj = null;
            #endregion

            #region Calling Non Generic Logic
            //Maths obj = new Maths();
            //int p = 100;
            //int q = 200;

            ////string format = "before swap p = {0}, q= {1}";
            ////string message = string.Format(format, p, q);
            ////Console.WriteLine(message);

            //Console.WriteLine("before swap p = {0}, q= {1}", p, q);
            //obj.Swap(ref p, ref q);
            //Console.WriteLine("after swap p = {0}, q= {1}", p, q);


            //Maths obj1 = new Maths();
            //string p1 = "abc";
            //string q1 = "pqr";

            //Console.WriteLine("before swap p1 = {0}, q1= {1}", p1, q1);
            //obj1.Swap(ref p1, ref q1);
            //Console.WriteLine("after swap p1 = {0}, q1= {1}", p1, q1);
            #endregion

            #region Generics Demo - 1

            //Maths<int> obj = new Maths<int>();
            //int p = 100;
            //int q = 200;

            //Console.WriteLine("before swap p = {0}, q= {1}", p, q);
            //obj.Swap(ref p, ref q);
            //Console.WriteLine("after swap p = {0}, q= {1}", p, q);


            //Maths<string> obj1 = new Maths<string>();
            //string p1 = "abc";
            //string q1 = "pqr";

            //Console.WriteLine("before swap p1 = {0}, q1= {1}", p1, q1);
            //obj1.Swap(ref p1, ref q1);
            //Console.WriteLine("after swap p1 = {0}, q1= {1}", p1, q1);
            #endregion

            #region Generics Demo - 2

            #region Problem with Generic Class + NonGeneric Method
            //Maths<double> m = new Maths<double>();
            //int result =   m.Add(10, 20);
            //Console.WriteLine(result);
            #endregion

            #region Solution to the above problem

            //Maths obj = new Maths();
            //int result = obj.Add(10, 20);
            //Console.WriteLine(result);

            //int p = 100;
            //int q = 200;

            //Console.WriteLine("before swap p = {0}, q= {1}", p, q);
            //obj.Swap<int>(ref p, ref q);
            //Console.WriteLine("after swap p = {0}, q= {1}", p, q);

            //string p1 = "abc";
            //string q1 = "pqr";

            //Console.WriteLine("before swap p1 = {0}, q1= {1}", p1, q1);
            //obj.Swap<string>(ref p1, ref q1);
            //Console.WriteLine("after swap p1 = {0}, q1= {1}", p1, q1);

            #endregion

            #endregion

            #region Generics Demo - 3
            //SpecialMaths obj = new SpecialMaths();
            #endregion

            #region Generics Demo - 4
            //SpecialMaths<int, short, bool, string> obj =
            //    new SpecialMaths<int, short, bool, string>();

            //int result=    obj.NonsenseMethod(10, 1, true, "abcd");
            //Console.WriteLine(result);


            //int p = 100;
            //int q = 200;

            //Console.WriteLine("before swap p = {0}, q= {1}", p, q);
            //obj.Swap(ref p, ref q);
            //Console.WriteLine("after swap p = {0}, q= {1}", p, q);
            #endregion

            #region Using Delegate , Generic Delegate, Func / Action
            ////MyDelegate pointer = new MyDelegate(Check);

            ////MyDelegate<bool, int> pointer =
            ////    new MyDelegate<bool, int>(Check);

            ////////Action ptr = new Action(); //Explore this on your own

            //Func<int, bool> pointer = 
            //    new Func<int, bool>(Check);


            //bool result = pointer(20);
            ////bool result = Check(20);
            //Console.WriteLine(result);
            #endregion

            #region Collections

            #region Int Array
            //int[]arr = new int[] { 1, 2, 3 };

            ////int[] arr = new int[3];
            ////arr[0] = 100;
            ////arr[1] = 200;
            ////arr[2] = 300;

            //foreach (int i in arr)
            //{
            //    Console.WriteLine(i);
            //}


            #endregion

            #region Emp Objects
            Emp emp1 = new Emp();
            emp1.No = 1;
            emp1.Name = "Mahesh";
            emp1.Address = "Pune";

            Emp emp2 = new Emp();
            emp2.No = 2;
            emp2.Name = "Nilesh";
            emp2.Address = "Panji";

            Emp emp3 = new Emp();
            emp3.No = 3;
            emp3.Name = "Sujit";
            emp3.Address = "Chennai";
            #endregion

            #region Emp Array
            ////Emp[] arr = new Emp[3];
            ////arr[0] = emp1;
            ////arr[1] = emp2;
            ////arr[2] = emp3;

            //Emp[] arr = new Emp[] { emp1, emp2, emp3 };

            //foreach (Emp emp in arr)
            //{
            //    Console.WriteLine(emp.No + emp.Name+ emp.Address);
            //}
            #endregion

            #region Object Array
            //object[] arr = new object[] { 100, emp1, "abcd" };

            //object[] arr = new object[4];
            //arr[0] = 100;
            //arr[1] = emp1;
            //arr[2] = "abcd";
            //arr[3] = true;

            //foreach (object obj in arr)
            //{
            //    if (obj is int)
            //    {
            //        int i = Convert.ToInt32(obj);
            //        Console.WriteLine(i);
            //    }
            //    else if (obj is string)
            //    {
            //        string s = obj.ToString();
            //        Console.WriteLine(s);
            //    }
            //    else if (obj is Emp)
            //    {
            //        Emp emp = (Emp)obj;
            //        Console.WriteLine("Hi " + emp.Name + " from " + emp.Address);
            //    }
            //    else
            //    {
            //        Console.WriteLine("Unknown data!");
            //    }
            //}
            #endregion

            #region ArrayList

            //ArrayList arr = new ArrayList();
            //arr.Add(100);
            //arr.Add(emp1);
            //arr.Add("abcd");
            //arr.Add(true);
            //arr.Add(emp2);
            //arr.Add(emp3);


            //foreach (object obj in arr)
            //{
            //    if (obj is int)
            //    {
            //        int i = Convert.ToInt32(obj);
            //        Console.WriteLine(i);
            //    }
            //    else if (obj is string)
            //    {
            //        string s = obj.ToString();
            //        Console.WriteLine(s);
            //    }
            //    else if (obj is Emp)
            //    {
            //        Emp emp = (Emp)obj;
            //        Console.WriteLine("Hi " + emp.Name + " from " + emp.Address);
            //    }
            //    else
            //    {
            //        Console.WriteLine("Unknown data!");
            //    }
            //}
            #endregion

            #region HashTable

            //Hashtable arr = new Hashtable();
            //arr.Add(emp1.No, emp1);
            //arr.Add(emp2.No, emp2);
            //arr.Add(emp3.No, emp3);
            ////arr.Add(3, "abcd");

            //Console.WriteLine("Enter No to search Emp from Hashtable: ");
            //int No = Convert.ToInt32(Console.ReadLine());

            //object value = arr[No];

            //if (value !=null)
            //{
            //    if (value is Emp)
            //    {
            //        Emp emp = (Emp)value;
            //        Console.WriteLine("Hello " + emp.Name);
            //    }
            //}
            //else
            //{
            //    Console.WriteLine("No Item Found!");
            //}

            #endregion

            #region List<Emp>
            //List<Emp> arr = new List<Emp>();
            //arr.Add(emp1);
            //arr.Add(emp2);
            //arr.Add(emp3);
            ////arr.Add(1234); //this will throw an err!

            //foreach (Emp emp in arr)
            //{
            //    Console.WriteLine(emp.Name + " - " + emp.Address);
            //}
            #endregion

            #region Stack<Emp>
            //Stack<Emp> arr = new Stack<Emp>();
            //arr.Push(emp1);
            //arr.Push(emp2);
            //arr.Push(emp3);

            //foreach (Emp emp in arr)
            //{
            //    Console.WriteLine(emp.Name + " - " + emp.Address);
            //}

            #endregion

            #region Queue<Emp>
            //Queue<Emp> arr = new Queue<Emp>();
            //arr.Enqueue(emp1);
            //arr.Enqueue(emp2);
            //arr.Enqueue(emp3);

            //foreach (Emp emp in arr)
            //{
            //    Console.WriteLine(emp.Name + " - " + emp.Address);
            //}
            #endregion

            #region Dictionary
            //Dictionary<int, Emp> arr = new Dictionary<int, Emp>();
            //arr.Add(emp1.No, emp1);
            //arr.Add(emp2.No, emp2);
            //arr.Add(emp3.No, emp3);

            //Console.WriteLine("Enter No to search Emp from Hashtable: ");
            //int No = Convert.ToInt32(Console.ReadLine());

            //Emp emp = arr[No];

            //if (emp != null)
            //{
            //   Console.WriteLine("Hello " + emp.Name);
            //}
            //else
            //{
            //    Console.WriteLine("No Item Found!");
            //}
            #endregion

            #endregion

            #region Implicit Type

            // var v = 100; //this line is as good as :  int v = 100;
            // v = "jjj";

            //var v = new Emp(); //Emp v = new    Emp();
            //v.No = 199;
            //v.Name = "abcd";

            //Console.WriteLine(v.Name);

            #endregion

            #region Object Initializer
            //Emp emp = new Emp() { No = 4, Name = "ABC", Address = "Pune" };

            ////emp.No = 4;
            ////emp.Name = "ABC";
            ////emp.Address = "pune";


            ////Collection Initializer

            ////var arr = new List<Emp>()
            ////{
            ////  new Emp (){ No = 11, Name = "Vivek1", Address = "Pune1" },
            ////  new Emp (){ No = 12, Name = "Vivek2", Address = "Pune2" },
            ////  new Emp (){ No = 13, Name = "Vivek3", Address = "Pune3" },
            ////  new Emp (){ No = 14, Name = "Vivek4", Address = "Pune4" }
            ////};

            ////foreach (var emp in arr)
            ////{
            ////    Console.WriteLine(emp.Name + " - " + emp.Address);
            ////}


            #endregion

            #region Anonymous Type Objects
            //var v1 = new { No = 1, Name = "abc", Address  = "pune" };
            //var v2 = new { No = 1, Name = "abc", Address = "pune" };
            //var v2 = new { No = "xyz", Name = 123, Address = "pune" };
            //var v2 = new { No = 1, Address = "pune" , Name = "abc" };

            //v1.Name = "xyz"; //this shows err.. Name is readonly!


            //Console.WriteLine(  v1.GetType());
            //Console.WriteLine(  v2.GetType());

            //Console.WriteLine(v1.Name);
            //Console.WriteLine(v1.Address);
            #endregion

            #region Extension Method
            //String str = new String(new Char()['m','a','h',.........])
            //string str = "mahesh"; //this is alias to above code

            //MyString obj = new MyString();
            //bool result = obj.CheckForValidEMailAddress(str, 100);
            //Console.WriteLine(result);

            //bool result = MyString.CheckForValidEMailAddress(str, 100);

            //bool result = MyString.CheckForValidEMailAddress(str,100);

            //bool result = str.CheckForValidEMailAddress(200);
            //Console.WriteLine(result);

            //int []arr = new int[] { 10, 20, 30, 40 };


            #endregion

            #region Expression Tree
            // Stopwatch watch = new Stopwatch();

            // //MyDelegate pointer = new MyDelegate(Check);
            // //Func<int, bool> pointer = new Func<int, bool>(Check);

            // //Func<int, bool> pointer = (i) =>
            // //                            {
            // //                                return i > 0;
            // //                            };

            //Expression<Func<int, bool>> tree = (i) => i > 0; //Create Tree
            // Func<int, bool> pointer = tree.Compile();       //Compile Tree

            // watch.Start();
            // //bool result = Check(200);

            // //bool result = pointer(200);//Create, Compile, Execute Expression Tree

            // bool result = pointer(200); //Execute Tree

            // bool result1 = pointer(200); //Execute Tree
            // bool result2 = pointer(200); //Execute Tree
            // bool result3 = pointer(200); //Execute Tree

            // watch.Stop();

            // Console.WriteLine(result);
            // Console.WriteLine("Time Taken  = {0} Elapsed Ticks.", watch.ElapsedTicks);
            #endregion

            #region Common Collection Initializer for LINQ
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


            #endregion

            #region Routine For Each Based Search
            //List<Emp> result = new List<Emp>();
            //foreach (Emp emp in emps)
            //{
            //    if (emp.Address.ToLower().Contains(filter.ToLower()))
            //    {
            //        result.Add(emp);
            //    }
            //}
            #endregion

            #region LINQ Select * 

            //var result = (from emp in emps
            //              where emp.Address.ToLower().Contains(filter.ToLower())
            //              select emp);//.ToList();

            //emps.Add(new Emp() { No = 9, Name = "Jayesh", Address = "Kanpur" });


            //Console.WriteLine("--------------------------");
            //Console.WriteLine(  "Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (var emp in result)
            //{
            //    Console.WriteLine(emp.ToString());
            //}

            #endregion

            #region LINQ Select Only Name
            //var result = (from emp in emps
            //              where emp.Address.ToLower().Contains(filter.ToLower())
            //              select emp.Name);

            //emps.Add(new Emp() { No = 9, Name = "Jayesh", Address = "Kanpur" });


            //Console.WriteLine("--------------------------");
            //Console.WriteLine("Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (string name in result)
            //{
            //    Console.WriteLine(name);
            //}
            #endregion

            #region LINQ Select Name, Address into Result Holder Objects
            //var result = (from emp in emps
            //              where emp.Address.ToLower().Contains(filter.ToLower())
            //              select new ResultHolder() 
            //              {
            //                  EName = "mr / mrs " +  emp.Name, 
            //                  EAddress = emp.Address 
            //              });

            //Console.WriteLine("--------------------------");
            //Console.WriteLine("Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (var rhObject in result)
            //{
            //    Console.WriteLine(rhObject.EName  + " -  " + rhObject.EAddress);
            //}
            #endregion

            #region LINQ Select Name, Address into Anonymous Type Objects
            //var result = (from emp in emps
            //              where emp.Address.ToLower().Contains(filter.ToLower())
            //              select new
            //              {
            //                  EName = "mr / mrs " + emp.Name,
            //                  EAddress = emp.Address
            //              });

            //Console.WriteLine("--------------------------");
            //Console.WriteLine("Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (var obj in result)
            //{
            //    Console.WriteLine(obj.EName + " -  " + obj.EAddress);
            //}
            #endregion

            #region LINQ Select *  | LINQ Transformation into Methods

            #region How LINQ is written
            ////var result = (from emp in emps
            ////              where emp.Address.ToLower().Contains(filter.ToLower())
            ////              select emp).ToList();
            #endregion

            #region How LINQ is transformed 

            ////Func<Emp, bool> pointer = (emp) => 
            ////                                {
            ////                                    return emp.Address.ToLower().Contains(filter.ToLower())  ;
            ////                                };

            ////var result = emps.Where(pointer);

            ////var result = emps.Where((emp) =>
            ////{
            ////    return emp.Address.ToLower().Contains(filter.ToLower());
            ////});
            #endregion

            #region Print Result
            ////Console.WriteLine("--------------------------");
            ////Console.WriteLine("Result is ");
            ////Console.WriteLine("--------------------------");

            ////foreach (var emp in result)
            ////{
            ////    Console.WriteLine(emp.ToString());
            ////}

            #endregion

            #region Maximum Features Combined Code
            //var result = emps
            //    .Where((emp) =>
            //        {
            //            return emp.Address.ToLower().Contains(filter.ToLower());
            //        })
            //    .Select((emp) => 
            //        {
            //            return emp.Name;
            //            //return new
            //            //{
            //            //    EName = "mr / mrs " + emp.Name,
            //            //    EAddress = emp.Address
            //            //};
            //        });

            //Console.WriteLine("--------------------------");
            //Console.WriteLine("Result is ");
            //Console.WriteLine("--------------------------");

            //foreach (var name in result)
            //{
            //    Console.WriteLine(name);
            //}
            #endregion

            #endregion

            #region Partial Method
            //Student student = new Student();
            //student.Age = 20;

            #endregion

            #region Dynamic Type 
            //Factory factory = new Factory();
            //Console.WriteLine("1 : Student, 2 : Book");
            //int choice = Convert.ToInt32(Console.ReadLine());

            ////dynamic someObject = factory.GetMeSomething(choice);
            ////Console.WriteLine(someObject.GetStudentDetails());

            //object someObject = factory.GetMeSomething(choice);
            //if (someObject is Student)
            //{
            //    Student student = (Student)someObject;
            //    Console.WriteLine(student.GetStudentDetails()) ;
            //}
            //else if (someObject is Book)
            //{
            //    Book book = (Book)someObject;
            //    Console.WriteLine(book.GetBookDetails());
            //}
            #endregion

            #region Named and Optional Paramaters
            //Print(100, "Rohit", "Mumbai");
            //Print(100, "Rohit");

            //Print(100);
            //Print(100,address: "Chennai");

            //Print(100, address: "Chennai", name: "MSD");

            #endregion

            //Week week = new Week();

            //foreach (string day in week)
            //{
            //    Console.WriteLine(day);
            //}


            Console.ReadLine();
        }




        public static void Print (int no, string name = "Virat", 
                                          string address= "Delhi")
        {
            Console.WriteLine("No = {0}, Name = {1}, Address = {2}", no,name,address);
        }
       

        //public static bool Check(int i)
        //{
        //    return i > 0;
        //}


    }

    public class Week : IEnumerable
    {
        private string[] days = new string[] { "Mon", "Tue", "Wed", "Thur" };

        public IEnumerator GetEnumerator()
        {
            for (int i = 0; i < days.Length; i++)
            {
                yield return days[i];
            }
        }
    }

    public class ResultHolder
    {
        public string EName { get; set; }
        public string EAddress { get; set; }
    }

    // public class MyString: String
    public static class MyString
    {
        //public static bool CheckForValidEMailAddress(string str, int i)
        public static bool CheckForValidEMailAddress(this string str, int i)
        {
            //some logic for checking the string!
            return false;
        }

        //public bool CheckForValidEMailAddress(string str, int i)
        //{
        //    //some logic for checking the string!
        //    return false;
        //}
    }

    #region Generics Demo - 4
    //public class Maths<T>   //Generic Class
    //{
    //    public void Swap(ref T x, ref T y) //Generic Method
    //    {
    //        T z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }
    //}

    //public class SpecialMaths<P,Q,R,S> : Maths<P>
    //{
    //    public P NonsenseMethod(P x, Q y, R z, S w)
    //    {
    //        return x;
    //    }
    //}
    #endregion

    #region Generics Demo - 3
    //public class Maths<T>   //Generic Class
    //{
    //    public void Swap(ref T x, ref T y) //Generic Method
    //    {
    //        T z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }
    //}

    //public class SpecialMaths: Maths<int>
    //{

    //}
    #endregion

    #region Generics Demo - 2
    //public class Maths  //Generic Class
    //{

    //    public int Add (int x, int y)
    //    {
    //        return x + y;
    //    }
    //    public void Swap<T> (ref T x, ref T y) //Generic Method
    //    {
    //        T z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }
    //}
    #endregion

    #region Generics Demo - 1
    //public class Maths<T>   //Generic Class
    //{
    //    public void Swap(ref T x, ref T y) //Generic Method
    //    {
    //        T z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }
    //}
    #endregion

    #region Non Generic Logic
    //public class Maths
    //{
    //    public void Swap(ref int x,ref int y)
    //    {
    //        int z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }

    //    public void Swap(ref string x, ref string y)
    //    {
    //        string z;
    //        z = x;
    //        x = y;
    //        y = z;
    //    }
    //}
    #endregion

    //Emp Class Code
    public class Emp
    {
        #region Auto Property
        public int No { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        #endregion

        #region Normal / Routine Property + Member
        //private int _No;
        //private string _Name;
        //private string _Address;

        //public string Address
        //{
        //    get { return _Address; }
        //    set { _Address = value; }
        //}

        //public string Name
        //{
        //    get { return _Name; }
        //    set { _Name = value; }
        //}

        //public int No
        //{
        //    get { return _No; }
        //    set { _No = value; }
        //}
        #endregion


        public override string ToString()
        {
            return string.Format("No = {0}, Name = {1}, Address = {2}", No, Name, Address);
        }

    }

    #region Sealed Class 
    //public sealed class A
    //{

    //}

    //public class B : A
    //{

    //}
    #endregion

    public class Book
    {
        public string GetBookDetails()
        {
            return "Some Book details...";
        }

    }

    public class Factory
    {
        public object GetMeSomething(int choice)
        {
            if (choice == 1)
            {
                return new Student();
            }
            else
            {
                return new Book();
            }
        }
    }
}




