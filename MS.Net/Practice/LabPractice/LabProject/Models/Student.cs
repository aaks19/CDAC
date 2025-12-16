using System.ComponentModel.DataAnnotations;

namespace LabProject.Models
{
    public class Student
    {
        public int No { get; set; }

        [Required(ErrorMessage = " Name is required")]
        public string Name { get; set; }

        [Required(ErrorMessage = " Address is required")]
        public string Address { get; set; }

        [Required(ErrorMessage = " Age is required")]
        [Range(20,40,ErrorMessage ="Age is incorrect")]
        public int Age { get; set; }

        [Required(ErrorMessage = " Email is required")]
        public string Email { get; set; }

        public bool IsEmailVerified { get; set; }
    }
}
