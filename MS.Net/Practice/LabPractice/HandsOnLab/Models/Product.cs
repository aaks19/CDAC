using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Security.Principal;

namespace HandsOnLab.Models
{
    [Table("Products")]
    public class Product
    {
        [Column("id",TypeName = "int")]
        [Key]
        public int id { get; set; }


        [Column("title",TypeName = "varchar")]
        [MaxLength(50)]
        public string title { get; set; }


        [Column("description", TypeName = "varchar")]
        [MaxLength(100)]
        public string description { get; set; }


        [Column("cost", TypeName = "int")]
        public int cost { get; set; }
    }

    public class  MyDBContext: DbContext
    {
        public DbSet<Product> Products { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;");
        }
    }
}
