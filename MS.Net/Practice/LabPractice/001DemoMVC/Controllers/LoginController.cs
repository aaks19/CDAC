using _001DemoMVC.Models;
using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult SignIn()
        {
            return View();
        }

        [HttpPost]
        public IActionResult SignIn(User user)
        {
           //IACSD Students needs to write code for validation of 
           //credentials - w.r.t. DB
           //Check actual credentials w.r.t. DB 

            if (user.UserName == "mahesh" && user.Password == "mahesh@123")
            {
                HttpContext.Session.SetString("IsLoggedIn", "true");
                HttpContext.Session.SetString("UserName", user.UserName);

                return Redirect("/Home/Index");
            }
            else
            {
                ViewBag.ErrorMessage = "May be credentials are wrong!";
                return View();
            }
        }

        public IActionResult SignOut()
        {
            HttpContext.Session.Remove("IsLoggedIn");
            HttpContext.Session.Remove("UserName");
            return Redirect("/Login/SignIn");
        }
    }
}
