using _001DemoMVC.Filters;
using _001DemoMVC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ModelBinding;

namespace _001DemoMVC.Controllers
{
    public class DemoController : Controller
    {
        EFDBContext dbContext = new EFDBContext();
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [IACSDFilter]
        public IActionResult Create(Emp emp)
        {
            if (ModelState.IsValid)
            {
                dbContext.Emps.Add(emp);
                dbContext.SaveChanges();

                return new ContentResult()
                {
                    Content = "Done!",
                    ContentType = "text/plain"
                };
            }
            else 
            { 
                return View(emp); 
            }
        }

        public IActionResult Index()
        {
            return View();
        }

        [HttpPost]
        [IACSDFilter]
        public IActionResult Index([ModelBinder(typeof(MyModelBinder))] 
                                    List<Emp> emps)
        {
            return new ContentResult()
            {
                Content = "Done!",
                ContentType = "text/plain"
            };
        }
    }

    public class MyModelBinder : IModelBinder
    {
        public Task BindModelAsync(ModelBindingContext bindingContext)
        {
            List<Emp> emps = new List<Emp>();

            Emp emp1 = new Emp();
            emp1.name = bindingContext.ValueProvider.GetValue("Name1").Values[0];
            emp1.address = bindingContext.ValueProvider.GetValue("Address1").Values[0];

            Emp emp2 = new Emp();
            emp2.name = bindingContext.ValueProvider.GetValue("Name2").Values[0];
            emp2.address = bindingContext.ValueProvider.GetValue("Address2").Values[0];

            emps.Add(emp1);
            emps.Add(emp2);

            bindingContext.Result = ModelBindingResult.Success(emps);

            return Task.CompletedTask;
        }
    }
}
