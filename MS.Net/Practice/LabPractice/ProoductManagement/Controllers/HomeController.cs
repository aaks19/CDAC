using Microsoft.AspNetCore.Mvc;
using ProductManagement.Models;

namespace ProductManagement.Controllers
{
    public class HomeController : Controller
    {
        ProductDAL prodDal = new ProductDAL();
        public IActionResult Index()
        {
            List<Product> products = prodDal.GetProducts();
            return View("Index",products);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Product product)
        {
            prodDal.AddProduct(product);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Product product = prodDal.GetProduct(id);
            return View("Edit",product);
        }

        public IActionResult AfterEdit(Product product)
        {
            Product updateProduct = prodDal.GetProduct(product.pid);

            updateProduct.pname = product.pname;
            updateProduct.qty = product.qty;
            updateProduct.price = product.price;

            prodDal.UpdateProduct(updateProduct);

            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            prodDal.Delete(id);

            return Redirect("/Home/Index");
        }
    }
}
