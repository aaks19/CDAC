using LabProject.Filters;
using LabProject.Models;
using Microsoft.AspNetCore.Mvc;

namespace LabProject.Controllers
{
    public class LoginController : Controller
    {
        [LogFilter]

        public IActionResult SignIn()
        {
            ViewBag.Title = "SignIn here";
            ViewBag.UserName = "Guest";
            return View();
        }

        [HttpPost]
        [LogFilter]

        public IActionResult SignIn(LoginUser user)
        {
            if (ModelState.IsValid)
            {
                if(user.UserName == "aks" && user.Password == "aks@123")
                {
                    HttpContext.Session.SetString("UserName", user.UserName);
                    return Redirect("/Home/Index");
                }
                else
                {
                    ViewBag.Message = "Credentials are Incorrect";
                    return View(user);
                }
            }
            else
            {
                return View(user);
            }
        }

        [LogFilter]

        public IActionResult SignOut()
        {
            HttpContext.Session.Remove("UserName");
            return Redirect("/Login/SignIn");
        }
    }
}
