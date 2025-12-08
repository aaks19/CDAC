using Microsoft.Data.SqlClient;
using _12DemoDB.Model;
using _12DemoDB.DAL;

namespace _12DemoDB
{
    internal class Program
    {
        static void Main(string[] args)
        {
            EmpDAL empdalObj = new EmpDAL();

            #region Select
            //var emps =  empdalObj.GetEmps();

            //foreach (var emp in emps)
            //{
            //    Console.WriteLine(emp.ToString());
            //}
            #endregion

            #region Insert
            //Emp emp = new Emp();
            //Console.WriteLine("Enter Your Name");
            //emp.Name = Console.ReadLine();

            //Console.WriteLine("Enter Your Address");
            //emp.Address = Console.ReadLine();

            //int rowsAffected =  empdalObj.AddEmp(emp);

            //if (rowsAffected > 0)
            //{
            //    Console.WriteLine("Record Inserted Into DB");
            //}
            //else
            //{
            //    Console.WriteLine("Something Wrong!");
            //}

            #endregion

            Console.ReadLine();
        }
    }
}
