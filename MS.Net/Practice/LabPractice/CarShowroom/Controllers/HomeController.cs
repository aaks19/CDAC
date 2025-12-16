using CarShowroom.Models;
using Microsoft.AspNetCore.Mvc;

namespace CarShowroom.Controllers
{
    public class HomeController : Controller
    {
        CarDAL dal = new CarDAL();
        public IActionResult Index()
        {
            List<Car> cars = dal.GetCars();
            return View("Index",cars);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Car car)
        {
            if (!ModelState.IsValid)
            {
                return View("Create");
            }
            dal.AddCar(car);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Car car = dal.GetCar(id);
            return View("Edit",car);
        }

        public IActionResult AfterEdit(Car car)
        {
            if (!ModelState.IsValid)
            {
                return View("Edit");
            }
            Car updatecar = dal.GetCar(car.id);
            updatecar.name = car.name;
            updatecar.color = car.color;
            updatecar.qty = car.qty;
            updatecar.price = car.price;

            dal.UpdateCar(updatecar);
            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            dal.DeleteCar(id);
            return Redirect("/Home/Index");
        }

    }
}
