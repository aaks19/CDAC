using _001DemoMVC.Filters;

namespace _001DemoMVC
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
           
            // Add services to the container.
            builder.Services.AddControllersWithViews();

            //builder.Services.AddExceptionHandler<MyExceptionHandler>();
            builder.Services.AddSession();
            builder.Services.AddCors((corsBuilder) => {
                
                corsBuilder.AddPolicy("Policy1", (policyOptions) => 
                {
                    policyOptions.WithOrigins("*")
                                 .WithMethods("*")
                                 .WithHeaders("*");
                });

                corsBuilder.AddPolicy("Policy2", (policyOptions) =>
                {
                    policyOptions.WithOrigins("amazon.com")
                                 .WithMethods("GET, POST")
                                 .WithHeaders("*");
                });


            });

            var app = builder.Build();
            
            //app.UseExceptionHandler("/Exception/Error");
            app.UseStaticFiles();
    

            app.UseRouting();
            
            app.UseSession();

            app.UseCors();

            app.UseAuthorization();

            app.MapControllerRoute(
                name: "default",
                pattern: "{controller=Test}/{action=Index}/{id?}");

            app.Run();
        }
    }
}
