# Build `_001DemoMVC` from scratch — A beginner-friendly, step-by-step guide

This guide shows **how I (as a beginner programmer)** would write this ASP.NET Core MVC project from scratch. It explains *why* each step exists, *what* to type, and *what happens* as you write code. At the end you'll have all the source files needed for the demo employee-management app (MVC + Web API + EF Core + simple session login + filters).

> This guide assumes you have .NET SDK (6/7/8) installed and a working development environment (Windows recommended for LocalDB).

---

## High-level plan (what we will build and why)
1. Create the project skeleton with `dotnet new mvc`.
2. Add folders: `Controllers`, `Models`, `Filters`, `Views` (these will exist by default in an MVC project) and a `LoggerLib` helper (or a simple logger class).
3. Add EF Core and configure a local SQL Server database (LocalDB) with `EFDBContext` and model classes (`Emp`, `Trainer`, `Subject`).
4. Create controllers: `HomeController` (MVC UI), `LoginController`, `ExceptionController`, `BaseController` (to attach `AuthFilter`), `EmpsController` (Web API), `LogController` (logging API).
5. Implement filters: `AuthFilter`, `IACSDFilter`, `MyExceptionHandler`.
6. Create Razor views for list, create, edit, sign-in and error pages.
7. Wire middleware in `Program.cs`: sessions, CORS, exception handler, routing.
8. Run the app and test flows (create, edit, delete, sign-in, API endpoints).

---

## 0) Create the project
Open a terminal and run:
```bash
dotnet new mvc -n _001DemoMVC
cd _001DemoMVC
```
This scaffolds a minimal MVC app with `Program.cs`, `Controllers`, `Views`, `wwwroot`, and a `.csproj` file. Read `Program.cs` — we'll replace it later with our final configuration.

If you plan to use EF Core and LocalDB, add the package:
```bash
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
dotnet add package Microsoft.EntityFrameworkCore.Tools
```
(If you plan to run migrations locally, install the EF tool too:
`dotnet tool install --global dotnet-ef`.)

---

