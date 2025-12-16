using Microsoft.Data.SqlClient;
using System.Data.SqlTypes;
using System.Reflection.Metadata.Ecma335;

namespace CarShowroom.Models
{
    public class CarDAL
    {
        string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=PracticeDB;Integrated Security=True;";

        public List<Car> GetCars()
        {
            List<Car> list = new List<Car>();
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand cmd = new SqlCommand("select * from carshowroom", connection);
            SqlDataReader reader = cmd.ExecuteReader();
            while (reader.Read())
            {
                Car car = new Car();
                car.id = Convert.ToInt32(reader["id"]);
                car.name = reader["name"].ToString();
                car.color = reader["color"].ToString();
                car.qty = Convert.ToInt32(reader["qty"]);
                car.price = Convert.ToDouble(reader["price"]);

                list.Add(car);
            }
            connection.Close();
            return list;
        }


        public Car GetCar(int id)
        {
            var cars = GetCars();
            var carSearched = (from c in cars
                               where c.id == id
                               select c).First();
            return carSearched;
        }

        public int AddCar(Car car)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "insert into carshowroom(name,color,qty,price) values('{0}','{1}',{2},{3})";
            string query = string.Format(queryFormat, car.name, car.color, car.qty, car.price);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            connection.Close();
            return rowAffected;
        }

        public int UpdateCar(Car car)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "Update carshowroom set name='{0}',color='{1}',qty={2},price={3} where id={4}";
            string query = string.Format(queryFormat, car.name, car.color, car.qty, car.price,car.id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            connection.Close();
            return rowAffected;
        }

        public int DeleteCar(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "delete from carshowroom where id={0}";
            string query = string.Format(queryFormat, id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            connection.Close();
            return rowAffected;
        }
    }
}
