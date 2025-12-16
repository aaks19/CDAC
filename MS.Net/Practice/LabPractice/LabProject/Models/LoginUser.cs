using System.ComponentModel.DataAnnotations;

namespace LabProject.Models
{
    public class LoginUser
    {
        [Required(ErrorMessage ="User name cannt be empty...")]
        public string UserName { get; set; }

        [Required(ErrorMessage = "Password cannt be empty...")]
        public string Password { get; set; }
    }
}