## 1) Project structure — create folders
Create the following folders (if they don't already exist):
```
Controllers/
Models/
Filters/
Views/Home/
Views/Shared/
LoggerLib/   (or a simple Logger.cs under a Utilities folder)
```
We'll keep Logger simple: a static `FileLogger` writing to a file for the demo.

---

## 2) Implement the models and DbContext (EF Core)
Create `Models/Emp.cs` and `Models/EFDBContext.cs`. For a beginner-friendly pattern we **register DbContext with DI** and provide a constructor that accepts `DbContextOptions`.

**Why this design?** Using DI lets ASP.NET Core manage DbContext lifecycle (scoped per request) and makes testing easier.

`Models/Emp.cs` — create the entity (use PascalCase for property names as standard C# convention):
```csharp
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace _001DemoMVC.Models
{
    [Table("Employee")]
    public class Emp
    {
        [Column("No", TypeName = "int")]
        [Key]
        public int No { get; set; }

        [Column("Name", TypeName = "varchar")]
        [StringLength(50)]
        public string Name { get; set; }

        [Column("Address", TypeName = "varchar")]
        [StringLength(50)]
        public string Address { get; set; }
    }
}
```

`Models/Trainer.cs` and `Models/Subject.cs` (optional, for relationships):
```csharp
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Collections.Generic;

namespace _001DemoMVC.Models
{
    [Table("Trainer")]
    public class Trainer
    {
        [Column("TID", TypeName = "int")]
        [Key]
        public int TrainerID { get; set; }

        [Column("TName", TypeName = "varchar")]
        [StringLength(50)]
        public string TrainerName { get; set; }

        public List<Subject> Subjects { get; set; }
    }

    [Table("Subject")]
    public class Subject
    {
        [Column("SID", TypeName = "int")]
        [Key]
        public int SubjectID { get; set; }

        [Column("SName", TypeName = "varchar")]
        [StringLength(50)]
        public string SubjectName { get; set; }

        public List<Trainer> Trainers { get; set; }
    }
}
```

`Models/EFDBContext.cs` — DI-friendly DbContext:
```csharp
using Microsoft.EntityFrameworkCore;

namespace _001DemoMVC.Models
{
    public class EFDBContext : DbContext
    {
        public EFDBContext(DbContextOptions<EFDBContext> options) : base(options)
        {
        }

        public DbSet<Emp> Emps { get; set; }
        public DbSet<Trainer> Trainers { get; set; }
        public DbSet<Subject> Subjects { get; set; }

        // Optional: use OnModelCreating to configure relationships or naming if needed.
    }
}
```

**What to do next:** Add a connection string in `appsettings.json`:
```json
"ConnectionStrings": {
  "DefaultConnection": "Data Source=(LocalDB)\MSSQLLocalDB;Initial Catalog=EFDB;Integrated Security=True;"
}
```

Then in `Program.cs` (we'll add code later) register the DbContext:
```csharp
builder.Services.AddDbContext<EFDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));
```

---

## 3) Simple File Logger (LoggerLib or Utilities/FileLogger.cs)
Create a tiny logger to write messages to a file for demo purposes. In production, use `ILogger<T>` and structured logging.
```csharp
using System;
using System.IO;

namespace LoggerLib
{
    public class FileLogger
    {
        private readonly string _filePath;
        private static FileLogger _current;
        public static FileLogger CurrentLogger => _current ??= new FileLogger(Path.Combine(AppContext.BaseDirectory, "app.log"));

        private FileLogger(string path)
        {
            _filePath = path;
        }

        public void Log(string message)
        {
            var line = $"{DateTime.UtcNow:O} {message}";
            File.AppendAllText(_filePath, line + Environment.NewLine);
        }
    }
}
```
**Why this:** Beginner-friendly, synchronous logging. For production prefer `ILogger` or asynchronous logging.

---

## 4) Filters — AuthFilter, IACSDFilter, MyExceptionHandler
Create `Filters/AuthFilter.cs`:
```csharp
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;

namespace _001DemoMVC.Filters
{
    public class AuthFilter : ActionFilterAttribute
    {
        public override void OnActionExecuting(ActionExecutingContext context)
        {
            var isLoggedIn = context.HttpContext.Session.GetString("IsLoggedIn");
            if (string.IsNullOrEmpty(isLoggedIn) || isLoggedIn != "true")
            {
                context.Result = new RedirectResult("/Login/SignIn");
            }
        }
    }
}
```
**Note:** Using `context.Result = new RedirectResult(...)` cleanly cancels the action execution.

Create `Filters/IACSDFilter.cs` (simple lifecycle logger):
```csharp
using Microsoft.AspNetCore.Mvc.Filters;
using LoggerLib;

namespace _001DemoMVC.Filters
{
    public class IACSDFilter : Attribute, IActionFilter, IResultFilter
    {
        public void OnActionExecuted(ActionExecutedContext context)
        {
            FileLogger.CurrentLogger.Log(context.HttpContext.Request.Path + " call successful.");
        }

        public void OnActionExecuting(ActionExecutingContext context)
        {
            FileLogger.CurrentLogger.Log(context.HttpContext.Request.Path + " is about to get called.");
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
```

Create `Filters/MyExceptionHandler.cs` (for centralized exception handling):
```csharp
using Microsoft.AspNetCore.Diagnostics;
using LoggerLib;
using System.Threading;
using System;
using System.Threading.Tasks;

namespace _001DemoMVC.Filters
{
    public class MyExceptionHandler : IExceptionHandler
    {
        public ValueTask<bool> TryHandleAsync(HttpContext httpContext, Exception exception, CancellationToken cancellationToken)
        {
            FileLogger.CurrentLogger.Log("Exception: " + exception.Message);
            FileLogger.CurrentLogger.Log(exception.StackTrace ?? "No stack trace");

            // Redirect to friendly error page
            httpContext.Response.Redirect("/Exception/Error");
            return ValueTask.FromResult(true);
        }
    }
}
```

---

## 5) Controllers — step-by-step (best practices for beginners)

### 5.1 BaseController
Create `Controllers/BaseController.cs` to centralize filters:
```csharp
using _001DemoMVC.Filters;
using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    [AuthFilter] // apply auth to all derived controllers
    public class BaseController : Controller
    {
    }
}
```
**Why:** Avoid repeating `[AuthFilter]` on each controller.

### 5.2 HomeController — MVC UI (use DI and async patterns)
Create `Controllers/HomeController.cs`:
```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;
using System.Linq;
using System.Collections.Generic;

namespace _001DemoMVC.Controllers
{
    public class HomeController : BaseController
    {
        private readonly EFDBContext _db;
        public HomeController(EFDBContext db)
        {
            _db = db;
        }

        public async Task<IActionResult> Index()
        {
            var loggedInUserName = HttpContext.Session.GetString("UserName");
            ViewBag.Username = loggedInUserName;

            List<Emp> emps = await _db.Emps.ToListAsync();
            return View("Index", emps);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> AfterCreate(Emp emp)
        {
            if (!ModelState.IsValid) return View("Create", emp);

            await _db.Emps.AddAsync(emp);
            await _db.SaveChangesAsync();
            return RedirectToAction("Index");
        }

        public async Task<IActionResult> Edit(int id)
        {
            var emp = await _db.Emps.FindAsync(id);
            if (emp == null) return NotFound();
            return View("Edit", emp);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> AfterEdit(Emp emp)
        {
            if (!ModelState.IsValid) return View("Edit", emp);
            var empBeingUpdated = await _db.Emps.FindAsync(emp.No);
            if (empBeingUpdated == null) return NotFound();

            empBeingUpdated.Name = emp.Name;
            empBeingUpdated.Address = emp.Address;
            await _db.SaveChangesAsync();

            return RedirectToAction("Index");
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Delete(int id)
        {
            var emp = await _db.Emps.FindAsync(id);
            if (emp == null) return NotFound();

            _db.Emps.Remove(emp);
            await _db.SaveChangesAsync();

            return RedirectToAction("Index");
        }
    }
}
```

**Explanation while writing:**  
- Constructor accepts `EFDBContext` so the framework injects it automatically.  
- `Index` uses `ToListAsync()` to avoid blocking threads.  
- POST actions use `[ValidateAntiForgeryToken]` for CSRF protection; add `@Html.AntiForgeryToken()` in view forms.  
- `Delete` is handled as `POST` to avoid deletion via a GET request.

### 5.3 LoginController — simple session auth (beginner friendly)
Create `Controllers/LoginController.cs`:
```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;

namespace _001DemoMVC.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult SignIn()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult SignIn(User user)
        {
            // In a real app verify against DB and use hashing
            if (user.UserName == "mahesh" && user.Password == "mahesh@123")
            {
                HttpContext.Session.SetString("IsLoggedIn", "true");
                HttpContext.Session.SetString("UserName", user.UserName);
                return RedirectToAction("Index", "Home");
            }

            ViewBag.ErrorMessage = "May be credentials are wrong!";
            return View();
        }

        public IActionResult SignOut()
        {
            HttpContext.Session.Remove("IsLoggedIn");
            HttpContext.Session.Remove("UserName");
            return RedirectToAction("SignIn");
        }
    }
}
```
**Why session?** Simple to understand. Later, swap in ASP.NET Identity.

### 5.4 EmpsController — Web API (use DI and async, return proper HTTP responses)
Create `Controllers/EmpsController.cs`:
```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;
using System.Collections.Generic;

namespace _001DemoMVC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EmpsController : ControllerBase
    {
        private readonly EFDBContext _db;
        public EmpsController(EFDBContext db) { _db = db; }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Emp>>> Get()
        {
            return await _db.Emps.ToListAsync();
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Emp>> Get(int id)
        {
            var emp = await _db.Emps.FindAsync(id);
            if (emp == null) return NotFound();
            return emp;
        }

        [HttpPost]
        public async Task<ActionResult<Emp>> Post(Emp emp)
        {
            _db.Emps.Add(emp);
            await _db.SaveChangesAsync();
            return CreatedAtAction(nameof(Get), new { id = emp.No }, emp);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, Emp emp)
        {
            var existing = await _db.Emps.FindAsync(id);
            if (existing == null) return NotFound();

            existing.Name = emp.Name;
            existing.Address = emp.Address;
            await _db.SaveChangesAsync();
            return NoContent();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var emp = await _db.Emps.FindAsync(id);
            if (emp == null) return NotFound();

            _db.Emps.Remove(emp);
            await _db.SaveChangesAsync();
            return NoContent();
        }
    }
}
```
**Why these changes?** Proper HTTP codes, async calls, DI — these are beginner-appropriate best practices.

### 5.5 LogController — small API to accept logs (optional)
Create `Controllers/LogController.cs`:
```csharp
using Microsoft.AspNetCore.Mvc;
using LoggerLib;

namespace _001DemoMVC.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LogController : ControllerBase
    {
        [HttpPost]
        public IActionResult Post([FromBody] LogModel logModel)
        {
            if (logModel == null || string.IsNullOrWhiteSpace(logModel.LogMessage))
                return BadRequest();

            FileLogger.CurrentLogger.Log(logModel.LogMessage);
            return Ok();
        }
    }

    public class LogModel { public string LogMessage { get; set; } }
}
```

---

## 6) Razor Views — Create, Edit, Index, SignIn, Error
For brevity, use basic views similar to the ones you provided but slightly improved with tag helpers and anti-forgery tokens.

Example `Views/Home/Create.cshtml`:
```cshtml
@model _001DemoMVC.Models.Emp
@{
    ViewData["Title"] = "Create";
}
<form asp-action="AfterCreate" method="post">
    @Html.AntiForgeryToken()
    <div class="form-group">
        <label asp-for="Name"></label>
        <input asp-for="Name" class="form-control" />
        <span asp-validation-for="Name" class="text-danger"></span>
    </div>
    <div class="form-group">
        <label asp-for="Address"></label>
        <input asp-for="Address" class="form-control" />
        <span asp-validation-for="Address" class="text-danger"></span>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
</form>
```

`Views/Home/Edit.cshtml` — similar, use `asp-for` and anti-forgery, and include a hidden field for `No`.

`Views/Home/Index.cshtml` — iterate over model (list of `Emp`) and present actions; for Delete, use a small form with `method="post"` to call the `Delete` POST action.

`Views/Login/SignIn.cshtml` — include `@Html.AntiForgeryToken()` and post to `SignIn`.

`Views/Shared/Error.cshtml` — friendly error page.

---

## 7) Program.cs — wire everything (DI, sessions, CORS, exception handler)
Replace `Program.cs` contents with the following (explained inline):
```csharp
using _001DemoMVC.Filters;
using _001DemoMVC.Models;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services
builder.Services.AddControllersWithViews();

// Register DbContext with connection string from appsettings.json
builder.Services.AddDbContext<EFDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Register our exception handler
builder.Services.AddExceptionHandler<MyExceptionHandler>();

// Add session support
builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30);
    options.Cookie.HttpOnly = true;
    options.Cookie.IsEssential = true;
});

// Configure CORS (development-friendly)
builder.Services.AddCors(policy =>
{
    policy.AddPolicy("Policy1", p => p.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader());
});

var app = builder.Build();

// Middleware pipeline
app.UseExceptionHandler("/Exception/Error"); // central error UI
app.UseStaticFiles();
app.UseRouting();
app.UseSession();
app.UseCors("Policy1");
app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
```

**Why these choices?**
- Registering DbContext with DI ensures proper lifetimes and easier testing.
- Session configured with sensible defaults and `HttpOnly` cookies.
- CORS opens for development; lock down for production.
- UseExceptionHandler provides unified error handling.

---

## 8) Database — migrations and seeding (optional)
To create DB from models:
```bash
dotnet ef migrations add InitialCreate
dotnet ef database update
```
This will create the `EFDB` database and tables based on your models. Verify with SSMS or Visual Studio Server Explorer.

---

## 9) Testing the app (manual checklist)
1. `dotnet run` — open browser at `https://localhost:5001` (or the port shown).  
2. Visit `/Login/SignIn`, sign in with `mahesh` / `mahesh@123`.  
3. Use the Home UI to create, edit, delete employees.  
4. Check API: `GET /api/Emps` returns JSON list.  
5. Trigger an exception (introduce `throw new Exception("test")` temporarily) and see `/Exception/Error` and logs in `app.log`.  

---

## 10) Summary — what you learned by writing it yourself
- How to scaffold an ASP.NET Core MVC app and add EF Core.  
- How DI simplifies resource management (DbContext injection).  
- Why async DB calls matter for scalability.  
- How to protect POST endpoints with anti-forgery tokens.  
- Why middleware ordering, CORS, and exception handling are important.  
- Where to improve for production (authentication, logging, validation, security).

---

## Full source files (copy-paste ready)
Below are the final source files we built above. You can copy these into the corresponding files in your project.

### Program.cs
```csharp
// (full Program.cs shown earlier — copy the Program.cs block from the guide above)
```

### Models/Emp.cs
```csharp
// (copy Emp.cs block from above)
```

### Models/EFDBContext.cs
```csharp
// (copy EFDBContext block from above)
```

### Controllers/HomeController.cs
```csharp
// (copy HomeController block from above)
```

### Controllers/EmpsController.cs
```csharp
// (copy EmpsController block from above)
```

...and so on for LoginController, LogController, Filters, Views.

---

## Final notes and next steps
If you'd like, I can:
- Produce a downloadable ZIP that contains all files with the exact folder layout.  
- Convert this Markdown to a PDF for printing or sharing.  
- Generate ready-to-run project files (I can create a zipped project with `Program.cs`, `csproj`, models, controllers and views).  

Tell me which of those you want next and I’ll generate it.  
Happy coding!