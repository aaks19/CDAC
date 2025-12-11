# _001DemoMVC — Interleaved step-by-step coding guide

This guide shows a *development-first* approach: we start by writing a small piece (the `Emp` model), then realize we need supporting code (DbContext, views, controllers), write those pieces, then return to extend the original code, and so on. The goal is to simulate how you actually code—building one file partially, switching to another to resolve dependencies, then returning to continue.

Each step contains:
- A short goal,
- The exact code to add or change,
- Why you wrote it,
- What to do next.

---

## Prerequisites
Install .NET SDK and create a new MVC project:

```bash
dotnet new mvc -n _001DemoMVC
cd _001DemoMVC
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
dotnet add package Microsoft.EntityFrameworkCore.Tools
```

Create folders if not present:
```
Controllers/
Models/
Filters/
Views/Home/
Views/Shared/
LoggerLib/ (or Utilities/)
```

---

## Step 1 — Start with the core entity: `Emp` (partial)
**Goal:** Create the `Emp` entity so we can reason about the data shape.

**File:** `Models/Emp.cs` — add this initial code:

```csharp
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace _001DemoMVC.Models
{
    // We'll map to an existing or future table named "Employee"
    [Table("Employee")]
    public class Emp
    {
        [Column("No", TypeName = "int")]
        [Key]
        public int No { get; set; }

        // We'll add validation and display attributes later
        public string Name { get; set; }
        public string Address { get; set; }
    }
}
```

**Why now:** It's often easiest to define the model first, even partially. Later steps will reference `Emp` (DbContext, Views, Controllers).

**Next:** We need a `DbContext` to query/save `Emp`. Create a minimal `EFDBContext` next.

---

## Step 2 — Add `EFDBContext` (partial, DI-ready)
**Goal:** Add a DbContext so later controllers can query `Emp`.

**File:** `Models/EFDBContext.cs` — add:

```csharp
using Microsoft.EntityFrameworkCore;

namespace _001DemoMVC.Models
{
    public class EFDBContext : DbContext
    {
        public EFDBContext(DbContextOptions<EFDBContext> options) : base(options)
        {
        }

        // Expose a DbSet for Emp now that the class exists
        public DbSet<Emp> Emps { get; set; }
    }
}
```

**Why now:** Controllers and scaffolding require a DbContext. We made it constructor-based to support DI in `Program.cs`. At this point we haven't added connection strings; we'll do that when wiring `Program.cs`.

**Next:** We want a simple UI to list employees. Add the `HomeController` skeleton that depends on `EFDBContext`. But we will only scaffold a GET Index action for now.

---

## Step 3 — Create `HomeController` (initial)
**Goal:** Add a controller that will return a view with a list of `Emp`. Use constructor injection.

**File:** `Controllers/HomeController.cs` — add:

```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;

namespace _001DemoMVC.Controllers
{
    public class HomeController : Controller
    {
        private readonly EFDBContext _db;
        public HomeController(EFDBContext db)
        {
            _db = db;
        }

        // Show list of employees (async)
        public async Task<IActionResult> Index()
        {
            List<Emp> emps = await _db.Emps.ToListAsync();
            return View("Index", emps);
        }
    }
}
```

**Why now:** With this controller you can start wiring the view; you will run into compilation errors until `Program.cs` registers EFDBContext — we will do that soon.

**Next:** Create a minimal Index view to render the `List<Emp>`.

---

## Step 4 — Add `Index` view (minimal)
**Goal:** Create the Razor view for Index so the project can render the page.

**File:** `Views/Home/Index.cshtml` — add:

```cshtml
@model List<_001DemoMVC.Models.Emp>
<html>
<head>
    <title>Home - Employees</title>
</head>
<body>
    <h2>Employees</h2>
    <a href="/Home/Create">Create</a>
    <table>
        <thead><tr><th>No</th><th>Name</th><th>Address</th></tr></thead>
        <tbody>
        @foreach (var emp in Model)
        {
            <tr>
                <td>@emp.No</td>
                <td>@emp.Name</td>
                <td>@emp.Address</td>
            </tr>
        }
        </tbody>
    </table>
</body>
</html>
```

**Why now:** Even before DB wiring, this view compiles and demonstrates the expected model. Next we'll wire up `Program.cs` with DI and connection string so the app can run.

---

## Step 5 — Wire `Program.cs` with EF and run (partial)
**Goal:** Register `EFDBContext` in DI and provide connection string in `appsettings.json`.

**File changes:**

1. `appsettings.json` — add connection string:
```json
{
  "ConnectionStrings": {
    "DefaultConnection": "Data Source=(LocalDB)\MSSQLLocalDB;Initial Catalog=EFDB;Integrated Security=True;"
  }
}
```

