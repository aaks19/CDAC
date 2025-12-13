using HandsOnLab.Models;
using Microsoft.AspNetCore.Mvc;

namespace HandsOnLab.Controllers
{
    public class ProductsController : Controller
    {
        MyDBContext dbObject = new MyDBContext();
        public IActionResult Index()
        {
            var products = dbObject.Products.ToList();
            return View("Index", products);
        }
    }
}
