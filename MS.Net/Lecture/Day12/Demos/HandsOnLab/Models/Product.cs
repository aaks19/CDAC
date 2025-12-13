using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace HandsOnLab.Models
{
    [Table("Product")]
    public class Product
    {
        [Column("id", TypeName = "int")]
        [Key]
        public int id { get; set; }

        [Column("title", TypeName = "varchar")]
        [MaxLength(50)]
        public string title { get; set; }
        
        [Column("description", TypeName = "varchar")]
        [MaxLength(100)]
        public string description { get; set; }
        [Column("cost", TypeName = "int")]
        public int cost { get; set; }
    }

  
}
