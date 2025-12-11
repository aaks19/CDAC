using Microsoft.EntityFrameworkCore;

namespace VehicleManagement.Models
{
    public class EFDBContext : DbContext
    {
        public EFDBContext(DbContextOptions<EFDBContext> options) : base(options)
        {
        }
        public DbSet<Vehicle> Vehicles { get; set; }
    }
}
