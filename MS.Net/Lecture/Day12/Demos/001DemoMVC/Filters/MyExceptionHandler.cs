using LoggerLib;
using Microsoft.AspNetCore.Diagnostics;

namespace _001DemoMVC.Filters
{
    public class MyExceptionHandler : IExceptionHandler
    {
        public ValueTask<bool> TryHandleAsync(HttpContext httpContext, Exception exception, CancellationToken cancellationToken)
        {
            //Log the message
            FileLogger.CurrentLogger.Log(exception.Message);
            FileLogger.CurrentLogger.Log("-------------------------");
            FileLogger.CurrentLogger.Log(exception.StackTrace);

            //redirect to common UI
            httpContext.Response.Redirect("/Exception/Error"); 

            //Convey to life cycle - that we have handled the exception
            return ValueTask.FromResult(true); 
        }
    }
}
