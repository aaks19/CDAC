using Microsoft.AspNetCore.Mvc.Filters;
using LoggerLib;
namespace _001DemoMVC.Filters
{
    public class IACSDFilter : Attribute, IActionFilter, IResultFilter
    {
        public void OnActionExecuted(ActionExecutedContext context)
        {
            FileLogger.CurrentLogger.Log(context.HttpContext.Request.Path.Value
 + " call successfull.");
        }

        public void OnActionExecuting(ActionExecutingContext context)
        {
            FileLogger.CurrentLogger.Log(context.HttpContext.Request.Path.Value
   + " is about to get called.");     
        }

        public void OnResultExecuted(ResultExecutedContext context)
        {
            FileLogger.CurrentLogger.Log("UI creation done!");
        }

        public void OnResultExecuting(ResultExecutingContext context)
        {
            FileLogger.CurrentLogger.Log("UI is about to be created!");
        }
    }
}
