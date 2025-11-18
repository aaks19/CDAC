# Express Login App - Complete Code Explanation & Steps to Write Similar Code

---

## 📋 TABLE OF CONTENTS
1. [Project Overview](#project-overview)
2. [server.js - Line by Line Explanation](#serverjs-explanation)
3. [Database Connection Setup](#database-connection-setup)
4. [Login Controller - Line by Line Explanation](#login-controller-explanation)
5. [Router Setup - Line by Line Explanation](#router-setup)
6. [Step-by-Step Guide to Write Similar Code](#step-by-step-guide)

---

## PROJECT OVERVIEW

This is an **Express.js Login Application** that includes:
- User Authentication (Login, Register, Logout)
- Password Reset (Forgot Password)
- Session Management
- MySQL Database Integration
- EJS Template Engine for Views

**Technologies Used:**
- Express.js (Web Framework)
- MySQL2 (Database)
- Express-session (Session Management)
- Body-parser (Parse request bodies)
- EJS (Template Engine)
- Bcrypt (Password Hashing - for future implementation)

---

## server.js - LINE BY LINE EXPLANATION

### Line 1: Import Express
```javascript
const express=require("express")
```
- **What it does:** Imports the Express framework
- **Why:** Express is the web framework used to create the server and handle HTTP requests
- **Returns:** An Express application object that we can configure

### Line 2: Import Path Module
```javascript
const path=require("path");
```
- **What it does:** Imports Node.js built-in `path` module
- **Why:** Used to work with file and directory paths in a cross-platform way
- **Used in:** Setting the views directory path

### Line 3: Import Express-session
```javascript
const session=require("express-session")
```
- **What it does:** Imports the express-session middleware
- **Why:** Manages user sessions - keeps track of logged-in users
- **Function:** Stores session data on the server side

### Line 4: Import Body-parser
```javascript
const bodyParser=require("body-parser")
```
- **What it does:** Imports body-parser middleware
- **Why:** Parses incoming request bodies (form data, JSON)
- **Function:** Converts form data into readable JavaScript objects

### Line 5: Import Login Routes
```javascript
const loginroutes=require("./router/loginroutes")
```
- **What it does:** Imports the login route definitions
- **Why:** Separates route logic from main server file (modular code)
- **Contains:** GET/POST routes for login, register, forgot password

### Line 6: Import Product Routes
```javascript
const productroutes=require("./router/productroutes")
```
- **What it does:** Imports the product route definitions
- **Why:** Handles product-related routes separately
- **Contains:** Routes to display and manage products

### Line 8: Create Express App
```javascript
const app=express()
```
- **What it does:** Creates the Express application instance
- **Why:** All configurations and routes are applied to this `app` object
- **Returns:** An application object with methods like `set()`, `use()`, `listen()`

---

### CONFIGURATION SECTION (Lines 13-15)

### Line 13: Set View Engine
```javascript
app.set("view engine","ejs")
```
- **What it does:** Tells Express to use EJS as the template engine
- **Why:** EJS allows us to write HTML with embedded JavaScript
- **Result:** Files with `.ejs` extension will be processed as templates

### Line 14: Set Views Directory Path
```javascript
app.set("views",path.join(__dirname,"views"))
```
- **What it does:** Specifies where view files are located
- **`__dirname`:** Represents the current directory where server.js is
- **`path.join()`:** Safely joins path segments
- **Result:** Express knows to look for .ejs files in the `views` folder

### Line 16: Serve Static Files
```javascript
app.use(express.static("public"))
```
- **What it does:** Serves static files (CSS, JS, images) from the `public` folder
- **Why:** CSS and JavaScript files in the public folder are directly accessible
- **Example:** `/css/style.css` maps to `public/css/style.css`

### Line 17: Parse URL-encoded Bodies
```javascript
app.use(bodyParser.urlencoded({extended:false}))
```
- **What it does:** Middleware to parse form data sent via POST requests
- **`extended:false`:** Uses simple query string parser (not qs library)
- **Result:** Form data is available in `req.body`

---

### SESSION CONFIGURATION (Lines 20-25)

### Lines 20-25: Initialize Session
```javascript
app.use(
  session({
    secret: "mysecretkey",
    resave: false,
    saveUninitialized: false
  })
);
```
- **What it does:** Sets up session management middleware
- **`secret`:** Encryption key for session (use environment variable in production)
- **`resave: false`:** Don't save unchanged sessions
- **`saveUninitialized: false`:** Don't create empty sessions
- **Result:** Creates `req.session` object available in all routes

---

### ROUTE HANDLING (Lines 28-29)

### Line 28: Mount Login Routes
```javascript
app.use("/login",loginroutes)
```
- **What it does:** Mounts login router at `/login` path
- **Result:** All routes in loginroutes.js are prefixed with `/login`
- **Example:** `router.get("/")` becomes `GET /login`

### Line 29: Mount Product Routes
```javascript
app.use("/product",productroutes)
```
- **What it does:** Mounts product router at `/product` path
- **Result:** Product routes are prefixed with `/product`

---

### START SERVER (Lines 31-33)

### Lines 31-33: Listen on Port
```javascript
app.listen(3333,()=>{
    console.log("running at port 3333")
})
```
- **What it does:** Starts the server on port 3333
- **Port:** 3333 is the port number where the server listens
- **Callback:** Function that runs when server starts successfully
- **Output:** Logs "running at port 3333" to console

---

## DATABASE CONNECTION SETUP

### dbconnection.js - LINE BY LINE EXPLANATION

### Line 1: Import MySQL
```javascript
const mysql=require('mysql2')
```
- **What it does:** Imports the mysql2 library
- **Why:** Enables connection to MySQL database

### Lines 5-9: Create Connection Configuration
```javascript
const db=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'aks123',
    database:"expressdb"
})
```
- **`host:'localhost'`:** MySQL server is on the same machine
- **`user:'root'`:** MySQL username
- **`password:'aks123'`:** MySQL password
- **`database:"expressdb"`:** Database name to connect to
- **Returns:** A connection object

### Lines 11-17: Connect to Database
```javascript
db.connect((err)=>{
    if(err){
        console.log(err);
    }else{
        console.log("connection done")
    }
})
```
- **`db.connect()`:** Attempts to connect to database
- **Callback function:** Runs when connection attempt completes
- **`if(err)`:** If connection fails, logs error
- **`else`:** If successful, logs "connection done"

### Line 19: Export Connection
```javascript
module.exports=db;
```
- **What it does:** Makes the connection object available to other files
- **Why:** Controllers need this to run SQL queries

---

## LOGIN CONTROLLER EXPLANATION

### loginController.js - LINE BY LINE EXPLANATION

### Lines 1-3: Import Dependencies
```javascript
const connection=require("../databaseconfig/dbconnection")
const bcrypt= require('bcrypt')
```
- **`connection`:** MySQL database connection object
- **`bcrypt`:** For password hashing (prepared for future use)

---

### FUNCTION 1: Get Login Form

### Lines 6-8: Display Login Form
```javascript
exports.getLoginForm=(req,resp)=>{
    resp.render("login")
}
```
- **`exports.getLoginForm`:** Exports function to be used in routes
- **`req`:** Request object (incoming HTTP request)
- **`resp`:** Response object (send back to client)
- **`resp.render("login")`:** Renders the login.ejs template
- **Result:** User sees the login form page

---

### FUNCTION 2: Validate User Login

### Lines 11-41: Validate User Details
```javascript
exports.validateuserdetails=(req,resp)=>{
    const {email,password}=req.body;

    connection.query("select * from myusers where email=?",[email],async (err,result)=>{
        if(err){
            console.log("error")
            console.log(err);
        }else{
            if(result.length===0){
                return resp.send("<h1>Invalid user</h1>")
            }else{
                var user=result[0];
                if(user.password===password){
                    req.session.user=user;
                    resp.redirect("/product/getproducts")
                }else{
                    return resp.send("<h1>Invalid user</h1>")
                }
            }
        }
    })
}
```

**Line 13:** Destructure email and password from form data
```javascript
const {email,password}=req.body;
```
- Extracts email and password from POST request form data

**Line 15:** Execute SQL Query
```javascript
connection.query("select * from myusers where email=?",[email],async (err,result)=>{
```
- **`SELECT * FROM myusers`:** Get all user data
- **`WHERE email=?`:** Filter by email (? is placeholder for security)
- **`[email]`:** Parameter values to prevent SQL injection
- **Callback:** Runs when query completes

**Line 16-19:** Handle Database Errors
```javascript
if(err){
    console.log("error")
    console.log(err);
}
```
- Checks if there's a database connection error
- Logs error to console for debugging

**Line 20-22:** Check if User Exists
```javascript
if(result.length===0){
    return resp.send("<h1>Invalid user</h1>")
}
```
- **`result.length===0`:** No user found with that email
- Returns "Invalid user" message

**Line 23-30:** Validate Password
```javascript
else{
    var user=result[0];
    if(user.password===password){
        req.session.user=user;
        resp.redirect("/product/getproducts")
    }else{
        return resp.send("<h1>Invalid user</h1>")
    }
}
```
- **`user=result[0]`:** Get the first (and only) user record
- **`user.password===password`:** Compare stored password with entered password
- **`req.session.user=user`:** Store user data in session (makes user "logged in")
- **`resp.redirect()`:** Redirect to products page if password matches
- **`else`:** Return error if password doesn't match

---

### FUNCTION 3: Get Forgot Password Form

### Lines 45-47: Display Forgot Password Form
```javascript
exports.getForgotPassword=(req,resp)=>{
    resp.render("forgotPassword")
}
```
- Renders the forgot password form page

---

### FUNCTION 4: Update Password

### Lines 49-67: Update User Password
```javascript
exports.updateNewPassword=(req,resp)=>{
    const {email,pass}=req.body;

    connection.query("select * from myusers where email=?",[email],async (err,result)=>{
        if(err){
            console.log(err);
        }
        else{
            var user = result[0];
            if(result.length !== 0){
                connection.query("update myusers set password=? where email=?",[pass,email],async (err)=>{
                    if(err){
                        console.log(err)
                    }else{
                        resp.redirect("/login")
                    }
                })
            }
        }
    })
}
```

**Line 50:** Get email and new password from form
```javascript
const {email,pass}=req.body;
```

**Line 52:** Find user by email
```javascript
connection.query("select * from myusers where email=?",[email],async (err,result)=>{
```
- Checks if email exists in database

**Line 59:** Check if user exists
```javascript
if(result.length !== 0){
```
- If user is found, proceed to update password

**Line 60:** Update password in database
```javascript
connection.query("update myusers set password=? where email=?",[pass,email],async (err)=>{
```
- **`UPDATE myusers`:** Modify the users table
- **`SET password=?`:** Update the password field
- **`WHERE email=?`:** For the specific user

**Line 65:** Redirect after update
```javascript
resp.redirect("/login")
```
- Takes user back to login page after password update

---

### FUNCTION 5: Get Registration Form

### Lines 72-74: Display Registration Form
```javascript
exports.getregistrationForm=(req,resp)=>{
    resp.render("register")
}
```
- Renders the registration form page

---

### FUNCTION 6: Register New User

### Lines 77-88: Register User
```javascript
exports.registeruser=async (req,resp)=>{
    const {uname,email,pass}=req.body;

    connection.query("insert into myusers(uname,email,password) values(?,?,?)",[uname,email,pass],async (err)=>{
        if(err){
            console.log(err)
        }else{
            resp.redirect("/login");
        }
    })
}
```

**Line 77:** Function is marked as `async`
- Prepared for future use of async/await with bcrypt

**Line 78:** Extract form data
```javascript
const {uname,email,pass}=req.body;
```
- Gets username, email, and password from registration form

**Line 81:** Insert new user into database
```javascript
connection.query("insert into myusers(uname,email,password) values(?,?,?)",[uname,email,pass],async (err)=>{
```
- **`INSERT INTO myusers`:** Add new row to users table
- **`(uname,email,password)`:** Which columns to fill
- **`values(?,?,?)`:** Three placeholders for three values
- **`[uname,email,pass]`:** The actual values

**Line 82-86:** Handle error or success
```javascript
if(err){
    console.log(err)
}else{
    resp.redirect("/login");
}
```
- If error: logs to console
- If success: redirects to login page

---

### FUNCTION 7: Logout User

### Lines 90-93: Logout User
```javascript
exports.logoutuser=(req,resp)=>{
    req.session.destroy();
    resp.redirect("/login")
}
```
- **`req.session.destroy()`:** Destroys the session (logs user out)
- **`resp.redirect("/login")`:** Takes user back to login page
- After logout, the session is cleared and user must login again

---

## ROUTER SETUP

### loginroutes.js - LINE BY LINE EXPLANATION

### Line 1-3: Import Dependencies
```javascript
const express=require("express")
const router=express.Router();
const lController=require("../controller/loginController")
```
- **Line 1:** Import Express
- **Line 2:** Create a new router object
- **Line 3:** Import login controller with all functions

---

### Line 6: Login Page Route
```javascript
router.get("/",lController.getLoginForm)
```
- **`GET /login`:** When user visits this URL
- **Calls:** `getLoginForm` function from controller
- **Result:** Shows login form

---

### Line 7: Validate Login Route
```javascript
router.post("/validateuser",lController.validateuserdetails)
```
- **`POST /login/validateuser`:** When form is submitted
- **Calls:** `validateuserdetails` function
- **Checks:** Email and password, creates session if valid

---

### Line 10: Registration Form Route
```javascript
router.get("/register",lController.getregistrationForm)
```
- **`GET /login/register`:** Shows registration form

---

### Line 11: Register User Route
```javascript
router.post("/registeruser",lController.registeruser)
```
- **`POST /login/registeruser`:** When registration form is submitted
- **Calls:** `registeruser` function
- **Action:** Inserts new user into database

---

### Line 14: Forgot Password Form Route
```javascript
router.get("/forgotPassword",lController.getForgotPassword)
```
- **`GET /login/forgotPassword`:** Shows forgot password form

---

### Line 15: Update Password Route
```javascript
router.post("/updatepassword",lController.updateNewPassword)
```
- **`POST /login/updatepassword`:** When password reset form submitted
- **Action:** Updates password in database

---

### Line 17: Logout Route
```javascript
router.get("/logout",lController.logoutuser)
```
- **`GET /login/logout`:** User clicks logout
- **Action:** Destroys session and redirects to login

---

### Line 19: Export Router
```javascript
module.exports=router;
```
- Makes router available to server.js

---

---

## STEP-BY-STEP GUIDE TO WRITE SIMILAR CODE

### STEP 1: Initialize Node.js Project
```
1. Create a new folder for your project
2. Open terminal in that folder
3. Run: npm init -y
4. This creates package.json with default settings
```

### STEP 2: Install Required Packages
```
npm install express mysql2 express-session body-parser ejs bcrypt nodemon
```

**What each package does:**
- `express`: Web framework
- `mysql2`: Database driver
- `express-session`: Session management
- `body-parser`: Parse form data
- `ejs`: Template engine
- `bcrypt`: Password hashing
- `nodemon`: Auto-restart server on file changes

---

### STEP 3: Create Folder Structure
```
Create these folders:
📁 controller/
📁 router/
📁 views/
📁 databaseconfig/
📁 public/
   └─ css/
   └─ js/
```

---

### STEP 4: Create Database Connection (databaseconfig/dbconnection.js)

**Follow these steps:**

1. **Import mysql2 library**
   ```javascript
   const mysql = require('mysql2')
   ```

2. **Create connection configuration object**
   ```javascript
   const db = mysql.createConnection({
       host: 'localhost',      // Where MySQL is running
       user: 'root',           // Your MySQL username
       password: 'your_password', // Your MySQL password
       database: 'your_db'     // Database name
   })
   ```

3. **Connect to database and handle errors**
   ```javascript
   db.connect((err) => {
       if(err) {
           console.log(err)
       } else {
           console.log("connection done")
       }
   })
   ```

4. **Export the connection**
   ```javascript
   module.exports = db
   ```

---

### STEP 5: Create Controller (controller/loginController.js)

**Follow these steps:**

1. **Import dependencies**
   ```javascript
   const connection = require("../databaseconfig/dbconnection")
   const bcrypt = require('bcrypt')
   ```

2. **For each feature, create a function that:**
   - Takes `req` (request) and `resp` (response) as parameters
   - Extracts data from `req.body`
   - Runs SQL query using `connection.query()`
   - Handles errors in callback
   - Sends response using `resp.render()`, `resp.send()`, or `resp.redirect()`

3. **Example structure for login validation:**
   ```javascript
   exports.validateuserdetails = (req, resp) => {
       // 1. Get data from form
       const {email, password} = req.body
       
       // 2. Query database
       connection.query(
           "SELECT * FROM table WHERE email=?",
           [email],
           (err, result) => {
               // 3. Handle error
               if(err) console.log(err)
               else {
                   // 4. Check if record exists
                   if(result.length === 0) {
                       resp.send("Not found")
                   } else {
                       // 5. Validate data
                       if(result[0].password === password) {
                           // 6. Store in session
                           req.session.user = result[0]
                           // 7. Redirect
                           resp.redirect("/path")
                       }
                   }
               }
           }
       )
   }
   ```

4. **Export all functions**
   ```javascript
   exports.functionName = (req, resp) => { ... }
   ```

---

### STEP 6: Create Routes (router/loginroutes.js)

**Follow these steps:**

1. **Import dependencies**
   ```javascript
   const express = require('express')
   const router = express.Router()
   const controller = require("../controller/controllerFile")
   ```

2. **Define GET routes (to display forms)**
   ```javascript
   router.get("/path", controller.functionName)
   ```

3. **Define POST routes (to handle form submissions)**
   ```javascript
   router.post("/path", controller.functionName)
   ```

4. **Export router**
   ```javascript
   module.exports = router
   ```

---

### STEP 7: Create Main Server File (server.js)

**Follow these steps:**

1. **Import all dependencies**
   ```javascript
   const express = require('express')
   const path = require('path')
   const session = require('express-session')
   const bodyParser = require('body-parser')
   const routes = require('./router/routefile')
   ```

2. **Create Express app**
   ```javascript
   const app = express()
   ```

3. **Configure view engine**
   ```javascript
   app.set("view engine", "ejs")
   app.set("views", path.join(__dirname, "views"))
   ```

4. **Add middleware**
   ```javascript
   // Serve static files
   app.use(express.static("public"))
   
   // Parse form data
   app.use(bodyParser.urlencoded({extended: false}))
   
   // Setup sessions
   app.use(session({
       secret: "your_secret_key",
       resave: false,
       saveUninitialized: false
   }))
   ```

5. **Mount routes**
   ```javascript
   app.use("/path", routes)
   ```

6. **Start server**
   ```javascript
   app.listen(3333, () => {
       console.log("Server running on port 3333")
   })
   ```

---

### STEP 8: Create Views (views/filename.ejs)

**Follow these steps:**

1. **Create HTML template**
   ```html
   <html>
   <head>
       <title>Page Title</title>
       <link rel="stylesheet" href="/css/style.css">
   </head>
   <body>
       <h1>Welcome</h1>
       
       <!-- For form submissions, use POST -->
       <form method="POST" action="/route/path">
           <input type="email" name="email" required>
           <input type="password" name="password" required>
           <button type="submit">Submit</button>
       </form>
       
       <!-- Use <%= %> to display JavaScript variables -->
       <p>Hello <%= user.name %></p>
       
       <!-- Use <% %> for JavaScript logic -->
       <% if(user) { %>
           <p>User is logged in</p>
       <% } %>
   </body>
   </html>
   ```

---

### STEP 9: Run Your Application

1. **Start the server**
   ```
   node server.js
   or (with nodemon for auto-restart)
   npx nodemon server.js
   ```

2. **Access in browser**
   ```
   http://localhost:3333/login
   ```

---

### COMMON PATTERNS TO REMEMBER

**Pattern 1: Display a Form**
```javascript
router.get("/path", controller.getForm)

exports.getForm = (req, resp) => {
    resp.render("form")
}
```

**Pattern 2: Handle Form Submission**
```javascript
router.post("/path", controller.handleSubmit)

exports.handleSubmit = (req, resp) => {
    const {field1, field2} = req.body
    connection.query(
        "SQL QUERY HERE",
        [field1, field2],
        (err, result) => {
            if(err) console.log(err)
            else resp.redirect("/nextpage")
        }
    )
}
```

**Pattern 3: Store User in Session**
```javascript
req.session.user = userObject
```

**Pattern 4: Check if User is Logged In**
```javascript
<% if(req.session.user) { %>
    <!-- Show for logged in users -->
<% } %>
```

**Pattern 5: Destroy Session (Logout)**
```javascript
req.session.destroy()
resp.redirect("/login")
```

---

### KEY SECURITY NOTES

⚠️ **Important:** This code has security issues for learning only:

1. **Passwords should be hashed**
   ```javascript
   // Instead of: user.password === password
   // Use: bcrypt.compare(password, user.hashedPassword)
   ```

2. **Use parameterized queries** (Already doing this ✓)
   ```javascript
   connection.query("SELECT * FROM users WHERE email=?", [email])
   // The ? prevents SQL injection
   ```

3. **Secrets should be in environment variables**
   ```javascript
   // Instead of: secret: "mysecretkey"
   // Use: secret: process.env.SESSION_SECRET
   ```

---

## SUMMARY

This Express.js login application follows the **MVC Pattern**:

- **M (Model):** MySQL Database
- **V (View):** EJS Templates in views/ folder
- **C (Controller):** Functions in controller/ folder

**Data Flow:**
1. User submits form (browser)
2. Router receives request
3. Controller processes it (queries database)
4. Response sent back (render page or redirect)
5. Session maintains logged-in state

This structure makes code organized, reusable, and easy to maintain!

---

**Happy Coding! 🚀**
