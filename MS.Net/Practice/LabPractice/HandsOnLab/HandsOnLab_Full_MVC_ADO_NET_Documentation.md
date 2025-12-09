
# HandsOnLab – ASP.NET Core MVC CRUD Application (ADO.NET)

## 1. Introduction
This document provides a complete end-to-end explanation of an ASP.NET Core MVC CRUD application using ADO.NET and SQL Server LocalDB.

---

## 2. Database Setup (From Scratch)

### Step 2.1: Create Database
```sql
CREATE DATABASE IACSDDB;
GO
```

### Step 2.2: Use Database
```sql
USE IACSDDB;
GO
```

### Step 2.3: Create Emp Table
```sql
CREATE TABLE Emp (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Address NVARCHAR(200) NOT NULL
);
GO
```

---

## 3. Project Creation Steps

1. Open Visual Studio
2. Create new project
3. Choose ASP.NET Core Web App (Model-View-Controller)
4. Name it HandsOnLab
5. Select .NET 6 / 7
6. Create project

---

## 4. Model Layer

### Emp.cs
```csharp
namespace HandsOnLab.Models
{
    public class Emp
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
    }
}
```

---

## 5. Data Access Layer (ADO.NET)

### EmpDAL.cs
```csharp
using Microsoft.Data.SqlClient;

namespace HandsOnLab.Models
{
    public class EmpDAL
    {
        private string connectionString =
            "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";

        public List<Emp> GetEmps()
        {
            List<Emp> list = new List<Emp>();
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            SqlCommand cmd = new SqlCommand("select * from Emp", connection);
            SqlDataReader reader = cmd.ExecuteReader();

            while (reader.Read())
            {
                Emp emp = new Emp();
                emp.Id = Convert.ToInt32(reader["Id"]);
                emp.Name = reader["Name"].ToString();
                emp.Address = reader["Address"].ToString();
                list.Add(emp);
            }
            return list;
        }

        public int AddEmps(Emp emp)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            string query = $"insert into Emp(Name,Address) values('{emp.Name}','{emp.Address}')";
            SqlCommand cmd = new SqlCommand(query, connection);
            return cmd.ExecuteNonQuery();
        }

        public Emp GetEmp(int id)
        {
            return GetEmps().First(e => e.Id == id);
        }

        public int UpdateEmp(Emp emp)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            string query = $"update Emp set Name='{emp.Name}', Address='{emp.Address}' where Id={emp.Id}";
            SqlCommand cmd = new SqlCommand(query, connection);
            return cmd.ExecuteNonQuery();
        }

        public int Delete(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);
            connection.Open();
            string query = $"delete from Emp where Id={id}";
            SqlCommand cmd = new SqlCommand(query, connection);
            return cmd.ExecuteNonQuery();
        }
    }
}
```

---

## 6. Controller Layer

### HomeController.cs
```csharp
using HandsOnLab.Models;
using Microsoft.AspNetCore.Mvc;

namespace HandsOnLab.Controllers
{
    public class HomeController : Controller
    {
        EmpDAL dbObj = new EmpDAL();

        public IActionResult Index()
        {
            return View(dbObj.GetEmps());
        }

        public IActionResult Create()
        {
            return View();
        }

        public IActionResult AfterCreate(Emp emp)
        {
            dbObj.AddEmps(emp);
            return RedirectToAction("Index");
        }

        public IActionResult Edit(int id)
        {
            return View(dbObj.GetEmp(id));
        }

        public IActionResult AfterEdit(Emp emp)
        {
            dbObj.UpdateEmp(emp);
            return RedirectToAction("Index");
        }

        public IActionResult Delete(int id)
        {
            dbObj.Delete(id);
            return RedirectToAction("Index");
        }
    }
}
```

---

## 7. Views

### Index.cshtml
```razor
@model List<HandsOnLab.Models.Emp>
```

### Create.cshtml
```html
<form method="post" action="/Home/AfterCreate">
    <input name="Name" />
    <input name="Address" />
    <button type="submit">Save</button>
</form>
```

### Edit.cshtml
```razor
@model HandsOnLab.Models.Emp
<form method="post" action="/Home/AfterEdit">
    <input type="hidden" name="Id" value="@Model.Id" />
    <input name="Name" value="@Model.Name" />
    <input name="Address" value="@Model.Address" />
    <button type="submit">Update</button>
</form>
```

---

## 8. Program.cs

```csharp
var builder = WebApplication.CreateBuilder(args);
builder.Services.AddControllersWithViews();
var app = builder.Build();
app.UseRouting();
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");
app.Run();
```

---

## 9. End-to-End Flow

Browser → Controller → DAL → Database → Model → View → Browser

---

✅ This document is suitable for:
- Lab submission
- Interviews
- Revision
