# HandsOnLab – ASP.NET Core MVC CRUD Application Using ADO.NET

This document is a **complete, deeply explained guide** for your ASP.NET Core MVC application that performs **CRUD operations on an `Emp` table** in a SQL Server LocalDB database using **ADO.NET**.

It includes:

- Database creation (from scratch)
- Table creation
- Step-by-step project and file creation order
- Full source code for:
  - `Emp` model
  - `EmpDAL` (Data Access Layer)
  - `HomeController`
  - Views: `Index`, `Create`, `Edit`
  - `Program.cs`
- Very detailed explanations:
  - Line-by-line
  - What + Why + Flow
  - MVC and ADO.NET jargon

---

## 1. Database Setup From Scratch

Your application uses the following connection string:

```csharp
"Data Source=(LocalDB)\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;"
```

So we must:

1. Create a **database** named `IACSDDB`
2. Create a **table** named `Emp`

### 1.1 Open SQL Server Environment

You can use one of the following:

- **SQL Server Management Studio (SSMS)**  
- **Visual Studio → View → SQL Server Object Explorer**  
- Any SQL client connected to `(LocalDB)\MSSQLLocalDB`

### 1.2 Connect to LocalDB

- Server Name: `(LocalDB)\MSSQLLocalDB`
- Authentication: **Windows Authentication**
- Click **Connect**

This matches your connection string's `Data Source` value.

### 1.3 Create Database `IACSDDB`

Run:

```sql
CREATE DATABASE IACSDDB;
GO
```

- `CREATE DATABASE` physically creates a new database on the server.
- `GO` indicates the end of the batch in tools like SSMS.

### 1.4 Select the Database

```sql
USE IACSDDB;
GO
```

- `USE IACSDDB` makes `IACSDDB` the **current working database**.

### 1.5 Create `Emp` Table

```sql
CREATE TABLE Emp (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Address NVARCHAR(200) NOT NULL
);
GO
```

**Column explanation:**

- `Id INT IDENTITY(1,1) PRIMARY KEY`  
  - `INT` → integer type  
  - `IDENTITY(1,1)` → auto-increment starting from 1  
  - `PRIMARY KEY` → uniquely identifies each row  
- `Name NVARCHAR(100) NOT NULL`  
  - Unicode string up to 100 characters  
  - `NOT NULL` → must always have a value  
- `Address NVARCHAR(200) NOT NULL`  
  - Unicode string up to 200 characters  
  - Cannot be null

You can test the table by inserting a sample row:

```sql
INSERT INTO Emp (Name, Address)
VALUES ('Test User', 'Pune');

SELECT * FROM Emp;
```

Now the **database layer is fully ready**.

---

## 2. Creating the ASP.NET Core MVC Project

### 2.1 Create the Project

1. Open **Visual Studio**
2. Click **Create a new project**
3. Choose **ASP.NET Core Web App (Model-View-Controller)**
4. Click **Next**
5. Project name: `HandsOnLab`
6. Choose a location and click **Create**
7. Choose a .NET version (e.g., `.NET 6`)
8. Click **Create**

Visual Studio will generate the basic MVC folder structure:

```
HandsOnLab
├── Controllers
├── Models
├── Views
└── Program.cs
```

### 2.2 Configure MVC in `Program.cs`

In `Program.cs`, the necessary lines are:

```csharp
var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
```

We’ll explain this file in detail later (Section 7).

---

## 3. Model Layer – Emp.cs

### 3.1 Purpose of the Model

The **Model** in MVC represents the **data** and **business entities**.  
Here, `Emp` represents one row of the `Emp` table.

### 3.2 Full Code – `Emp.cs`

```csharp
namespace HandsOnLab.Models
{
    public class Emp
    {
        public int Id { get; set; }
        public string Name{ get; set; }
        public string Address { get; set; }

    }
}
```

### 3.3 Line-by-Line Explanation

#### `namespace HandsOnLab.Models`

- **Namespace** is a logical grouping of related classes.
- `HandsOnLab.Models` indicates this file belongs to the **Models** part of your MVC project.

#### `public class Emp`

- `public` → accessible from anywhere in the project.
- `class Emp` → defines a **POCO** (Plain Old CLR Object) class.
- This class maps to the `Emp` table in the database.

