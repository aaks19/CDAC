using _001DemoMVC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using _001DemoMVC.Filters;
namespace _001DemoMVC.Controllers
{

    public class HomeController : BaseController
    {
        EFDBContext dbObject = new EFDBContext();


        public IActionResult Index()
        {
            string loggedInUserName =
            HttpContext.Session.GetString("UserName");

            //ViewData["username"] = loggedInUserName;
            ViewBag.Username = loggedInUserName;

            //if (loggedInUserName == "mahesh")
            //{
            //    throw new Exception("My Exception");
            //}

            List<Emp> emps = dbObject.Emps.ToList();
            return View("Index", emps);
        }
        #region Handle Excpetion Via Traditional Try - Catch Block Code

        //public IActionResult Index()
        //{
        //    try
        //    {
        //        string loggedInUserName =
        //       HttpContext.Session.GetString("UserName");

        //        //ViewData["username"] = loggedInUserName;
        //        ViewBag.Username = loggedInUserName;


        //        if (loggedInUserName == "mahesh")
        //        {
        //            throw new Exception("My Exception");
        //        }

        //        List<Emp> emps = dbObject.Emps.ToList();
        //        return View("Index", emps);
        //    }
        //    catch (Exception ex)
        //    {
        //        return View("Error", ex);
        //    }
        //}
        #endregion
        public IActionResult Create()
        {
            return View();
        }
        public IActionResult AfterCreate(Emp emp)
        {
            dbObject.Emps.Add(emp);
            dbObject.SaveChanges();
            return Redirect("/Home/Index");
        }
        public IActionResult Edit(int id)
        {
            Emp emp = dbObject.Emps.Find(id);
            return View("Edit", emp);
        }
        public IActionResult AfterEdit(Emp emp)
        {
            Emp empBeingUpdated = dbObject.Emps.Find(emp.no);
            empBeingUpdated.name = emp.name;
            empBeingUpdated.address = emp.address;

            dbObject.SaveChanges();

            return Redirect("/Home/Index");
        }
        public IActionResult Delete(int id)
        {
            var emp = dbObject.Emps.Find(id);

            dbObject.Emps.Remove(emp);
            dbObject.SaveChanges();

            return Redirect("/Home/Index");
        }
    }
}
