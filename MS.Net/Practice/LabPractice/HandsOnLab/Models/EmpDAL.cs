using Microsoft.Data.SqlClient;

namespace HandsOnLab.Models
{
    public class EmpDAL
    {
        private string connectionString = 
            "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";
         public List<Emp> GetEmps()
        {
            List<Emp> list = new List<Emp>();

            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            SqlCommand cmd = new SqlCommand("select * from Emp", connection);

            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Emp emp = new Emp();
                emp.Id = Convert.ToInt32(reader["Id"]);
                emp.Name = reader["Name"].ToString();
                emp.Address = reader["Address"].ToString();
                list.Add(emp);
            }


            return list;
        }

        public int AddEmps(Emp emp)
        {
            List<Emp> list = new List<Emp>();

            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = "insert into Emp(Name,Address) values('{0}','{1}')";
            string query = string.Format(queryFormat, emp.Name, emp.Address);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();


            return rowAffected;
        }


        public Emp GetEmp(int id)
        {
            var emp = GetEmps();
            var empSearched = (from e in emp
                               where e.Id == id
                               select e).First();
            return empSearched;
        }

        public int UpdateEmp(Emp emp)
        {
            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = " update Emp set Name='{0}', Address='{1}' where Id={2}";
            string query = string.Format(queryFormat, emp.Name, emp.Address, emp.Id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            connection.Close();
            return rowAffected;
        }


        public int Delete(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = " delete from Emp where Id='{0}'";
            string query = string.Format(queryFormat, id);

            SqlCommand cmd = new SqlCommand(query, connection);
            int rowAffected = cmd.ExecuteNonQuery();

            return rowAffected;
        }
    }
}