#### `public int Id { get; set; }`

- Defines a public property named `Id` of type `int`.
- `{ get; set; }` → auto-implemented property, C# generates backing storage.
- Corresponds to `Id` column in the `Emp` SQL table, which is the **primary key**.

#### `public string Name { get; set; }`

- Holds the employee's name.
- Maps to `Name` column in the DB (`NVARCHAR(100)`).
- Type: `string` in C#, since it's textual.

#### `public string Address { get; set; }`

- Holds the employee's address.
- Maps to `Address` column in the DB (`NVARCHAR(200)`).

This model will be used:

- By `EmpDAL` to populate data from DB rows.
- By `HomeController` to pass data to Views.
- By Views (`Index`, `Edit`) to display and edit data.

---

## 4. Data Access Layer – EmpDAL.cs (Using ADO.NET)

### 4.1 Purpose

`EmpDAL` (Employee Data Access Layer) is responsible for interacting with the database using **ADO.NET**.

It provides methods to:

- Read all employees (`GetEmps`)
- Insert new employee (`AddEmps`)
- Get a single employee by Id (`GetEmp`)
- Update employee (`UpdateEmp`)
- Delete employee (`Delete`)

### 4.2 Full Code – `EmpDAL.cs`

```csharp
using Microsoft.Data.SqlClient;

namespace HandsOnLab.Models
{
    public class EmpDAL
    {
        private string connectionString = 
            "Data Source=(LocalDB)\MSSQLLocalDB;Initial Catalog=IACSDDB;Integrated Security=True;";
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
            List<Emp> list = new List<Emp>();

            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = "insert into Emp(Name,Address) values('{0}','{1}')";
            string query = string.Format(queryFormat, emp.Name, emp.Address);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();


            return rowAffected;
        }


        public Emp GetEmp(int id)
        {
            var emp = GetEmps();
            var empSearched = (from e in emp
                               where e.Id == id
                               select e).First();
            return empSearched;
        }

        public int UpdateEmp(Emp emp)
        {
            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = " update Emp set Name='{0}', Address='{1}' where Id={2}";
            string query = string.Format(queryFormat, emp.Name, emp.Address, emp.Id);

            SqlCommand cmd = new SqlCommand(query, connection);

            int rowAffected = cmd.ExecuteNonQuery();

            connection.Close();
            return rowAffected;
        }


        public int Delete(int id)
        {
            SqlConnection connection = new SqlConnection(connectionString);

            connection.Open();

            string queryFormat = " delete from Emp where Id='{0}'";
            string query = string.Format(queryFormat, id);

            SqlCommand cmd = new SqlCommand(query, connection);
            int rowAffected = cmd.ExecuteNonQuery();

            return rowAffected;
        }
    }
}
```

### 4.3 Explanation of Key ADO.NET Types

- `SqlConnection` → Represents a connection to SQL Server.
- `SqlCommand` → Represents a SQL statement or stored procedure to execute.
- `SqlDataReader` → Reads rows returned by a `SELECT` query, forward-only and read-only.
- `ExecuteReader()` → Executes SQL that returns rows (e.g., `SELECT`).
- `ExecuteNonQuery()` → Executes SQL that doesn't return rows (e.g., `INSERT`, `UPDATE`, `DELETE`). Returns number of rows affected.

### 4.4 Line-by-Line Explanation (Important Parts)

#### `using Microsoft.Data.SqlClient;`

- Imports the ADO.NET provider for SQL Server.
- Provides `SqlConnection`, `SqlCommand`, `SqlDataReader`, etc.

#### `private string connectionString = "...";`

- Stores the connection details needed to connect to the database.
- `Data Source=(LocalDB)\MSSQLLocalDB` → Name of the SQL instance.
- `Initial Catalog=IACSDDB` → Name of the database.
- `Integrated Security=True` → Uses Windows authentication.

---

### 4.4.1 `GetEmps()` – Retrieve All Employees

```csharp
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
```

**Flow Explanation:**

1. `List<Emp> list = new List<Emp>();`  
   - Creates an empty list to hold all employee objects.

2. `SqlConnection connection = new SqlConnection(connectionString);`  
   - Creates a DB connection object but does not open it yet.