2. `Program.cs` — update to register DbContext and minimal MVC:

```csharp
using _001DemoMVC.Models;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews();

builder.Services.AddDbContext<EFDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

var app = builder.Build();
app.UseStaticFiles();
app.UseRouting();
app.MapControllerRoute("default", "{controller=Home}/{action=Index}/{id?}");
app.Run();
```

**Why now:** With these changes you can run the app. If the database `EFDB` doesn't exist, create it using migrations or let EF create it. For now, the Index will throw if table empty/nonexistent—so we'll add migrations next or seed data.

**Next:** Create a migration or seed a few `Emp` records programmatically for testing.

---

## Step 6 — Seed sample data (temporary quick seed)
**Goal:** Make the Index page show sample employees without doing EF migrations immediately.

**Option A — Seed at startup in `Program.cs` (quick):**

Modify `Program.cs` after `var app = builder.Build();` and before `app.Run();`:

```csharp
using (var scope = app.Services.CreateScope())
{
    var db = scope.ServiceProvider.GetRequiredService<EFDBContext>();
    db.Database.EnsureCreated(); // creates DB/tables if model changed
    if (!db.Emps.Any())
    {
        db.Emps.AddRange(
            new Emp { Name = "Mahesh", Address = "Pune" },
            new Emp { Name = "Asha", Address = "Mumbai" }
        );
        db.SaveChanges();
    }
}
```

**Why now:** This avoids dealing with EF migrations for a beginner and allows running the app to see the list.

**Next:** Run `dotnet run` and open the app — Index should display the seeded employees.

---

## Step 7 — Add Create form (back to `HomeController`)
**Goal:** Provide a way to add employees from UI. We'll add both the GET (Create view) and POST action. Return to `HomeController` and extend it.

**Modify `HomeController.cs` — add methods:**

```csharp
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
```

**Why now:** After adding the Create action we need the Create view — we'll add it next.

**Next:** Add `Views/Home/Create.cshtml`.

---

## Step 8 — Create the Create view
**File:** `Views/Home/Create.cshtml` — add:

```cshtml
@model _001DemoMVC.Models.Emp
@{
    ViewData["Title"] = "Create Employee";
}
<form asp-action="AfterCreate" method="post">
    @Html.AntiForgeryToken()
    <div>
        <label asp-for="Name"></label>
        <input asp-for="Name" />
        <span asp-validation-for="Name"></span>
    </div>
    <div>
        <label asp-for="Address"></label>
        <input asp-for="Address" />
        <span asp-validation-for="Address"></span>
    </div>
    <button type="submit">Create</button>
</form>
```

**Why now:** The form posts to `AfterCreate`. We added anti-forgery which `ValidateAntiForgeryToken` validates.

**Next:** Test creating an employee and ensure Index now shows new entries.

---

## Step 9 — Add Edit and Delete (back to `HomeController` and views)
**Goal:** Allow editing and deleting an employee using safe HTTP methods.

**Modify `HomeController.cs` to add Edit and Delete actions:**

```csharp
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
    var existing = await _db.Emps.FindAsync(emp.No);
    if (existing == null) return NotFound();
    existing.Name = emp.Name;
    existing.Address = emp.Address;
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
```

**Why now:** Note that Delete is a POST to avoid accidental GET deletion.

**Add views:**
- `Views/Home/Edit.cshtml` similar to Create but pre-filled via `asp-for`.
- In `Index.cshtml`, change Delete link to a small form:

```cshtml
<form asp-action="Delete" method="post" style="display:inline">
    <input type="hidden" name="id" value="@emp.No" />
    <button type="submit">Delete</button>
</form>
```

**Next:** Test Edit and Delete flows.

---

## Step 10 — Add Login (Auth) pieces (partial)
**Goal:** Add simple session-based login so only logged-in users use HomeController.

**Create `Models/User.cs`:**

```csharp
namespace _001DemoMVC.Models
{
    public class User
    {
        public string UserName { get; set; }
        public string Password { get; set; }
    }
}
```

**Create `Filters/AuthFilter.cs`:**

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

**Create `Controllers/LoginController.cs`:**

```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;

namespace _001DemoMVC.Controllers
{
    public class LoginController : Controller
    {
        public IActionResult SignIn() => View();

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult SignIn(User user)
        {
            // temporary: hard-coded validation
            if (user.UserName == "mahesh" && user.Password == "mahesh@123")
            {
                HttpContext.Session.SetString("IsLoggedIn", "true");
                HttpContext.Session.SetString("UserName", user.UserName);
                return RedirectToAction("Index", "Home");
            }
            ViewBag.ErrorMessage = "Invalid credentials";
            return View();
        }

        public IActionResult SignOut()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("SignIn");
        }
    }
}
```

