using Microsoft.Data.SqlClient;

namespace LibraryManagement.Models
{
    public class LibraryDAL
    {
        string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=PracticeDB;Integrated Security=True;";

        public List<Book> GetBooks()
        {
            List<Book> list = new List<Book>();

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            SqlCommand cmd = new SqlCommand("select * from librarymanagement",connection);
            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Book book = new Book();
                book.id = Convert.ToInt32(reader["id"]);
                book.bname = reader["bname"].ToString();
                book.author = reader["author"].ToString();
                book.qty = Convert.ToInt32(reader["qty"]);
                book.price = Convert.ToInt32(reader["price"]);

                list.Add(book);
            }

            
            connection.Close();

            return list;

        }

        public Book GetBook(int id)
        {
            var book = GetBooks();
            var bookSearch = (from b in book
                              where b.id == id
                              select b).First();
            return bookSearch;
        }

        public int AddBook(Book book)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "insert into librarymanagement(bname,author,qty,price) values('{0}','{1}',{2},{3})";
            string query = string.Format(queryFormat, book.bname, book.author, book.qty, book.price);
            SqlCommand cmd = new SqlCommand(query, connection);

            int rowsAffected = cmd.ExecuteNonQuery();

            connection.Close();

            return rowsAffected;
        }

        public int UpdateBook(Book book)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "update librarymanagement set bname='{0}',author='{1}',qty={2},price={3} where id={4}";
            string query = string.Format(queryFormat, book.bname, book.author, book.qty, book.price, book.id);
            SqlCommand cmd = new SqlCommand(query, connection);

            int rowsAffected = cmd.ExecuteNonQuery();

            connection.Close();

            return rowsAffected;
        }

        public int DeleteBook(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "delete from librarymanagement where id={0}";
            string query = string.Format(queryFormat, id);
            SqlCommand cmd = new SqlCommand(query, connection);

            int rowsAffected = cmd.ExecuteNonQuery();

            connection.Close();

            return rowsAffected;
        }
    }
}
