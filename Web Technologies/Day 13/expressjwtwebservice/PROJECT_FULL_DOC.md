## expressjwtwebservice — Full Project Documentation

This document explains every file in the `expressjwtwebservice` project line-by-line, describes the runtime flow, and provides step-by-step instructions to recreate the project. Save this file for future reference.

---

## Overview

Project implements a small Express-based product API protected by JWTs. Files covered:

- `package.json`
- `server.js`
- `databaseconfig/dbconnection.js`
- `middleware/jwttokendetails.js`
- `controller/loginController.js`
- `controller/productController.js`
- `router/loginroutes.js`
- `router/productroutes.js`

This document: for each file shows the code, explains each line (what it does and why) and then provides notes about how the pieces interact at runtime.

---

## 1) `package.json`

```json
{
  "name": "expressjwtproductwebservice",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "type": "commonjs",
  "dependencies": {
    "bcrypt": "^6.0.0",
    "body-parser": "^2.2.0",
    "jsonwebtoken": "",
    "express": "^5.1.0",
    "mysql2": "^3.15.3",
    "nodemon":""
  }
}
```

Line-by-line highlights

- `name`, `version`, `description`, `main`: metadata for npm. `main` points to module entry.
- `scripts`: contains `test` (placeholder). You can add `start` and `dev` scripts here (e.g., `node server.js`, `nodemon server.js`).
- `type: "commonjs"`: ensures Node treats files as CommonJS modules (using `require/module.exports`).
- `dependencies`: lists runtime packages. Note some values are empty strings (`jsonwebtoken`, `nodemon`) — these should contain versions; npm may treat them as invalid. Recommend running `npm install jsonwebtoken nodemon --save` to add valid versions.

Why fix package.json

- Replace empty dependency versions with actual versions to avoid install issues.
- Add useful scripts:
  - `"start": "node server.js"`
  - `"dev": "nodemon server.js"`

---

## 2) `server.js`

```javascript
const express=require("express")
const app=express()
const bodyParser=require("body-parser")
const loginroutes=require("./router/loginroutes")
const productroutes=require("./router/productroutes")

//configure application
  
//app.use(bodyParser.urlencoded({extended:false}))
app.use(bodyParser.json())
//handle routes
app.use("/login",loginroutes)
app.use("/product",productroutes)

app.listen(3333,()=>{
    console.log("running at port 3333")
})
```

Line-by-line explanation

- `const express=require("express")` — import Express framework. Required to create app and routes.
- `const app=express()` — create an Express application instance.
- `const bodyParser=require("body-parser")` — import body parsing middleware. Note: since Express 4.16+, you can use `express.json()` and `express.urlencoded()` instead of this external package.
- `const loginroutes=require("./router/loginroutes")` — import login router module.
- `const productroutes=require("./router/productroutes")` — import product router module.
- `//app.use(bodyParser.urlencoded({extended:false}))` — commented: would parse `application/x-www-form-urlencoded` bodies (forms). Uncomment if needed.
- `app.use(bodyParser.json())` — parse `application/json` request bodies so `req.body` is available in handlers.
- `app.use("/login",loginroutes)` — mount login routes at `/login`.
- `app.use("/product",productroutes)` — mount product routes at `/product`.
- `app.listen(3333,()=>{ console.log("running at port 3333") })` — start server on port 3333.

Notes

- Recommend using `const PORT = process.env.PORT || 3333; app.listen(PORT, ...)` to allow environment configuration.
- Replace `body-parser` with `app.use(express.json())` for modern apps, unless you specifically need `body-parser` features.

---

## 3) `databaseconfig/dbconnection.js`

```javascript
const mysql=require('mysql2')


//created a connection configuration
const db=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'aks123',
    database:"expressdb"
})

db.connect((err)=>{
    if(err){
        console.log(err);
    }else{
        console.log("connection done")
    }
})

module.exports=db;
```

Line-by-line explanation

- `const mysql=require('mysql2')` — import mysql2 driver, which supports modern features and prepared statements.
- `const db=mysql.createConnection({...})` — create a single connection using provided config (host, user, password, database). This uses plain credentials stored in file — acceptable for local development but not for production.
- `db.connect((err)=>{ ... })` — explicitly connect and log success or error. Many drivers lazily connect on first query; this forces connection now and helps detect config issues early.
- `module.exports=db` — export the connection instance so controllers can reuse it with `require('../databaseconfig/dbconnection')`.

