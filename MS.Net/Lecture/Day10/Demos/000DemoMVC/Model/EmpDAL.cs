using Microsoft.Data.SqlClient;
using System.Collections.Generic;
namespace _000DemoMVC.Model
{
    public class EmpDAL
    {
        string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";
        public List<Emp> GetEmps()
        {
            List<Emp> list = new List<Emp>();   
            SqlConnection connection = 
                new SqlConnection(connectionString);

            connection.Open();

            SqlCommand command =
                new SqlCommand("select * from Emp", connection);

            SqlDataReader reader = command.ExecuteReader();
            while (reader.Read())
            {
                list.Add(new Emp() 
                {
                    No = Convert.ToInt32(reader["No"]),
                    Name = reader["Name"].ToString(),
                    Address = reader["Address"].ToString()
                });
            }

            connection.Close();
            return list;
        }

        public Emp GetEmp(int No)
        {
            var emps = GetEmps();
            var empSearched = (from e in emps
                       where e.No == No
                       select e).First();
            return empSearched;
        }

        public int AddEmp(Emp emp)
        {
            SqlConnection connection =
              new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = "insert into Emp(Name, Address) values ('{0}', '{1}')";

            string query = string.Format(queryFormat, emp.Name, emp.Address);

            SqlCommand command =
                new SqlCommand(query, connection);

            int rowsAffected = command.ExecuteNonQuery();
            connection.Close();
            return rowsAffected;
        }

        public int UpdateEmp(Emp emp)
        {
            SqlConnection connection =
              new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = "update Emp set Name = '{1}', Address = '{2}' where No = {0}";

            string query = string.Format(queryFormat,emp.No, emp.Name, emp.Address);

            SqlCommand command =
                new SqlCommand(query, connection);

            int rowsAffected = command.ExecuteNonQuery();
            connection.Close();
            return rowsAffected;
        }

        public int RemoveEmp(int No)
        {
            SqlConnection connection =
              new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = "delete from Emp where No = {0}";

            string query = string.Format(queryFormat, No);

            SqlCommand command =
                new SqlCommand(query, connection);

            int rowsAffected = command.ExecuteNonQuery();
            connection.Close();
            return rowsAffected;
        }
    }

}
