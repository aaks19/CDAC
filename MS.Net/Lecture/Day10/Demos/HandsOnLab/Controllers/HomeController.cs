using HandsOnLab.Models;
using Microsoft.AspNetCore.Mvc;

namespace HandsOnLab.Controllers
{
    public class HomeController : BaseController
    {
        EmpDAL dbObj = new EmpDAL();
        public IActionResult Index()
        {
            List<Emp> emps = dbObj.GetEmps();
            return View("Index", emps);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Emp emp)
        {
            dbObj.AddEmp(emp);
            return Redirect("/Home/Index");
        }
    }
}