Recommendations

- Move DB credentials to environment variables (e.g., `process.env.DB_HOST`) and do not commit secrets.
- For web apps use `createPool` instead of a single connection for concurrency: `mysql.createPool({...})`.

---

## 4) `middleware/jwttokendetails.js`

```javascript
const jwt=require("jsonwebtoken")
const JWT_SECRET="mysecretkey"

exports.generateToken=(user)=>{
    // Include minimal info in the token payload
  const payload = { id: user.uid, uname: user.uname, email:user.email };
  
  return jwt.sign(payload, JWT_SECRET, { expiresIn: 200000 });
}

exports.authenticateJWT=async (req, res, next)=> {
    console.log("validated jwt token")
  const auth = req.headers.authorization; // {authorization:"Bearere sldjg345sdkfjskl"}
  if (!auth || !auth.startsWith('Bearer ')) {
    res.status(401).json({ message: 'Missing token' });
  }else{
      const token = auth.split(' ')[1];
      try {
            const payload = jwt.verify(token, JWT_SECRET);
            // attach to request
            req.user = payload; // {id, username, role, iat, exp}
            console.log(req.user)
            next();
        } catch (err) {
    return res.status(401).json({ message: 'Invalid/Expired token' });
  }
  }

  
  
}
```

Line-by-line explanation

- `const jwt=require("jsonwebtoken")` — import `jsonwebtoken` to sign and verify JWTs.
- `const JWT_SECRET="mysecretkey"` — secret used to sign tokens. Should be long, random, and stored in an environment variable in production.
- `exports.generateToken=(user)=>{ ... }` — function that creates a JWT from a `user` object.
  - `const payload = { id: user.uid, uname: user.uname, email:user.email }` — extracts minimal claims to embed in token. Keep payload small and avoid sensitive data.
  - `return jwt.sign(payload, JWT_SECRET, { expiresIn: 200000 })` — sign token with expiration (200000 seconds? check unit; `jsonwebtoken` expects seconds or time string). Returns token string.
- `exports.authenticateJWT=async (req, res, next)=> { ... }` — Express middleware to validate incoming JWTs.
  - Reads `req.headers.authorization`, checks `Bearer ` prefix, extracts token.
  - `jwt.verify(token, JWT_SECRET)` verifies signature and expiration; on success attaches `payload` to `req.user` and calls `next()`.
  - On failure returns 401 with appropriate message.

Notes and recommendations

- Store `JWT_SECRET` in env variable (e.g., `process.env.JWT_SECRET`).
- Use reasonable token expiry and refresh tokens if needed.
- `console.log("validated jwt token")` logs for debugging but can be noisy in production.

---

## 5) `controller/loginController.js`

```javascript
const connection=require("../databaseconfig/dbconnection")
const {generateToken}=require("../middleware/jwttokendetails")

exports.validateuser=(req,resp)=>{
    const {email,password}=req.body
    connection.query("select * from myusers where email=?",[email],(err,result)=>{
        if(err){
            resp.status(500).json({message:"Invalid credentials"})
        }else{
            const user=result[0];
            if(user.password===password){
                //pass only required information
            //const token=generateToken({id:user.uid,uname:user.uname,email:user.email})
            const token=generateToken(user)
               //resp.json({token:token})
               resp.json({token})
            }else{
                resp.status(500).json({message:"invalid credential"})
            }
        }
    })
}
```

Line-by-line explanation

- `const connection=require("../databaseconfig/dbconnection")` — reuse DB connection.
- `const {generateToken}=require("../middleware/jwttokendetails")` — import token generator.
- `exports.validateuser=(req,resp)=>{ ... }` — POST handler to authenticate users.
  - `const {email,password}=req.body` — get credentials from request body.
  - `connection.query("select * from myusers where email=?",[email],...)` — parameterized SQL to fetch user row by email (prevents SQL injection).
  - Error handling: if DB error, respond 500 with message.
  - `const user=result[0];` — assume at least one row exists; could be undefined if email not found — code should check `result.length` to avoid runtime error.
  - `if(user.password===password){ ... }` — compares stored password directly to provided plaintext. This is insecure if passwords are not hashed. Recommended to use `bcrypt.compare` and store hashed passwords.
  - `const token=generateToken(user)` — create JWT for authenticated user. `generateToken` uses fields like `user.uid` and `user.uname` so the user row should have those fields.
  - `resp.json({token})` — respond with token JSON.
  - On password mismatch return 500 (prefer 401 Unauthorized) with message.

