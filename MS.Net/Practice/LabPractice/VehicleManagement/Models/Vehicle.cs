using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace VehicleManagement.Models
{
    [Table("Vehicles")]
    public class Vehicle
    {
        [Column("id",TypeName ="int")]
        [Key]
        public int id { get; set; }

        [Column("buyerName", TypeName ="varchar")]
        [MaxLength(50)]
        public string buyerName { get; set; }

        [Column("vehicleName", TypeName = "varchar")]
        [MaxLength(50)]
        public string vehicleName { get; set; }

        [Column("price", TypeName = "int")]
        [MaxLength(50)]
        public int price { get; set; }
    }
}