3. `connection.Open();`  
   - Opens a physical connection to the database.

4. `SqlCommand cmd = new SqlCommand("select * from Emp", connection);`  
   - Prepares a SQL query that selects all rows from `Emp` table.

5. `SqlDataReader reader = cmd.ExecuteReader();`  
   - Executes the query.  
   - Returns a `SqlDataReader` to read the result set row-by-row.

6. `while (reader.Read())`  
   - Iterates through each row one by one.

7. Inside `while`:
   ```csharp
   Emp emp = new Emp();
   emp.Id = Convert.ToInt32(reader["Id"]);
   emp.Name = reader["Name"].ToString();
   emp.Address = reader["Address"].ToString();
   list.Add(emp);
   ```
   - Creates a new `Emp` object for each row.
   - Reads the columns by name (`Id`, `Name`, `Address`).
   - Converts them to appropriate C# types.
   - Adds the object to the list.

8. `return list;`  
   - Returns the list of employees to the caller (e.g., Controller).

---

### 4.4.2 `AddEmps(Emp emp)` – Insert Employee

```csharp
public int AddEmps(Emp emp)
{
    List<Emp> list = new List<Emp>();

    SqlConnection connection = new SqlConnection(connectionString);

    connection.Open();

    string queryFormat = "insert into Emp(Name,Address) values('{0}','{1}')";
    string query = string.Format(queryFormat, emp.Name, emp.Address);

    SqlCommand cmd = new SqlCommand(query, connection);

    int rowAffected = cmd.ExecuteNonQuery();

    return rowAffected;
}
```

**Flow Explanation:**

1. `SqlConnection connection = new SqlConnection(connectionString);`  
   - Creates DB connection.

2. `connection.Open();`  
   - Opens it.

3. `string queryFormat = "insert into Emp(Name,Address) values('{0}','{1}')";`  
   - Defines a **format string** for SQL Insert.

4. `string query = string.Format(queryFormat, emp.Name, emp.Address);`  
   - Replaces `{0}` with `emp.Name` and `{1}` with `emp.Address`.
   - Builds final SQL query.

5. `SqlCommand cmd = new SqlCommand(query, connection);`  
   - Prepares the command to execute.

6. `int rowAffected = cmd.ExecuteNonQuery();`  
   - Executes INSERT.
   - Returns number of rows affected (should be 1 if success).

7. `return rowAffected;`  
   - Returns count to caller.

> ⚠ **Note:** This approach is vulnerable to **SQL Injection**. In production, use **parameterized queries** instead of string formatting.

---

### 4.4.3 `GetEmp(int id)` – Get a Single Employee

```csharp
public Emp GetEmp(int id)
{
    var emp = GetEmps();
    var empSearched = (from e in emp
                       where e.Id == id
                       select e).First();
    return empSearched;
}
```

- Calls `GetEmps()` to get all employees.
- Uses LINQ to filter:
  ```csharp
  from e in emp
  where e.Id == id
  select e
  ```
- Then takes the first match via `.First()`.

In real applications, you might query the database directly for the specific `id` for efficiency, but this is fine for a simple demo.

---

### 4.4.4 `UpdateEmp(Emp emp)` – Update Employee

```csharp
public int UpdateEmp(Emp emp)
{
    SqlConnection connection = new SqlConnection(connectionString);

    connection.Open();

    string queryFormat = " update Emp set Name='{0}', Address='{1}' where Id={2}";
    string query = string.Format(queryFormat, emp.Name, emp.Address, emp.Id);

    SqlCommand cmd = new SqlCommand(query, connection);

    int rowAffected = cmd.ExecuteNonQuery();

    connection.Close();
    return rowAffected;
}
```

- Builds an `UPDATE` statement.
- Sets new values for `Name` and `Address` where `Id` matches.
- Executes the query using `ExecuteNonQuery()`.

---

### 4.4.5 `Delete(int id)` – Delete Employee

```csharp
public int Delete(int id)
{
    SqlConnection connection = new SqlConnection(connectionString);

    connection.Open();

    string queryFormat = " delete from Emp where Id='{0}'";
    string query = string.Format(queryFormat, id);

    SqlCommand cmd = new SqlCommand(query, connection);
    int rowAffected = cmd.ExecuteNonQuery();

    return rowAffected;
}
```

