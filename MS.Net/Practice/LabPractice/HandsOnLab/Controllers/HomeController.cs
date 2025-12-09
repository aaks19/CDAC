using HandsOnLab.Models;
using Microsoft.AspNetCore.Mvc;

namespace HandsOnLab.Controllers
{
    public class HomeController : Controller
    {
        EmpDAL dbObj = new EmpDAL();
        public IActionResult Index()
        {
            List<Emp> emps = dbObj.GetEmps();
            return View("Index",emps);
        }

        public IActionResult Create()
        {
            return View();
        }
        
        public IActionResult AfterCreate(Emp emp)
        {
            dbObj.AddEmps(emp);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Emp emp = dbObj.GetEmp(id);
            return View("Edit", emp);
        }

        public IActionResult AfterEdit(Emp emp)
        {
            Emp empUpdated = dbObj.GetEmp(emp.Id);
            empUpdated.Name = emp.Name;
            empUpdated.Address = emp.Address;

            dbObj.UpdateEmp(empUpdated);

            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            dbObj.Delete(id);
            return Redirect("/Home/Index");
        }
    }
}
