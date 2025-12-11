using Microsoft.EntityFrameworkCore;

namespace VehicleManagement.Models
{
    public class EFDBContext : DbContext
    {
        public DbSet<Vehicle> Vehicles { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;");
        }
    }
}
