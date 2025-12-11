
# 📘 COMPLETE ASP.NET CORE MVC PROJECT BOOK  
## **_001DemoMVC – Super Beginner Guide + Deep Technical Documentation**  
### **Clean, Polished, and Professionally Structured**

---

# 🧭 Table of Contents

1. Introduction  
2. Tools Installation  
3. Creating the Project  
4. Understanding MVC Structure  
5. Database Model: Emp  
6. EFDBContext  
7. BaseController  
8. AuthFilter  
9. Login System (User Model, LoginController, SignIn View)  
10. HomeController (Full CRUD)  
11. Razor Views (Index, Create, Edit, Error)  
12. API Controllers (EmpsController, LogController)  
13. Exception Handling (MyExceptionHandler, ExceptionController)  
14. Program.cs Pipeline Deep Explanation  
15. Application Flow (Request → Filters → Controller → View)  
16. How All Files Work Together  
17. Final Summary  

---

# ⭐ 1. Introduction

This document explains the complete ASP.NET Core MVC project **_001DemoMVC** in a structured, beginner-friendly and deeply technical way.

It includes:

- Every file  
- Every controller  
- Every filter  
- Every view  
- Every line explained in depth  
- Clear flow diagrams (text form)  
- How the entire system works end-to-end  

No unused code (Trainer/Subject removed).  
This is a clean final document.

---

# 🛠️ 2. Installing Tools

You need:

### ✔ Visual Studio 2022  
Install workload: **ASP.NET and Web Development**

### ✔ SQL Server Express + LocalDB  
Used by connection string:
```
(LocalDB)\MSSQLLocalDB
```

### ✔ .NET 6 or 7 SDK  

---

# 🏗️ 3. Creating the Project  
Steps:

1. Open Visual Studio  
2. Create new project → "ASP.NET Core Web App (MVC)"  
3. Name project: `_001DemoMVC`  
4. Choose .NET 6 or 7  
5. Click Create  

VS generates:

```
Controllers/
Models/
Views/
wwwroot/
Program.cs
appsettings.json
```

---

# 🧱 4. MVC Structure Overview

### Model  
Classes representing database tables.

### View  
Razor pages for UI.

### Controller  
Classes responding to browser requests.

### Filters  
Code that runs before or after controller actions.

---

# 🧩 5. Emp Model (Full Deep Explanation)

File: **Models/Emp.cs**

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

### Line-by-line Explanation  
- `[Table("Employee")]` → maps class to SQL table  
- `[Key]` → primary key  
- `[Column(…)]` → binds property to column  
- `[StringLength]` → validation + DB constraint  

This model represents an employee row.

---

# 🗄️ 6. EFDBContext (Database Bridge)

File: **Models/EFDBContext.cs**

```csharp
public class EFDBContext: DbContext
{
    public DbSet<Emp> Emps { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        optionsBuilder.UseSqlServer("Data Source=(LocalDB)\MSSQLLocalDB;Initial Catalog=EFDB;Integrated Security=True;");
    }
}
```

### Deep Explanation  
- Inherits from **DbContext**, EF Core’s main database class  
- `DbSet<Emp>` represents **Employee table**  
- `UseSqlServer()` connects to SQL Server LocalDB  
- Overrides OnConfiguring → provides connection details  

---

# 🧱 7. BaseController

```csharp
[AuthFilter]
public class BaseController : Controller
{
}
```

### Why it exists:
Every controller inheriting BaseController automatically becomes **protected by login**.

---

# 🔐 8. AuthFilter (Authentication System)

```csharp
public class AuthFilter : ActionFilterAttribute
{
    public override void OnActionExecuting(ActionExecutingContext context)
    {
        string IsLoggedIn = context.HttpContext.Session.GetString("IsLoggedIn");
        if (IsLoggedIn == null || IsLoggedIn != "true")
        {
            context.HttpContext.Response.Redirect("/Login/SignIn");
        }
    }
}
```

### What it does:
- Runs BEFORE any controller action  
- Checks if user is logged in  
- If not → redirect to login page  

This protects HomeController.

---

# 🔑 9. Login System

## 9.1 User Model

