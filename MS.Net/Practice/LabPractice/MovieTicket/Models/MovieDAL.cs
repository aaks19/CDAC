using Microsoft.Data.SqlClient;

namespace MovieTicket.Models
{
    public class MovieDAL
    {
        private string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";
        public List<Movie> GetMovies()
        {
            List<Movie> list = new List<Movie>();

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            SqlCommand cmd = new SqlCommand("select * from MovieTicket", connection);

            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Movie movie = new Movie();
                movie.id = Convert.ToInt32(reader["id"]);
                movie.name = reader["name"].ToString();
                movie.qty = Convert.ToInt32(reader["qty"]);
                movie.price = Convert.ToDouble(reader["price"]);

                list.Add(movie);
            }


            return list;
        }

        public int AddMovie(Movie movie)
        {
            List<Movie> list = new List<Movie>();
            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = " insert into MovieTicket(name,qty,price) values('{0}','{1}','{2}')";
            string query = string.Format(queryFormat, movie.name, movie.qty, movie.price);

            SqlCommand cmd = new SqlCommand(query, connection);
            int affectedRows = cmd.ExecuteNonQuery();

            return affectedRows;
        }

        public Movie GetMovie(int id)
        {
            var movie = GetMovies();
            var ticketSearched = (
                    from m in movie
                    where m.id == id
                    select m).First(); 
          
            return ticketSearched;
        }

        public int UpdateMovie(Movie movies)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = " update MovieTicket set name='{0}', qty='{1}', price='{2}' where id='{3}'";
            string query = string.Format(queryFormat, movies.name, movies.qty, movies.price, movies.id);

            SqlCommand cmd = new SqlCommand(query, connection);
            int affectedRows = cmd.ExecuteNonQuery();

            connection.Close();
            return affectedRows;

        }

        public int Delete(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "delete from MovieTicket where id='{0}'";
            string query = string.Format(queryFormat, id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            return rowAffected;
        }
    }
}