- Creates and executes a `DELETE` statement.
- Deletes the row where `Id` matches the given `id`.

---

## 5. Controller Layer – HomeController.cs

### 5.1 Purpose of Controller

- The **Controller** acts as a **traffic manager**.
- Receives HTTP requests, calls appropriate DAL methods, and returns Views.

### 5.2 Full Code – `HomeController.cs`

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
            List<Emp> emps = dbObj.GetEmps();
            return View("Index",emps);
        }

        public IActionResult Create()
        {
            return View();
        }
        
        public IActionResult AfterCreate(Emp emp)
        {
            dbObj.AddEmps(emp);
            return Redirect("/Home/Index");
        }

        public IActionResult Edit(int id)
        {
            Emp emp = dbObj.GetEmp(id);
            return View("Edit", emp);
        }

        public IActionResult AfterEdit(Emp emp)
        {
            Emp empUpdated = dbObj.GetEmp(emp.Id);
            empUpdated.Name = emp.Name;
            empUpdated.Address = emp.Address;

            dbObj.UpdateEmp(empUpdated);

            return Redirect("/Home/Index");
        }

        public IActionResult Delete(int id)
        {
            dbObj.Delete(id);
            return Redirect("/Home/Index");
        }
    }
}
```

### 5.3 Action-wise Explanation

#### Field: `EmpDAL dbObj = new EmpDAL();`

- Creates an instance of `EmpDAL` to call database methods.
- In more advanced setups, this is typically injected using Dependency Injection, but this is fine for learning/demo.

---

### 5.3.1 `Index()`

```csharp
public IActionResult Index()
{
    List<Emp> emps = dbObj.GetEmps();
    return View("Index",emps);
}
```

- Calls `GetEmps()` from DAL to fetch all employees.
- Stores result in `emps`.
- Returns `Index` view, passing `emps` as the model.

**View Flow**:  
`/Home/Index` → `Index()` → `GetEmps()` → returns `List<Emp>` → passed to `Index.cshtml`.

---

### 5.3.2 `Create()`

```csharp
public IActionResult Create()
{
    return View();
}
```

- Handles GET request to `/Home/Create`.
- Simply returns the `Create.cshtml` view.
- Used to show empty form for adding a new employee.

---

### 5.3.3 `AfterCreate(Emp emp)`

```csharp
public IActionResult AfterCreate(Emp emp)
{
    dbObj.AddEmps(emp);
    return Redirect("/Home/Index");
}
```

- Handles POST request from `Create` form.
- Model binding automatically maps form fields `Name`, `Address` to `Emp emp`.
- Calls `AddEmps(emp)` to insert new record into DB.
- Redirects to `/Home/Index` to show updated list.

---

### 5.3.4 `Edit(int id)`

```csharp
public IActionResult Edit(int id)
{
    Emp emp = dbObj.GetEmp(id);
    return View("Edit", emp);
}
```

- Handles `/Home/Edit/{id}`.
- Calls `GetEmp(id)` from DAL to fetch one employee.
- Passes that `Emp` object to the `Edit` view.
- `Edit.cshtml` will pre-fill the form fields with this employee's data.

---

### 5.3.5 `AfterEdit(Emp emp)`

```csharp
public IActionResult AfterEdit(Emp emp)
{
    Emp empUpdated = dbObj.GetEmp(emp.Id);
    empUpdated.Name = emp.Name;
    empUpdated.Address = emp.Address;

    dbObj.UpdateEmp(empUpdated);

    return Redirect("/Home/Index");
}
```

- Handles POST from `Edit` form.
- `Emp emp` contains updated values from the form.
- Retrieves original employee from DB using `GetEmp(emp.Id)`.
- Updates its `Name` and `Address` fields.
- Calls `UpdateEmp(empUpdated)` to save changes.
- Redirects to `/Home/Index` to show updated list.

---

### 5.3.6 `Delete(int id)`

```csharp
public IActionResult Delete(int id)
{
    dbObj.Delete(id);
    return Redirect("/Home/Index");
}
```

- Handles `/Home/Delete/{id}`.
- Calls `Delete(id)` on DAL to remove the record.
- Redirects to `/Home/Index` afterward.

---

## 6. Views – Razor Pages

The Views handle the **presentation layer** (HTML + Razor syntax).

All views are in:  
`Views/Home/`

### 6.1 Index View – `Index.cshtml`

#### Full Code

```razor
@model List<HandsOnLab.Models.Emp>
<html>
    <head>
        <title>Home</title>
        <link href="~/lib/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
        <script src="~/lib/bootstrap/dist/js/bootstrap.min.js"></script>
    </head>
    <body class="container">
        <hr />
        <a href="/Home/Create" class="btn btn-primary">Create New Record</a>
        <hr />
        <div class="table-responsive">
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach (var emp in Model)
                {
                    <tr>
                        <td>@emp.Id</td>
                        <td>@emp.Name</td>
                        <td>@emp.Address</td>
                        <td>
                            <a href="/Home/Edit/@emp.Id" class="btn btn-warning">Edit</a>
                        </td>
                        <td>
                            <a href="/Home/Delete/@emp.Id" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                }
                </tbody>
            </table>
        </div>
    </body>
