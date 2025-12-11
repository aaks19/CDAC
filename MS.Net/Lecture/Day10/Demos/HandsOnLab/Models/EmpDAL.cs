using Microsoft.Data.SqlClient;

namespace HandsOnLab.Models
{
    public class EmpDAL
    {
        private string connectionString =
            "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=HandsOnDB;Integrated Security=True;";

        public List<Emp> GetEmps()
        {
            List<Emp> list = new List<Emp>();

            SqlConnection connection =
                new SqlConnection(connectionString);
            connection.Open();

            SqlCommand command =
                new SqlCommand("select * from Emp", connection);

            SqlDataReader reader =  command.ExecuteReader();

            while (reader.Read()) 
            {
                Emp emp = new Emp();
                emp.No = Convert.ToInt32(reader["No"]);
                emp.Name = reader["Name"].ToString();
                emp.Address = reader["Address"].ToString();
                list.Add(emp);
            }
            return list;
        }

        public int AddEmp(Emp emp)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat 
                = "insert into Emp(Name, Address) values('{0}', '{1}')";

            string query = string.Format(queryFormat, emp.Name, emp.Address);

            SqlCommand command = new SqlCommand(query, connection);

            int rowsAffected = command.ExecuteNonQuery();

            return rowsAffected;
        }
    }
}