**Why now:** We create the auth flow but haven't yet enabled sessions in `Program.cs`. Let's do that.

---

## Step 11 — Enable sessions and base controller
**Modify `Program.cs` to add session and apply AuthFilter via base controller:**

```csharp
// After builder.Services.AddControllersWithViews();
builder.Services.AddSession();
```

Also create `Controllers/BaseController.cs`:

```csharp
using _001DemoMVC.Filters;
using Microsoft.AspNetCore.Mvc;

namespace _001DemoMVC.Controllers
{
    [AuthFilter]
    public class BaseController : Controller { }
}
```

Then make `HomeController` inherit from `BaseController` (so it is protected by AuthFilter).

**Why now:** Centralizes auth check for all controllers inheriting `BaseController`.

**Next:** Add SignIn view and test login flow.

---

## Step 12 — Add SignIn view and test
**File:** `Views/Login/SignIn.cshtml`:

```cshtml
@model _001DemoMVC.Models.User
<form asp-action="SignIn" method="post">
    @Html.AntiForgeryToken()
    <div>
        <label asp-for="UserName"></label>
        <input asp-for="UserName" />
    </div>
    <div>
        <label asp-for="Password"></label>
        <input asp-for="Password" type="password" />
    </div>
    <button type="submit">Login</button>
    <div style="color:red">@ViewBag.ErrorMessage</div>
</form>
```

**Test:** Run app, go to `/Login/SignIn`, login with `mahesh/mahesh@123`, then access `/Home/Index`. If you are not authenticated, you should be redirected.

---

## Step 13 — Add global exception handling (small)
**Goal:** Add handler to redirect to friendly error page and log.

**Create `Filters/MyExceptionHandler.cs`** (as earlier) and register in `Program.cs`:

```csharp
builder.Services.AddExceptionHandler<MyExceptionHandler>();
app.UseExceptionHandler("/Exception/Error");
```

Also add `Controllers/ExceptionController.cs` with `Error` action returning `Views/Shared/Error.cshtml` (friendly message).

**Why now:** Centralizes error display.

---

## Step 14 — Add API endpoints (EmpsController) — partial
**Goal:** Provide a Web API for `Emp` records for external clients.

**File:** `Controllers/EmpsController.cs` — add:

```csharp
using Microsoft.AspNetCore.Mvc;
using _001DemoMVC.Models;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Threading.Tasks;

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
    // POST, PUT, DELETE similar — add when ready
}
```

**Why partial:** Start with GET endpoints; add POST/PUT/DELETE later when front-end or clients require them.

---

## Step 15 — Add logging controller and lifecycle filter (optional)
**Goal:** Add `LogController` to receive logs and `IACSDFilter` for lifecycle logging.

Add `Controllers/LogController.cs` and `Filters/IACSDFilter.cs` (as previous guide showed). Attach `IACSDFilter` optionally on `BaseController` by uncommenting.

**Why now:** Instrumentation helps debugging.

---

## Step 16 — Iterate and extend — return to models and validation
**Goal:** Now that plumbing exists, improve `Emp` by adding data annotations and consistent property names.

Update `Models/Emp.cs`:

```csharp
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

[Table("Employee")]
public class Emp
{
    [Column("No", TypeName = "int")]
    [Key]
    public int No { get; set; }

    [Column("Name", TypeName = "varchar")]
    [Required]
    [StringLength(50)]
    public string Name { get; set; }

    [Column("Address", TypeName = "varchar")]
    [StringLength(100)]
    public string Address { get; set; }
}
```

**Why now:** Use `[Required]` to prevent empty names; adjust `StringLength` for Address.

**Next:** Add client/server validation scripts in views and test model validation flows (invalid model returns to form with errors).

---

## Step 17 — Harden and refactor (final)
**Checklist of recommended refactors:**
- Replace hard-coded credentials with ASP.NET Core Identity or at least database-backed hashed passwords.
- Replace `FileLogger` with `ILogger<T>` or structured logger (Serilog).
- Remove `EnsureCreated()` in favor of migrations:
  ```
  dotnet ef migrations add Initial
  dotnet ef database update
  ```
- Protect APIs with proper CORS policies and authentication.
- Add unit/integration tests for controllers and filters.

---

## Final notes
This interleaved approach mirrors how you typically build real apps: implement a bit of a model, then create controllers/views that depend on it, then return to the model to add validation or new fields when the UI or DB needs change, and so on.

If you want, I can:
- Produce a ZIP with all final code files structured as a ready-to-run project.
- Convert this Markdown to PDF.
- Generate the full, updated deep explanation file with the interleaved timeline included.

