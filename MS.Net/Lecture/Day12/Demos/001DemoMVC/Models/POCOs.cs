using _001DemoMVC.Helpers;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace _001DemoMVC.Models
{
    [Table("Employee")]
	public class Emp
	{
        [Column("No",TypeName = "int")]
        [Key]
        //[Range(1,100, ErrorMessage = "No is not in range!")]
        public int no { get; set; }

        [Column("Name", TypeName = "varchar")]
        [StringLength(50)]
        [Required(ErrorMessage = "Name is required.")]
        [CheckDuplicate(ErrorMessage = "Name already exists!")]
        public string name { get; set; }

        [Column("Address", TypeName = "varchar")]
        [StringLength(50)]
        [Required(ErrorMessage = "Address is required.")]
        //[RegularExpression("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com$", ErrorMessage = "EMail has invalid pattern!")]
        public string address { get; set; }
    }

    [Table("Trainer")]
    public class Trainer
    {
        [Column("TID", TypeName = "int")]
        [Key]
        public int TrainerID { get; set; }

        [Column("TName", TypeName = "varchar")]
        [StringLength(50)]
        public string TrainerName { get; set; }

        public List<Subject> Subjects { get; set; }
    }

    [Table("Subject")]
    public class Subject
    {
        [Column("SID", TypeName = "int")]
        [Key]
        public int SubjectID { get; set; }

        [Column("SName", TypeName = "varchar")]
        [StringLength(50)]
        public string SubjectName { get; set; }

        public List<Trainer> Trainers { get; set; }
    }

    public class EFDBContext: DbContext
    {
        public DbSet<Emp>  Emps { get; set; }
        public DbSet<Trainer> Trainers { get; set; }
        public DbSet<Subject> Subjects { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=EFDB;Integrated Security=True;");
        }
    }
}
