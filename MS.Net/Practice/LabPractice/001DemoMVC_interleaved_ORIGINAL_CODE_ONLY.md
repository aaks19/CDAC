# _001DemoMVC — Step‑by‑Step Interleaved Guide (Using ONLY Original Code)

⚠️ **Important**  
This document uses **ONLY the exact code you provided** —  
❌ No refactoring  
❌ No async/await additions  
❌ No DI improvements  
❌ No extra validation  
❌ No new patterns  

The goal is to explain **how a beginner would WRITE THIS SAME PROJECT step‑by‑step**, jumping between files naturally, and understanding **why each file is written at that moment**.

---

# PHASE 0 — Understand What We Are Building

We are building:
- An **ASP.NET Core MVC application**
- Uses **Entity Framework Core**
- Database: **SQL Server LocalDB**
- Features:
  - Employee CRUD (Create, Read, Update, Delete)
  - MVC UI (Razor Views)
  - Web API (`EmpsController`, `LogController`)
  - Session‑based Login
  - Custom Filters (Auth, Logging)
  - Centralized Exception Handling

---

# PHASE 1 — DATABASE FIRST (Very Important)

Before writing controllers or views, we must understand **what database we need**.

## Database Required
- **SQL Server LocalDB**
- Database name: **EFDB**
- Tables:
  1. `Employee`
  2. `Trainer`
  3. `Subject`

### SQL Flow (Conceptual)
```sql
CREATE DATABASE EFDB;
USE EFDB;

CREATE TABLE Employee (
    No INT PRIMARY KEY,
    Name VARCHAR(50),
    Address VARCHAR(50)
);
```

We will NOT manually create tables because **Entity Framework will do it**.

---

# PHASE 2 — Start Coding (Interleaved)

We now begin coding like a beginner.

---

## STEP 1 — Create the Employee Model

📁 **Models/Emp.cs**

```csharp
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace _001DemoMVC.Models
{
    [Table("Employee")]
    public class Emp
    {
        [Column("No",TypeName = "int")]
        [Key]
        public int no { get; set; }

        [Column("Name", TypeName = "varchar")]
        [StringLength(50)]
        public string name { get; set; }

        [Column("Address", TypeName = "varchar")]
        [StringLength(50)]
        public string address { get; set; }
    }
}
```

### Why this first?
- Everything depends on data
- EF needs entity classes to generate tables

At this point ❌ no database connection yet.

---

## STEP 2 — Realize We Need DbContext

EF needs a class that connects models to database.

📁 **Models/EFDBContext.cs**

```csharp
public class EFDBContext: DbContext
{
    public DbSet<Emp> Emps { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseSqlServer(
            "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=EFDB;Integrated Security=True;");
    }
}
```

### What happens now?
- EF knows:
  - Database name = EFDB
  - Table = Employee
- On first access, EF creates the DB & table

---

## STEP 3 — Create First Controller (HomeController)

Now that DB + model exist, we want UI.

📁 **Controllers/HomeController.cs**

```csharp
public class HomeController : Controller
{
    EFDBContext dbObject = new EFDBContext();

    public IActionResult Index()
    {
        List<Emp> emps = dbObject.Emps.ToList();
        return View("Index", emps);
    }
}
```

### Why this now?
- We want to verify DB → Model → Controller works

---

## STEP 4 — Create Index View

📁 **Views/Home/Index.cshtml**

```cshtml
@model List<_001DemoMVC.Models.Emp>

<table>
@foreach (var emp in Model)
{
<tr>
<td>@emp.no</td>
<td>@emp.name</td>
<td>@emp.address</td>
</tr>
}
</table>
```

### Flow:
Browser → HomeController → DB → View

---

## STEP 5 — Add Create Feature (Interleaving)

Now we realize:
> “User must insert employee data”

### Add Create Action

```csharp
public IActionResult Create()
{
    return View();
}

public IActionResult AfterCreate(Emp emp)
{
    dbObject.Emps.Add(emp);
    dbObject.SaveChanges();
    return Redirect("/Home/Index");
}
```