</html>
```

#### Explanation

- `@model List<HandsOnLab.Models.Emp>`  
  - Strongly-typed view.
  - The `Model` is a `List<Emp>` passed from `HomeController.Index()`.

- The Bootstrap CSS and JS are included for styling and responsiveness.

- `@foreach (var emp in Model)` loops over each employee and renders a table row.

- `@emp.Id`, `@emp.Name`, `@emp.Address` render the values from model properties.

- `Edit` and `Delete` buttons generate links that pass the `emp.Id` to the controller:

  - `/Home/Edit/@emp.Id`
  - `/Home/Delete/@emp.Id`

---

### 6.2 Create View – `Create.cshtml`

#### Full Code

```html
<html>
    <head>
        <title>Create</title>
        <link href="~/lib/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
        <script src="~/lib/bootstrap/dist/js/bootstrap.min.js"></script>
    </head>
    <body class="container">
        <hr />
        <a href="/Home/Index" class="btn btn-primary">Go Home...</a>
        <hr />
        <div class="table-responsive">
            <form action="/Home/AfterCreate" method="post">
                <table class="table table-bordered text-center">

                    <tbody>
                        <tr>
                            <td>Enter Name</td>
                            <td>
                                <input type="text" name="Name" />
                            </td>
                        </tr>

                        <tr>
                            <td>Enter Address</td>
                            <td>
                                <input type="text" name="Address" />
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Submit" name="btnCreate" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
```

#### Explanation

- Renders a form for creating a new employee.
- `form action="/Home/AfterCreate" method="post"`  
  - Uses POST method.
  - Submits to `HomeController.AfterCreate(Emp emp)`.

- `name="Name"` and `name="Address"` match the property names in `Emp` class.
  - This allows ASP.NET Core's **Model Binding** to populate the `Emp emp` parameter.

---

### 6.3 Edit View – `Edit.cshtml`

#### Full Code

```razor
@model HandsOnLab.Models.Emp;
<html>
    <head>
        <title>Create</title>
        <link href="~/lib/bootstrap/dist/css/bootstrap.css" rel="stylesheet" />
        <script src="~/lib/bootstrap/dist/js/bootstrap.min.js"></script>
    </head>
    <body class="container">
        <hr />
        <a href="/Home/Index" class="btn btn-primary">Go Home...</a>
        <hr />
        <div class="table-responsive">
            <form action="/Home/AfterEdit" method="post">
                <input type="hidden" name="Id" value="@Model.Id" />
                <table class="table table-bordered text-center">

                    <tbody>
                        <tr>
                            <td>Enter Name</td>
                            <td>
                                <input type="text" name="Name" value="@Model.Name"/>
                            </td>
                        </tr>

                        <tr>
                            <td>Enter Address</td>
                            <td>
                                <input type="text" name="Address" value="@Model.Address"/>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Update" name="btnEdit" class="btn btn-info"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
