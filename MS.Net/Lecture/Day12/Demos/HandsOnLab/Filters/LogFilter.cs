using Microsoft.AspNetCore.Mvc.Filters;
using HandsOnLab.Logger;
namespace HandsOnLab.Filters
{
    public class LogFilter : Attribute, IActionFilter, IResultFilter
    {
        public void OnActionExecuted(ActionExecutedContext context)
        {
           FileLogger.CurrentLogger.Log("Completed Execution of " + 
               context.HttpContext.Request.Path.Value);
        }
        public void OnActionExecuting(ActionExecutingContext context)
        {
            FileLogger.CurrentLogger.Log("Received a call for " +
               context.HttpContext.Request.Path.Value);
        }
        public void OnResultExecuted(ResultExecutedContext context)
        {
            FileLogger.CurrentLogger.Log("UI is processed!");
        }
        public void OnResultExecuting(ResultExecutingContext context)
        {
            FileLogger.CurrentLogger.Log("UI is getting processed!");
        }
    }
}