```csharp
public class User
{
    public string UserName { get; set; }
    public string Password { get; set; }
}
```

Simple container for login form values.

---

## 9.2 LoginController

```csharp
public class LoginController : Controller
{
    public IActionResult SignIn()
    {
        return View();
    }

    [HttpPost]
    public IActionResult SignIn(User user)
    {
        if (user.UserName == "mahesh" && user.Password == "mahesh@123")
        {
            HttpContext.Session.SetString("IsLoggedIn", "true");
            HttpContext.Session.SetString("UserName", user.UserName);

            return Redirect("/Home/Index");
        }
        else
        {
            ViewBag.ErrorMessage = "May be credentials are wrong!";
            return View();
        }
    }

    public IActionResult SignOut()
    {
        HttpContext.Session.Remove("IsLoggedIn");
        HttpContext.Session.Remove("UserName");
        return Redirect("/Login/SignIn");
    }
}
```

### Deep Explanation  
- GET SignIn → shows login form  
- POST SignIn → checks hardcoded credentials  
- If match → creates session values  
- SignOut → clears session  

---

## 9.3 SignIn.cshtml

Login page with form binding to LoginController.

Displays errors via `ViewBag`.

---

# 🏠 10. HomeController (Full CRUD)

### This is the heart of the application.

```csharp
public class HomeController : BaseController
{
    EFDBContext dbObject = new EFDBContext();

    public IActionResult Index()
    {
        string loggedInUserName = HttpContext.Session.GetString("UserName");
        ViewBag.Username = loggedInUserName;

        List<Emp> emps = dbObject.Emps.ToList();
        return View("Index", emps);
    }

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

    public IActionResult Edit(int id)
    {
        Emp emp = dbObject.Emps.Find(id);
        return View("Edit", emp);
    }

    public IActionResult AfterEdit(Emp emp)
    {
        Emp empBeingUpdated = dbObject.Emps.Find(emp.no);
        empBeingUpdated.name = emp.name;
        empBeingUpdated.address = emp.address;

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
}
```

### Explanation  
- Index → Displays employee list  
- Create → Shows form  
- AfterCreate → Saves new employee  
- Edit → Loads existing employee  
- AfterEdit → Updates values  
- Delete → Removes employee  

---

# 📝 11. Razor Views (UI Pages)

## 11.1 Index.cshtml  
Shows table of employees.

## 11.2 Create.cshtml  
Form for adding employees.

## 11.3 Edit.cshtml  
Form with pre-filled employee data.

## 11.4 Error.cshtml  
Shown when exceptions occur.

---

# 🌐 12. API Controllers

## 12.1 EmpsController  
Provides JSON-based CRUD operations for external API calls.

## 12.2 LogController  
Writes logs via FileLogger.

---

# ⚠️ 13. Exception Handling

## MyExceptionHandler  
Logs and redirects errors.

## ExceptionController  
Displays Error view.

---

# 🌍 14. Program.cs Pipeline (Deep Breakdown)

- `UseExceptionHandler()` → enables global error UI  
- `UseStaticFiles()` → serves CSS/JS  
- `UseRouting()` → activates routing  
- `UseSession()` → required for login  
- `UseCors()` → enables cross-domain API usage  
- `MapControllerRoute()` → controls URL → controller mapping  

This forms the core HTTP request pipeline.

---

# 🔄 15. Application Request Flow (Detailed)

```
Browser Request
     ↓
Routing System
     ↓
Global Exception Handler
     ↓
AuthFilter (if controller inherits BaseController)
     ↓
Controller Action runs
     ↓
Database access (via EFDBContext)
     ↓
Return View or Redirect
```

---

# 🧩 16. How All Files Work Together

- LoginController sets session  
- AuthFilter checks session  
- BaseController ensures all Home pages require login  
- HomeController performs CRUD using EFDBContext  
- Views present UI  
- APIs allow external access  
- Program.cs ties everything together  

---

# 🏁 17. Final Summary

You now have a fully working, fully documented MVC project with:

- Authentication  
- EF Core database handling  
- CRUD  
- Razor views  
- Filters  
- Exception handling  
- APIs  
- Logging  

This is a complete MVC educational project.

---

