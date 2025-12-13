using Microsoft.EntityFrameworkCore;

namespace HandsOnLab.Models
{
    public class MyDBContext : DbContext
    {
        public DbSet<Product> Products { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=HandsOnDB;Integrated Security=True;");
        }
    }
}
