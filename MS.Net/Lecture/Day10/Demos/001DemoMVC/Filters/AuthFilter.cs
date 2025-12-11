using LoggerLib;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Mvc.Filters;

namespace _001DemoMVC.Filters
{
    public class AuthFilter  : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            string IsLoggedIn = context.HttpContext.Session.GetString("IsLoggedIn");
            if (IsLoggedIn ==null || IsLoggedIn!= "true")
            {
                context.HttpContext.Response.Redirect("/Login/SignIn");
            }
        }
    }

 
}
