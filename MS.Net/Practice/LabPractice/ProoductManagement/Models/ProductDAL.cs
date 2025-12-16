using Microsoft.CodeAnalysis.Elfie.Diagnostics;
using Microsoft.Data.SqlClient;

namespace ProductManagement.Models
{
    public class ProductDAL
    {
        string connectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";

        public List<Product> GetProducts()
        {
            List<Product> listProducts = new List<Product>();

            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            SqlCommand cmd = new SqlCommand("select * from productmanagement", connection);

            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Product product = new Product();
                product.pid = Convert.ToInt32(reader["pid"]);
                product.pname = reader["pname"].ToString();
                product.qty = Convert.ToInt32(reader["qty"]);
                product.price = Convert.ToDouble(reader["price"]);

                listProducts.Add(product);
            }

            connection.Close();

            return listProducts;
        }

        public int AddProduct(Product product)
        {
            List<Product> list = new List<Product>();

            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();

            string queryFormat = "insert into productmanagement(pname,qty,price) values('{0}','{1}','{2}')";
            string query = String.Format(queryFormat, product.pname, product.qty, product.price);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowsAffected = cmd.ExecuteNonQuery();
            connection.Close();

            return rowsAffected;
        }

        public Product GetProduct(int id)
        {
            var product = GetProducts();
            var pidSearch = (
                from p in product
                where p.pid == id
                select p
                ).First();

            return pidSearch;
        }

        public int UpdateProduct(Product product)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            string queryFormat = "update productmanagement set pname='{0}',qty={1},price={2} where pid = {3}";
            string query = String.Format(queryFormat, product.pname, product.qty, product.price, product.pid);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();
            connection.Close();

            return rowAffected;
        }

        public int Delete(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            string queryFormat = "delete from productmanagement where pid = {0}";
            string query = string.Format(queryFormat, id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();
            connection.Close();

            return rowAffected;
        }
    }
}
