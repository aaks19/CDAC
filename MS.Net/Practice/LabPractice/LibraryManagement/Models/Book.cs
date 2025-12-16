using System.ComponentModel.DataAnnotations;

namespace LibraryManagement.Models
{
    public class Book
    {
        public int id { get; set; }

        [Required(ErrorMessage ="Book name can't be blank")]
        public string bname { get; set; }

        [Required(ErrorMessage = "Author name can't be blank")]
        public string author { get; set; }

        [Required(ErrorMessage ="Quantity can't be blank")]
        [Range(1, 100, ErrorMessage = "Quantity must be greater than 0")]
        public int qty { get; set; }

        [Required(ErrorMessage = "Price can't be blank")]
        [Range(1,100,ErrorMessage ="Price must be greater than 0")]
        public int price { get; set; }
    }
}
