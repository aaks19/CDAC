using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    public class ExceptionController : Controller
    {
        public IActionResult Error()
        {
            return View();
        }
    }
}
