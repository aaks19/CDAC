namespace _000DemoMVC
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            builder.Services.AddControllersWithViews();

            var app = builder.Build();

            //app.Use((req, res, next) => { });
            //app.UseSession();
            app.UseStaticFiles();
            //app._______________
            //app._______________
            //app._______________
            //app._______________

            //app.MapControllerRoute(
            //  name: "myroute",
            //  pattern: "{controller=Home}/{action=Index}/{name}/{address}/{id?}");

            app.MapControllerRoute(
                name: "default",
                pattern: "{controller=Home}/{action=Index}/{id?}");

            app.Run();
        }
    }
}
