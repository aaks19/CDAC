using Microsoft.AspNetCore.Mvc;
using LabProject.Models;
using LabProject.Filters;
using Microsoft.EntityFrameworkCore.Update;


namespace LabProject.Controllers
{
    public class HomeController : Controller
    {
        StudentViewModel svm = new StudentViewModel();

        [AuthFilter]
        [LogFilter]
        public IActionResult Index()
        {
            ViewBag.Title = "Home";
            ViewBag.UserName = GetUserName();

            List<Student> students = svm.GetStudents();

            return View(students);
        }

        [AuthFilter]
        [LogFilter]

        public IActionResult Create()
        {
            return View();
        }

        [AuthFilter]
        [HttpPost]
        [LogFilter]

        public IActionResult Create(Student student)
        {
            if (ModelState.IsValid)
            {
                svm.AddStudent(student);
                return Redirect("/Home/Index");
            }
            else
            {
                ViewBag.Message = "Kuch to gadbad hai...";


                return View(student);
            }
        }


        [AuthFilter]
        [LogFilter]

        public IActionResult Edit(int id) // make sure it should be "id" in the parameter
        {
            Student student = svm.GetStudent(id);
            return View(student);
        }

        [AuthFilter]
        [HttpPost]
        [LogFilter]

        public IActionResult Edit(Student updatedStudent)
        {
            if (ModelState.IsValid)
            {
                int rowsAffected = svm.UpdateStudent(updatedStudent);
                if (rowsAffected > 0)
                {
                    return Redirect("/Home/Index");
                }
                else
                {
                    ViewBag.Message = "Failed to update";
                    return View(updatedStudent);
                }
            }
            else
            {
                ViewBag.Message = "Something went wrong...";
                return View(updatedStudent);
            }
                
        }

        [AuthFilter]
        [LogFilter]

        public IActionResult Delete(int id)
        {
            svm.DeleteStudent(id);
            return Redirect("/Home/Index");
        }

        public IActionResult About()
        {
            ViewBag.Title = "About";
            ViewBag.UserName = GetUserName();
            return View();
        }

        public IActionResult Contact()
        {
            ViewBag.Title = "Contacct";
            ViewBag.UserName = GetUserName();
            return View();
        }

        private string GetUserName()
        {
            if (HttpContext.Session.GetString("UserName") != null &&
                HttpContext.Session.GetString("UserName") != "")
            {
                return HttpContext.Session.GetString("UserName");
            }
            else
            {
                return "Guest";
            }
        }
    }
}
