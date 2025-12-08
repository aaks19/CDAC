using Microsoft.Data.SqlClient;

namespace Day7_SQL_Server
{
    internal class Program
    {
        static void Main(string[] args)
        {
            

            int choice;

            do
            {
                String connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB; Initial Catalog = IACSDDB; Integrated Security = True";
                SqlConnection connection = new SqlConnection(connectionString);
                connection.Open();

                Console.WriteLine("Choose: 1: Insert  2: Update   3: Display");
                choice = Convert.ToInt32(Console.ReadLine());
                switch (choice)
                {
                    case 1:
                        {
                            //vehicle details from user
                            Console.WriteLine("Vehicle details to be inserted:");
                            Console.Write("Vehicle Name: ");
                            String vehicleName = Console.ReadLine();
                            Console.Write("Vehicle Type: ");
                            String vehicleType = Console.ReadLine();
                            Console.Write("Vehicle Price: ");
                            String vehiclePrice = Console.ReadLine();
                            // create insert query
                            String queryFormat = "insert into vehicles (vehicle_name, vehicle_type, vehicle_price) values ('{0}', '{1}', {2})";
                            String query = String.Format(queryFormat, vehicleName, vehicleType, vehiclePrice);

                            //execute command
                            SqlCommand command = new SqlCommand(query, connection);
                            int rowAffected = command.ExecuteNonQuery();

                            if (rowAffected > 0)
                            {
                                Console.WriteLine("Inserted successfully...");
                            }
                            else
                            {
                                Console.WriteLine("Insertion failed...");
                            }
                            connection.Close();
                            break;
                        }
                    case 2:
                        {
                            Console.WriteLine("Vehicle ID to be updated:");
                            int vehicleId = Convert.ToInt32(Console.ReadLine());
                            Console.Write("Vehicle new Name: ");
                            String vehicleName = Console.ReadLine();
                            Console.Write("Vehicle new Type: ");
                            String vehicleType = Console.ReadLine();
                            Console.Write("Vehicle new Price: ");
                            String vehiclePrice = Console.ReadLine();
                            String queryFormat = "update vehicles set vehicle_name='{0}' , vehicle_type='{1}' , vehicle_price={2} where vid={3}";
                            String query = String.Format(queryFormat, vehicleName, vehicleType, vehiclePrice, vehicleId);

                            SqlCommand updateCmd = new SqlCommand(query, connection);
                            int rowAffected = updateCmd.ExecuteNonQuery();
                            if (rowAffected > 0)
                            {
                                Console.WriteLine("Updated successfully...");
                            }
                            else
                            {
                                Console.WriteLine("Updation failed...");
                            }
                            connection.Close();
                            break;
                        }
                    case 3:
                        {
                            SqlCommand displayCmd = new SqlCommand("select * from vehicles", connection);
                            SqlDataReader reader = displayCmd.ExecuteReader();

                            while (reader.Read())
                            {
                                Console.WriteLine(reader["vid"].ToString() + " -- " + reader["vehicle_name"].ToString() + " -- " + reader["vehicle_type"].ToString() + " -- " + reader["vehicle_price"].ToString());
                            }
                            connection.Close();
                            break;
                        }
                }
            } while (choice != 0);

            #region Insert Data

            ////vehicle details from user
            //Console.WriteLine("Vehicle details to be inserted:");
            //Console.Write("Vehicle Name: ");
            //String vehicleName = Console.ReadLine();
            //Console.Write("Vehicle Type: ");
            //String vehicleType = Console.ReadLine();
            //Console.Write("Vehicle Price: ");
            //String vehiclePrice = Console.ReadLine();
            //// create insert query
            //String queryFormat = "insert into vehicles (vehicle_name, vehicle_type, vehicle_price) values ('{0}', '{1}', {2})";
            //String query = String.Format(queryFormat, vehicleName, vehicleType, vehiclePrice);

            ////execute command
            //SqlCommand command = new SqlCommand(query, connection); 
            //int rowAffected = command.ExecuteNonQuery();

            //if(rowAffected > 0)
            //{
            //    Console.WriteLine("Inserted successfully...");
            //}
            //else
            //{
            //    Console.WriteLine("Insertion failed...");
            //}
            #endregion

            #region Update data
            //Console.WriteLine("Vehicle ID to be updated:");
            //int vehicleId = Convert.ToInt32(Console.ReadLine());
            //Console.Write("Vehicle new Name: ");
            //String vehicleName = Console.ReadLine();
            //Console.Write("Vehicle new Type: ");
            //String vehicleType = Console.ReadLine();
            //Console.Write("Vehicle new Price: ");
            //String vehiclePrice = Console.ReadLine();
            //String queryFormat = "update vehicles set vehicle_name='{0}' , vehicle_type='{1}' , vehicle_price='{2}' where vehicle_id={3}";
            //String query = String.Format(queryFormat,vehicleName,vehicleType,vehiclePrice,vehicleId);

            //SqlCommand updateCmd = new SqlCommand(query, connection);
            //int rowAffected = updateCmd.ExecuteNonQuery();
            //if (rowAffected > 0)
            //{
            //    Console.WriteLine("Updated successfully...");
            //}
            //else
            //{
            //    Console.WriteLine("Updation failed...");
            //}
            #endregion

            #region Display All
            //SqlCommand displayCmd = new SqlCommand("select * from vehicles", connection);
            //SqlDataReader reader = displayCmd.ExecuteReader();

            //while (reader.Read())
            //{
            //    Console.WriteLine(reader["vid"].ToString() + " -- " + reader["vehicle_name"].ToString() + " -- " + reader["vehicle_type"].ToString() + " -- " + reader["vehicle_price"].ToString());
            //}
            #endregion

            
        }
    }
}
