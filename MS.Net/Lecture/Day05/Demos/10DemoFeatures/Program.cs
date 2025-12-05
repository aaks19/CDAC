using System.Collections;

namespace _10DemoFeatures
{
    //public delegate bool MyDelegate(int i);

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

            #endregion

            Console.ReadLine();
        }
        public static bool Check(int i)
        {
            return i > 0;
        }

     
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

    }

}




