using System.ComponentModel.DataAnnotations;

namespace CarShowroom.Models
{
    public class Car
    {
        public int id { get; set; }

        [Required(ErrorMessage ="Name can't be empty")]
        public string name { get; set; }

        [Required(ErrorMessage = "Color field can't be empty")]
        public string color { get; set; }

        [Required(ErrorMessage = "Quantity can't be empty")]
        [Range(1,int.MaxValue,ErrorMessage ="quantity must be qreater than 0")]
        public int qty { get; set; }

        [Required(ErrorMessage = "Price can't be empty")]
        [Range(1, int.MaxValue, ErrorMessage = "price must be qreater than 0")]
        public double price { get; set; }
    }
}
