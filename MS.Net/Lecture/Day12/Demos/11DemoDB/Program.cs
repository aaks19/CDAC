using Microsoft.Data.SqlClient;

namespace _11DemoDB
{
    internal class Program
    {
        static void Main(string[] args)
        {
            #region Select 
            //string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True";

            //SqlConnection connection = new SqlConnection(connectionString);
            //connection.Open();
            //SqlCommand cmd = new SqlCommand("select * from Emp", connection);

            //SqlDataReader reader = cmd.ExecuteReader();

            //while (reader.Read())
            //{
            //    Console.WriteLine(reader["No"].ToString() + 
            //                      " - " + 
            //                      reader["Name"].ToString() + 
            //                      " - " +
            //                      reader["Address"].ToString());
            //}

            //connection.Close();

            //Console.WriteLine("Done reading from DB");
            #endregion

            #region Insert 

            //Console.WriteLine("Enter Your Name");
            //string name = Console.ReadLine();

            //Console.WriteLine("Enter Your Address");
            //string address= Console.ReadLine();

            //string queryFormat = 
            //    "insert into Emp(Name, Address) values('{0}', '{1}')";

            //string query = string.Format(queryFormat, name, address);

            //string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True";

            //SqlConnection connection = new SqlConnection(connectionString);
            //connection.Open();
            //SqlCommand cmd = new SqlCommand(query, connection);

            //int rowsAffected= cmd.ExecuteNonQuery();

            //if (rowsAffected > 0)
            //{
            //    Console.WriteLine("Record Inserted Into DB");
            //}
            //else
            //{
            //    Console.WriteLine("Something Wrong!");
            //}

            //connection.Close();

            //Console.WriteLine("Done updating DB");

            #endregion

            #region Update 

            //Console.WriteLine("Enter No to Edit Record");
            //int No = Convert.ToInt32(Console.ReadLine());

            //Console.WriteLine("Enter Your New Name");
            //string name = Console.ReadLine();

            //Console.WriteLine("Enter Your New Address");
            //string address = Console.ReadLine();

            //string queryFormat =
            //    "update Emp set Name = '{1}', Address = '{2}' where No = {0}";

            //string query = string.Format(queryFormat, No, name, address);

            //string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True";

            //SqlConnection connection = new SqlConnection(connectionString);
            //connection.Open();
            //SqlCommand cmd = new SqlCommand(query, connection);

            //int rowsAffected = cmd.ExecuteNonQuery();

            //if (rowsAffected > 0)
            //{
            //    Console.WriteLine("Record Inserted Into DB");
            //}
            //else
            //{
            //    Console.WriteLine("Something Wrong!");
            //}

            //connection.Close();

            //Console.WriteLine("Done updating DB");

            #endregion

            Console.ReadLine();

        }
    }
}
