using _12DemoDB.Model;
using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _12DemoDB.DAL
{
    public class EmpDAL
    {
        public List<Emp> GetEmps()
        {
            List<Emp> emps = new List<Emp>();

            string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True";

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand cmd = new SqlCommand("select * from Emp", connection);

            SqlDataReader reader = cmd.ExecuteReader();



            while (reader.Read())
            {
                Emp emp = new Emp();
                emp.No = Convert.ToInt32(reader["No"]);
                emp.Name = reader["Name"].ToString();
                emp.Address = reader["Address"].ToString();

                emps.Add(emp);
            }

            connection.Close();

            return emps;
        }

        public int AddEmp(Emp emp)
        {
            string queryFormat =
                "insert into Emp(Name, Address) values('{0}', '{1}')";

            string query = string.Format(queryFormat, emp.Name, emp.Address);

            string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True";

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand cmd = new SqlCommand(query, connection);

            int rowsAffected = cmd.ExecuteNonQuery();

            connection.Close();

            return rowsAffected;
        }

    }


}
