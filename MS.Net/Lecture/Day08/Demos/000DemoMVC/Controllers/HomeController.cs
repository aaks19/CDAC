using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using _000DemoMVC.Model;


namespace _000DemoMVC.Controllers
{

    //Test Responsive ness using https://responsivetesttool.com/
    public class HomeController : Controller
    {
        EmpDAL dbObject = new EmpDAL();

        #region How sequence methods called 

        //public HomeController()
        //{

        //}
        //public override void OnActionExecuting(ActionExecutingContext context)
        //{
        //}

        //public override void OnActionExecuted(ActionExecutedContext context)
        //{
        //}
        #endregion

        #region Old Index
        //public IActionResult Index()
        //{
        //    Emp emp = 
        //        new Emp() { No = 1, Name = "jayesh", Address = "pune" };

        //    return View("Index",emp);
        //}
        #endregion

        public IActionResult GetData2()
        {
            return new ContentResult() { Content = "Hi!", ContentType = "text/plain" };
        }

        public IActionResult GetData1()
        {
            return new JsonResult(dbObject.GetEmps());
        }

        public IActionResult Index()
        {
            List<Emp> emps = dbObject.GetEmps();
            return View("Index", emps);
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Emp emp)
        {
            dbObject.AddEmp(emp);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Emp emp =  dbObject.GetEmp(id);
            return View("Edit", emp);
        }

        public IActionResult AfterEdit(Emp emp)
        {
            Emp empBeingUpdated = dbObject.GetEmp(emp.No);
            empBeingUpdated.Name = emp.Name;
            empBeingUpdated.Address = emp.Address;

            dbObject.UpdateEmp(empBeingUpdated);

            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            dbObject.RemoveEmp(id);
            return Redirect("/Home/Index");
        }
    }
}
#region How View Engine is written


//public class RazorViewEngine : IViewEngine
//{
//    public ViewEngineResult FindView(ActionContext context, string viewName, bool isMainPage)
//    {
//        //Searches for specific file with extension .cshtml
//        //inside /Views/{ControllerName}/ folder
//        //or
//        //inside /Views/Shared/
//        throw new NotImplementedException();
//    }

//    public ViewEngineResult GetView(string? executingFilePath, string viewPath, bool isMainPage)
//    {
//        throw new NotImplementedException();
//    }
//}

#endregion

#region How Multiple Controller with Same name creates issue w.r.t. Controller Factory

//namespace _000DemoMVC.Controllers.IN
//{
//    public class HomeController : Controller
//    {
//        public HomeController()
//        {

//        }
//        public IActionResult Index()
//        {
//            return View("Index");
//        }
//    }
//}

//namespace _000DemoMVC.Controllers.FR
//{
//    public class HomeController : Controller
//    {
//        public HomeController()
//        {

//        }
//        public IActionResult Index()
//        {
//            return View("Index");
//        }
//    }
//}
#endregion
#region How any controller factory is written

//namespace Helper
//{
//    class MyOwnFactory : IControllerFactory
//    {
//        public object CreateController(ControllerContext context)
//        {
//            if (context.HttpContext.Request.Path.ToString().Contains("Home"))
//            {
//                return new _000DemoMVC.Controllers.HomeController();
//            }
//            else if(............)
//            {

//            }
//            throw new NotImplementedException();
//        }

//        public void ReleaseController(ControllerContext context, object controller)
//        {
//            throw new NotImplementedException();
//        }
//    }
//}
#endregion