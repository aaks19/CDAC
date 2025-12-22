using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    public class TestController : Controller
    {
        public IActionResult Index()
        {
            ViewBag.Title = "Welcome Home";
            return View();
        }

        public IActionResult About()
        {
            ViewBag.Title = "About Us!";
            return View();
        }

        public IActionResult Contact()
        {
            ViewBag.Title = "Reach Us Here!";
            return View();
        }
    }
}
