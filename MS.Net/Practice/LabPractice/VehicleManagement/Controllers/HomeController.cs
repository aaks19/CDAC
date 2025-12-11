using Microsoft.AspNetCore.Mvc;
using VehicleManagement.Models;

namespace VehicleManagement.Controllers
{
    public class HomeController : Controller
    {

        private readonly EFDBContext _db;
        public HomeController(EFDBContext db)
        {
            _db = db;
        }

        public IActionResult Index()
        {
            return View();
        }
    }
}