```

#### Explanation

- `@model HandsOnLab.Models.Emp;`  
  - This view expects a single `Emp` object as model.

- `<input type="hidden" name="Id" value="@Model.Id" />`  
  - Keeps the `Id` of the employee in a hidden field.
  - Needed so that `AfterEdit(Emp emp)` knows which record to update.

- `value="@Model.Name"` and `value="@Model.Address"`  
  - Pre-fill the form with existing values from database.

- Form posts to `/Home/AfterEdit`, mapped to:

  ```csharp
  public IActionResult AfterEdit(Emp emp)
  ```

---

## 7. Application Startup and Routing – Program.cs

### Full Code – `Program.cs`

```csharp
namespace HandsOnLab
{
    public class Program
    {
        public static void Main(string[] args)
        {

            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.
            builder.Services.AddControllersWithViews();

            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Home/Error");
            }
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.MapControllerRoute(
                name: "default",
                pattern: "{controller=Home}/{action=Index}/{id?}");

            app.Run();
        }
    }
}
```

### Explanation

- `WebApplication.CreateBuilder(args)`  
  - Creates a builder for configuring services and middleware.

- `builder.Services.AddControllersWithViews();`  
  - Registers MVC services (controllers + Razor views).

- `var app = builder.Build();`  
  - Builds the application pipeline object.

- `if (!app.Environment.IsDevelopment()) { ... }`  
  - In non-development environments, uses `/Home/Error` for error handling.

- `app.UseStaticFiles();`  
  - Enables serving static files (CSS, JS, images) from `wwwroot`.

- `app.UseRouting();`  
  - Enables routing middleware.

- `app.UseAuthorization();`  
  - Enables authorization (not used much here, but standard in templates).

- `app.MapControllerRoute(...)`  
  - Sets up the default conventional route:
    - `controller=Home`
    - `action=Index`
    - `id?` is optional parameter.

- `app.Run();`  
  - Starts the web server and begins listening for HTTP requests.

---

## 8. End-to-End Request Flow

Here’s what happens when user interacts with your app:

### 8.1 View All Employees

1. User opens `/Home/Index` in browser.
2. `HomeController.Index()` is called.
3. Controller calls `EmpDAL.GetEmps()`.
4. `GetEmps()` reads all rows from `Emp` table and returns `List<Emp>`.
5. Controller passes this list to `Index.cshtml` view.
6. `Index.cshtml` loops through employees and displays them in a table.

### 8.2 Create New Employee

1. User clicks **Create New Record** on Index page.
2. Browser navigates to `/Home/Create` → `HomeController.Create()`.
3. `Create.cshtml` is rendered with an empty form.
4. User fills `Name` and `Address` and submits.
5. Form posts to `/Home/AfterCreate` → `HomeController.AfterCreate(Emp emp)`.
6. `AddEmps(emp)` is called to insert data into DB.
7. User is redirected to `/Home/Index` to see updated list.

### 8.3 Edit Employee

1. User clicks **Edit** for a particular row on Index page.
2. Link goes to `/Home/Edit/{id}` → `HomeController.Edit(int id)`.
3. Controller calls `EmpDAL.GetEmp(id)`.
4. `Edit.cshtml` is rendered with existing values prefilled.
5. User updates the fields and submits the form.
6. Form posts to `/Home/AfterEdit` → `HomeController.AfterEdit(Emp emp)`.
7. Controller updates the record using `UpdateEmp(empUpdated)`.
8. User is redirected to `/Home/Index`.

### 8.4 Delete Employee

1. User clicks **Delete** on Index page.
2. Link goes to `/Home/Delete/{id}` → `HomeController.Delete(int id)`.
3. Controller calls `EmpDAL.Delete(id)`.
4. Record is deleted from DB.
5. User is redirected to `/Home/Index` to see updated list.

---

## 9. Summary

In this project, you have:

- Implemented **MVC pattern** (Model–View–Controller)
- Used **ADO.NET** to perform CRUD operations on SQL Server
- Built complete flow:
  - Database → DAL → Controller → View → Browser
- Practiced:
  - Strongly-typed views
  - Model binding
  - Routing
  - Basic Bootstrap UI

This documentation is suitable for:

- College practical records
- Viva and interview explanations
- Personal revision and portfolio

---

If you want, the next improvements can be:

- Use **parameterized queries** in `EmpDAL` for security.
- Migrate to **Entity Framework Core** for easier data access.
- Add **validation** (server-side + client-side) for forms.
- Add **error handling** and better user feedback messages.