Fixes & recommendations

- Check for empty results before using `result[0]`.
- Use `bcrypt.compare` to verify hashed passwords and `bcrypt.hash` when registering/updating.
- Return `401` for authentication failure instead of `500`.

---

## 6) `controller/productController.js`

```javascript
const connection=require("../databaseconfig/dbconnection")

exports.getAllProducts=(req,resp)=>{
    //if the token is valid, ideally we check user.role
    if(req.user.uname==='user1'){
            connection.query("select * from myproducts",(err,result,field)=>{
                if(err){
                    resp.status(404).json({message:"error occured" +JSON.stringify(err)});
                }else{
                    console.log(result);
                    resp.json({data:result})
                }
            })
    }else{
        resp.status(500).json({message:"invalid token"})
    }
}


//insert product in the table
exports.insertProduct=(req,resp)=>{
   const {pid,pname,qty,price,mfgdate}=req.body
   connection.query("insert into myproducts values(?,?,?,?,?)",[pid,pname,qty,price,mfgdate],(err,result,field)=>{
     if(err){
        resp.status(500).json({message:"data not inserted" +JSON.stringify(err)});
      }else{
        console.log("data added"+result)
        resp.json({data:"data added"})
      }
   })
}

//display data in the form for updation
exports.getById=(req,resp)=>{
    if(req.user.uname==="user1"){
    connection.query("select * from myproducts where pid=?",[req.params.id],(err,result,fields)=>{
        if(err){
            resp.status(500).json({message:"Product not found"})
        }else{
           console.log(result);

           //result[0].mfgdate=result[0].mfgdate.toISOString().split('T')[0];
            resp.json({data:result[0]})
        }

    })
}else{
    resp.status(500).json({"message":"invalid token"})
}
}

//update product in the table
exports.updateProduct=(req,resp)=>{
    if(req.user.uname==="user1"){
    const {pid,pname,qty,price,mfgdate}=req.body
    console.log("mfgdate"+mfgdate)
    connection.query("update myproducts set pname=?,qty=?,price=?,mfgdate=? where pid=?",[pname,qty,price,mfgdate,pid],(err,result,fields)=>{
        if(err){
            console.log(err)
            resp.status(500).json({message:"Product not updated"})
        }else{
            resp.json({data:result})
        }
    } )}else{
        resp.status(500).json({message:"invalid token"})
    }
}

//delete product from table
exports.deleteProduct=(req,resp)=>{
    if(req.user.uname==="user1"){
    connection.query("delete from myproducts where pid=?",[req.params.id],(err,result)=>{
        if(err){
            resp.status(500).json({message:JSON.stringify(err)})
        }else{
            resp.json({message:"deleted successfully"})
        }
    })
   }else{
    resp.status(500).json({message:"invalid token"})
   }
}
```

Line-by-line explanation & tips

- `const connection=require("../databaseconfig/dbconnection")` — reuse connection.
- `exports.getAllProducts=(req,resp)=>{ ... }` — returns all products if `req.user.uname==='user1'` (simple role check). If not, responds 500 with `invalid token` (prefer 403 Forbidden).
- `insertProduct` — inserts a product row using placeholders to avoid SQL injection.
- `getById` — selects single product by `pid` from route param `req.params.id`. Returns `result[0]`.
- `updateProduct` — updates product fields.
- `deleteProduct` — deletes product by id.

Notes and improvements

- Role check is simplistic: it checks `req.user.uname==='user1'`. In production, store `role` in token or DB and check `req.user.role`.
- Use proper HTTP status codes: 401 (Unauthenticated), 403 (Forbidden), 404 (Not Found), 500 (Server error).
- Validate input (e.g., ensure `pid` is integer, `price` is numeric).

---

## 7) `router/loginroutes.js`

```javascript
const express=require("express");
const router=express.Router();
const lcontroller=require("../controller/loginController")


router.post("/loginuser",lcontroller.validateuser)
module.exports=router;
```

Explanation

- Creates an Express Router and defines a POST route `/loginuser` which is handled by `validateuser` in `loginController`.
- When mounted in `server.js` under `/login`, the full path becomes `/login/loginuser`.

