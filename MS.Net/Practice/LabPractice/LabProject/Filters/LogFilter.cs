using LabProject.Logger;
using Microsoft.AspNetCore.Mvc.Filters;

namespace LabProject.Filters
{
    public class LogFilter : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            FileLogger.currentLogger.Log("Action Method " + context.ActionDescriptor.DisplayName + " executing at " + DateTime.Now.ToString());
        }

        override public void OnActionExecuted(ActionExecutedContext context)
        {
            FileLogger.currentLogger.Log("Action Method " + context.ActionDescriptor.DisplayName + " executed at " + DateTime.Now.ToString());
        }
    }
}