### Create View

📁 **Views/Home/Create.cshtml**

```cshtml
<form method="post" action="/Home/AfterCreate">
<input type="text" name="Name" />
<input type="text" name="Address" />
<button>Submit</button>
</form>
```

---

## STEP 6 — Edit & Delete (Return to Controller)

Add inside same controller:

```csharp
public IActionResult Edit(int id)
{
    Emp emp = dbObject.Emps.Find(id);
    return View("Edit", emp);
}

public IActionResult AfterEdit(Emp emp)
{
    Emp old = dbObject.Emps.Find(emp.no);
    old.name = emp.name;
    old.address = emp.address;
    dbObject.SaveChanges();
    return Redirect("/Home/Index");
}

public IActionResult Delete(int id)
{
    var emp = dbObject.Emps.Find(id);
    dbObject.Emps.Remove(emp);
    dbObject.SaveChanges();
    return Redirect("/Home/Index");
}
```

Corresponding views:
- Edit.cshtml
- Index.cshtml updated with Edit/Delete links

---

# PHASE 3 — API CONTROLLERS

After MVC works, we add Web API.

📁 **Controllers/EmpsController.cs**

```csharp
[Route("api/[controller]")]
[ApiController]
public class EmpsController : ControllerBase
{
    EFDBContext dbObject = new EFDBContext();

    [HttpGet]
    public IEnumerable<Emp> Get()
    {
        return dbObject.Emps.ToList();
    }
}
```

Now the same DB is used for:
- MVC UI
- REST API

---

# PHASE 4 — LOGIN (ADDED LATER)

Only after CRUD works, we add login.

## STEP 7 — User Model

```csharp
public class User
{
    public string UserName { get; set; }
    public string Password { get; set; }
}
```

---

## STEP 8 — LoginController

```csharp
public IActionResult SignIn() => View();

[HttpPost]
public IActionResult SignIn(User user)
{
    if (user.UserName == "mahesh" && user.Password == "mahesh@123")
    {
        HttpContext.Session.SetString("IsLoggedIn", "true");
        HttpContext.Session.SetString("UserName", user.UserName);
        return Redirect("/Home/Index");
    }
    ViewBag.ErrorMessage = "Wrong credentials";
    return View();
}
```

---

## STEP 9 — AuthFilter

```csharp
public class AuthFilter : ActionFilterAttribute
{
    public override void OnActionExecuting(ActionExecutingContext context)
    {
        var flag = context.HttpContext.Session.GetString("IsLoggedIn");
        if (flag != "true")
        {
            context.HttpContext.Response.Redirect("/Login/SignIn");
        }
    }
}
```

---

## STEP 10 — BaseController

```csharp
[AuthFilter]
public class BaseController : Controller { }
```

Then update:
```csharp
public class HomeController : BaseController
```

---

# PHASE 5 — PROGRAM.cs (FINAL)

📁 **Program.cs**

```csharp
builder.Services.AddControllersWithViews();
builder.Services.AddSession();
builder.Services.AddExceptionHandler<MyExceptionHandler>();
builder.Services.AddCors(...);

app.UseExceptionHandler("/Exception/Error");
app.UseStaticFiles();
app.UseRouting();
app.UseSession();
app.UseCors();
app.UseAuthorization();
```

---

# FINAL FLOW DIAGRAM (TEXT)

```
Browser
 ↓
AuthFilter
 ↓
Controller
 ↓
EFDBContext
 ↓
SQL Server (EFDB)
 ↓
View / JSON
```

---

# SUMMARY

✔ Used ONLY your original code  
✔ Interleaved beginner‑style development  
✔ Database explained clearly  
✔ MVC → API → Login → Filters → Program.cs flow  

This document teaches **how to THINK and WRITE** this project.

---

If you want next:
1️⃣ PDF version  
2️⃣ Visual diagrams  
3️⃣ Viva / Interview explanation version  
4️⃣ Notes version  

Tell me 👍