---

## 8) `router/productroutes.js`

```javascript
const express=require("express")
const router=express.Router();
const productcontroller=require("../controller/productController")
const {authenticateJWT}=require("../middleware/jwttokendetails")

//read all products
router.get("/products",authenticateJWT,productcontroller.getAllProducts)

router.get("/products/:id",authenticateJWT,productcontroller.getById)

router.post("/products/:id",authenticateJWT,productcontroller.insertProduct)

router.put("/products/:id",authenticateJWT,productcontroller.updateProduct)

router.delete("/products/:id",authenticateJWT,productcontroller.deleteProduct)

module.exports=router;
```

Explanation

- Imports `authenticateJWT` middleware and applies it to all product routes. This ensures a valid JWT is required before each controller runs.
- Defines routes for list, get-by-id, insert, update, delete operations. Mounted under `/product` in `server.js`, full paths are e.g. `/product/products`, `/product/products/123`.

Notes

- POST uses `/products/:id` which is unusual — typically `POST /products` creates a new product and `PUT/PATCH /products/:id` updates. Consider changing POST to `/products`.

---

## Runtime flow (how a typical request moves through the app)

1. Client sends POST `/login/loginuser` with JSON body `{ "email": "...", "password": "..." }`.
   - `router/loginroutes` maps `/login/loginuser` to `loginController.validateuser`.
   - `validateuser` queries `myusers` table looking for the email.
   - If match and password check succeeds, `generateToken(user)` signs a JWT and returns it in JSON.

2. Client stores token (localStorage, client app) and attaches it to future requests in `Authorization: Bearer <token>` header.

3. Client requests `/product/products` with header `Authorization: Bearer <token>`.
   - `router/productroutes` attaches `authenticateJWT` middleware to this route.
   - `authenticateJWT` verifies token signature and expiration, attaches payload to `req.user`.
   - If token valid, `productController.getAllProducts` runs and returns products JSON (if `req.user.uname==='user1'`).

4. For modifying operations (insert/update/delete) the same middleware check applies, then controller runs DB queries.

---

## Step-by-step instructions to recreate the project (copy into your notes)

1. Create project folder and initialize npm:

```powershell
mkdir expressjwtwebservice
cd expressjwtwebservice
npm init -y
```

2. Install dependencies (adjust versions as desired):

```powershell
npm install express mysql2 jsonwebtoken bcrypt body-parser ejs
npm i -D nodemon
```

3. Create directories:

```powershell
mkdir controller router databaseconfig middleware views
```

4. Add `server.js` (use the earlier snippet) and set up view engine if using EJS.

5. Create `databaseconfig/dbconnection.js` with correct DB credentials (or use env vars).

6. Create controllers and routers as defined above.

7. Create database and tables (example SQL):

```sql
CREATE DATABASE expressdb;
USE expressdb;
CREATE TABLE myusers (
  uid INT AUTO_INCREMENT PRIMARY KEY,
  uname VARCHAR(100),
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255)
);

CREATE TABLE myproducts (
  pid INT PRIMARY KEY,
  pname VARCHAR(255),
  qty INT,
  price DECIMAL(10,2),
  mfgdate DATE
);
```

8. Start server:

```powershell
node server.js
# or
npx nodemon server.js
```

9. Test using `curl` or Postman:

```powershell
# login
curl -X POST http://localhost:3333/login/loginuser -H "Content-Type: application/json" -d '{"email":"a@b.com","password":"pass"}'

# use token
curl -H "Authorization: Bearer <token>" http://localhost:3333/product/products
```

---

## Security and best-practice checklist

- Hash passwords with `bcrypt.hash` when storing and `bcrypt.compare` on login.
- Do not store secrets in source; use environment variables for DB password and JWT secret.
- Use proper HTTP status codes (401 Unauthorized, 403 Forbidden, 404 Not Found, 500 Internal Server Error).
- Validate request payloads and types.
- Use `createPool` for DB connectivity in web apps.
- Consider rate-limiting and input sanitization to mitigate abuse.

---

If you want, I can:

- Update your `package.json` to include correct dependency versions and useful scripts.
- Patch the controllers to use `bcrypt` and correct status codes and error handling.
- Change `productroutes` POST path to `/products` and add input validation.

Tell me which of those you'd like me to implement and I will apply the changes.
