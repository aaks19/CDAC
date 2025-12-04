# Complete Project Code: Full Sources and Explanations

This file contains the full source for all project files in the workspace and line-by-line (or section) explanations. Each file is shown in a fenced block followed by notes explaining purpose, flow, and important details.

---

## Backend: `librarybackend/server.js`

```javascript
const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const cors = require('cors')

const libraryroute = require('./routes/LibraryRoute')

app.use(cors())
app.use(bodyParser.json())

app.use("/library",libraryroute)

app.listen(3333,()=>{
        console.log("running on port 3333");
})


// const express = require("express");
// const mysql = require("mysql2");
// const cors = require("cors");

// const app = express();
// app.use(cors());
// app.use(express.json());

// const db = mysql.createConnection({
//   host: "localhost",
//   user: "root",
//   password: "aks123",
//   database: "expressdb"
// });

// db.connect(err => {
//   if (err) {
//     console.log("DB Error:", err);
//   } else {
//     console.log("MySQL Connected");
//   }
// });

// app.get("/employee", (req, res) => {
//   db.query("SELECT * FROM employees", (err, data) => {
//     if (err) return res.status(500).json(err);
//     res.json(data);
//   });
// });

// app.post("/employee", (req, res) => {
//   const { name, position, salary } = req.body;
//   
//   db.query(
//     "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)",
//     [name, position, salary],
//     (err, result) => {
//       if (err) return res.status(500).json(err);
//       res.json({ msg: "Employee Added", id: result.insertId });
//     }
//   );
// });


// app.listen(5000, () => console.log("Server running on port 5000"));
```

Explanation:
- Imports Express and tools (`body-parser`, `cors`) and creates an app instance.
- Mounts the router defined in `routes/LibraryRoute.js` at `/library`.
- Enables CORS for cross-origin requests and JSON body parsing — required for the frontend to call the API.
- Starts the server on port 3333 and logs the listening message.
- The commented-out block is previous/alternative setup code and is inactive.

---

## Backend: `librarybackend/routes/LibraryRoute.js`

```javascript
const express = require('express')
const router = express.Router()

const librarycontroller = require('../controller/LibraryController')

router.get("/books",librarycontroller.getAllBooks)

router.post("/books",librarycontroller.addBook)

router.put("/books/:id",librarycontroller.updateBooks)

router.delete("/books/:id",librarycontroller.deleteBook)


module.exports = router;
```

Explanation:
- Defines an Express router and maps endpoints to controller handlers.
- Endpoints are mounted at `/library` from the server, so full endpoints are `/library/books`, etc.

---

## Backend: `librarybackend/controller/LibraryController.js`

```javascript
const connection = require("../databaseconnection/DbConfig")

exports.getAllBooks=(req,resp)=>{
    connection.query("select * from library",(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:results})
        }
    })
}

exports.addBook=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("insert into library values (?,?,?,?,?)",[id,bname,bauthor,price,year],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:"book added"})
        }
    })
}

exports.updateBooks=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("update library set bname=?, bauthor=?, price=? , year=? where id=?",[bname,bauthor,price,year,id],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:results})
        }
    })
}

exports.deleteBook=(req,resp)=>{
    connection.query("delete from library where id=?",[req.params.id],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:"deleted success"})
        }
    })
}
```

Explanation (line-by-line highlights):
- `getAllBooks`: Runs `SELECT * FROM library`. On success returns JSON `{data: results}` — frontend expects `result.data.data`.
- `addBook`: Reads `id`, `bname`, `bauthor`, `price`, `year` from `req.body` and inserts a new row using placeholders. Returns `{data: "book added"}` on success.
- `updateBooks`: Expects full object in `req.body` and runs `UPDATE` matching `id`. Returns `{data: results}`.
- `deleteBook`: Uses `req.params.id` to delete the row and returns `{data: "deleted success"}`.

Notes:
- The controllers log SQL errors but do not send an HTTP error response. For production, send proper status codes and messages (e.g., `resp.status(500).json({error: err.message})`).
- `INSERT` uses a values-only form; explicit column listing is safer (e.g., `INSERT INTO library (id,bname,bauthor,price,year) VALUES (?,?,?,?,?)`).

---

## Backend: `librarybackend/databaseconnection/DbConfig.js`

```javascript
const mysql = require('mysql2')

const db = mysql.createConnection({
    host:"localhost",
    user:"root",
    password:"aks123",
    database:"expressdb"
})

db.connect((err)=>{
    if(err){
        console.log("Cannot connect to database");
    }
    else{
        console.log("Connection established");
    }
})

module.exports = db;
```

Explanation:
- Creates a MySQL connection using `mysql2` and attempts to connect immediately.
- Exports the connection object for controllers.
- Credentials are hard-coded; consider using environment variables for security.

---

## Frontend: `libraryfrontend/src/service/LibraryService.jsx`

```javascript
import axios from 'axios'
const baseUrl = "http://localhost:3333";

class LibraryService{
    getAllBooks(){
        return axios.get(baseUrl+"/library/books");
    }

    addBook(book){
        let myHeader = {'content-Type':'application/json'}
        return axios.post(baseUrl+"/library/books",book,{headers:myHeader})
    }

    updateBook(book){
        let myHeader = {'content-Type':'application/json'}
        return axios.put(baseUrl+"/library/books/"+book.id,book,{headers:myHeader})
    }

    deleteBook(id){
        return axios.delete(baseUrl+"/library/books/"+id);
    }
}

export default new LibraryService();
```

Explanation:
- Thin wrapper around Axios to call backend endpoints. The base URL points to the backend running on port 3333.
- Methods return Axios promises — callers should handle `.then()`/`.catch()` or use `await`.

---

## Frontend: `libraryfrontend/src/pages/LibraryList.jsx`

```javascript
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService'
import { Link, Navigate } from 'react-router-dom'

export default function LibraryList() {
    const [book, setbook] = useState([])

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData= async()=>{
        var result = await LibraryService.getAllBooks();
        console.log(result);
        setbook(result.data.data)
    }

    const deleteBook=(id)=>{
        LibraryService.deleteBook(id)
        .then(()=>{
            fetchData();
        })
        .catch((err)=>{
            console.log(err);
        })
    }

  return (
    <div>
        <Link to="/form">
        <button>Add Books</button>
        </Link>
      {book.map(b=>(
        <div key={b.id}>
            {b.id} | {b.bname} | {b.bauthor} | {b.price} | {b.year} 

            <Link to={`/edit/${b.id}`} state={{bookdata : b}}>
            <button>Edit</button>
            </Link>

            
            <button onClick={()=>deleteBook(b.id)}>Delete</button>
            
        </div>
      ))}
    </div>
  )
}
```

Explanation:
- Fetches book list on mount and renders each book with Edit/Delete.
- `Edit` link passes the selected book via `location.state.bookdata` to the edit route.
- On delete it calls the service and refetches to refresh UI.

---

## Frontend: `libraryfrontend/src/pages/LibraryForm.jsx`

```javascript
import React from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService';
import { Navigate, useNavigate } from 'react-router-dom';

export default function LibraryForm() {
    const [formdetail, setformdetail] = useState({id:"",bname:"",bauthor:"",price:"",year:""})
    const navigate = useNavigate();
    const handleChange=(e)=>{
        const {name, value} = e.target;
        setformdetail({...formdetail,[name]:value})
    }

    const addBook=(e)=>{
        e.preventDefault();
        if(formdetail.id==="" || formdetail.bname==="" || formdetail.bauthor==="" || formdetail.price === "" || formdetail.year===""){
            alert("Field cant be empty")
        }else{
            LibraryService.addBook(formdetail)
            .then(()=>{
                alert("Book added")
                navigate("/")
            })
        }
    }

  return (
    <div>

        <form onSubmit={addBook}>

        <label>Book id</label>
        <input type="text" name='id' id='id' value={formdetail.id} onChange={handleChange} /><br/><br />

        <label>Book Name</label>
        <input type="text" name='bname' id='bname' value={formdetail.bname} onChange={handleChange} /><br/><br />

        <label>Author Name</label>
        <input type="text" name='bauthor' id='bauthor' value={formdetail.bauthor} onChange={handleChange} /><br/><br />

        <label>Price</label>
        <input type="text" name='price' id='price' value={formdetail.price} onChange={handleChange} /><br/><br />

        <label>Year</label>
        <input type="date" name='year' id='year' value={formdetail.year} onChange={handleChange} /><br/><br />

        <button type='submit'>Add Book</button>
        </form>
    </div>
  )
}
```

Explanation:
- A controlled form that binds each input to `formdetail` fields and validates non-empty values on submit.
- Calls `LibraryService.addBook` to persist and navigates back to list on success.

Notes:
- `id` is collected from the user — consider using server-generated IDs to prevent collisions.
- `year` is an input type `date` — ensure DB column type matches the format stored.

---

## Frontend: `libraryfrontend/src/pages/LibraryEdit.jsx`

```javascript
import React from 'react'
import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LibraryService from '../service/LibraryService';
import { useEffect } from 'react';

export default function LibraryEdit() {
    const [formdetail, setformdetail] = useState({id:"",bname:"",bauthor:"",price:"",year:""})
    const navigate = useNavigate();
    const location = useLocation();
    const handleChange=(e)=>{
        const {name, value} = e.target;
        setformdetail({...formdetail,[name]:value})
    }

    useEffect(()=>{
        setformdetail(location.state.bookdata)
    },[])

    const updateBook=(e)=>{
            e.preventDefault();
            if(formdetail.id==="" || formdetail.bname==="" || formdetail.bauthor==="" || formdetail.price === "" || formdetail.year===""){
                alert("Field cant be empty")
            }else{
                LibraryService.updateBook(formdetail)
                .then(()=>{
                    alert("Book added")
                    navigate("/")
                })
            }
        }

  return (
    <div>

        <form onSubmit={updateBook}>

        <label>Book id</label>
        <input type="text" name='id' id='id' value={formdetail.id} onChange={handleChange} />

        <label>Book Name</label>
        <input type="text" name='bname' id='bname' value={formdetail.bname} onChange={handleChange} />

        <label>Author Name</label>
        <input type="text" name='bauthor' id='bauthor' value={formdetail.bauthor} onChange={handleChange} />

        <label>Price</label>
        <input type="text" name='price' id='price' value={formdetail.price} onChange={handleChange} />

        <label>Year</label>
        <input type="date" name='year' id='year' value={formdetail.year} onChange={handleChange} />

        <button type='submit'>Add Book</button>
        </form>
    </div>
  )
}
```

Explanation:
- Uses `location.state.bookdata` to pre-populate the form on mount. Submits the updated object to `LibraryService.updateBook`.

Caveat:
- If the user navigates directly to `/edit/:id` (page refresh / bookmark), `location.state` may be undefined. To support direct navigation, use `useParams()` to read the id and fetch book data from the server when `location.state` is missing.
- Success message currently reads "Book added" after update — change to "Book updated" for clarity.

---

## Frontend: `libraryfrontend/src/App.jsx`

```javascript
import './App.css'
import LibraryEdit from './pages/LibraryEdit'
import LibraryForm from './pages/LibraryForm'
import LibraryList from './pages/LibraryList'
import {Route, Routes} from 'react-router-dom'

function App() {

  return (
    <>
      <h1>Library Management</h1>
      <Routes>
        <Route path='/' element={<LibraryList/>}></Route>
        <Route path='/form' element={<LibraryForm/>}></Route>
        <Route path='/edit/:id' element={<LibraryEdit/>}></Route>
      </Routes>
    </>
  )
}

export default App
```

Explanation:
- Sets up application routes with `react-router-dom`.
- `LibraryEdit` uses a route param `:id` but currently relies on `location.state` for data; consider fetching when `state` is missing.

---

## Frontend: `libraryfrontend/src/main.jsx`

```javascript
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App.jsx";

createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <StrictMode>
      <App />
    </StrictMode>
  </BrowserRouter>
);
```

Explanation:
- App entry point. Wraps `App` with `BrowserRouter` (for routing) and `StrictMode` (development checks).

---

## Other files: configs, manifests, assets

### `librarybackend/package.json`

```json
{
  "name": "librarybackend",
  "version": "1.0.0",
  "main": "server.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "start": "node server.js"
  },
  "dependencies": {
    "body-parser": "^2.2.1",
    "cors": "^2.8.5",
    "express": "^5.2.1",
    "mysql2": "^3.15.3"
  }
}
```

Notes: Run `npm install` in `librarybackend` to install dependencies and `npm start` to run the server.

### `libraryfrontend/package.json`

```json
{
  "name": "libraryfrontend",
  "private": true,
  "version": "0.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "lint": "eslint .",
    "preview": "vite preview"
  },
  "dependencies": {
    "axios": "^1.13.2",
    "react": "^19.2.0",
    "react-dom": "^19.2.0",
    "react-router-dom": "^7.10.1"
  },
  "devDependencies": {
    "@vitejs/plugin-react": "^5.1.1",
    "eslint": "^9.39.1",
    "vite": "^7.2.4"
  }
}
```

Notes: Run `npm install` then `npm run dev` in `libraryfrontend` to start the dev server.

### `libraryfrontend/index.html`

```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>libraryfrontend</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.jsx"></script>
  </body>
</html>
```

### `libraryfrontend/vite.config.js`

```javascript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
})
```

### `libraryfrontend/src/index.css` (full file)

```css
:root {
  font-family: system-ui, Avenir, Helvetica, Arial, sans-serif;
  line-height: 1.5;
  font-weight: 400;

  color-scheme: light dark;
  color: rgba(255, 255, 255, 0.87);
  background-color: #242424;

  font-synthesis: none;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

a {
  font-weight: 500;
  color: #646cff;
  text-decoration: inherit;
}
a:hover {
  color: #535bf2;
}

body {
  margin: 0;
  display: flex;
  place-items: center;
  min-width: 320px;
  min-height: 100vh;
}

h1 {
  font-size: 3.2em;
  line-height: 1.1;
}

button {
  border-radius: 8px;
  border: 1px solid transparent;
  padding: 0.6em 1.2em;
  font-size: 1em;
  font-weight: 500;
  font-family: inherit;
  background-color: #1a1a1a;
  cursor: pointer;
  transition: border-color 0.25s;
}
button:hover {
  border-color: #646cff;
}
button:focus,
button:focus-visible {
  outline: 4px auto -webkit-focus-ring-color;
}

@media (prefers-color-scheme: light) {
  :root {
    color: #213547;
    background-color: #ffffff;
  }
  a:hover {
    color: #747bff;
  }
  button {
    background-color: #f9f9f9;
  }
}
```

Explanation:
- Global CSS for the project with font defaults, color scheme handling, and base styles for buttons and layout.

### `libraryfrontend/src/App.css` (full)

```css
#root {
  max-width: 1280px;
  margin: 0 auto;
  padding: 2rem;
  text-align: center;
}

.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.react:hover {
  filter: drop-shadow(0 0 2em #61dafbaa);
}

@keyframes logo-spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}

@media (prefers-reduced-motion: no-preference) {
  a:nth-of-type(2) .logo {
    animation: logo-spin infinite 20s linear;
  }
}

.card {
  padding: 2em;
}

.read-the-docs {
  color: #888;
}
```

### `libraryfrontend/src/assets/react.svg` (trimmed)

```xml
<svg xmlns="http://www.w3.org/2000/svg" ...>...SVG content...</svg>
```

### `libraryfrontend/eslint.config.js`

```javascript
import js from '@eslint/js'
import globals from 'globals'
import reactHooks from 'eslint-plugin-react-hooks'
import reactRefresh from 'eslint-plugin-react-refresh'
import { defineConfig, globalIgnores } from 'eslint/config'

export default defineConfig([
  globalIgnores(['dist']),
  {
    files: ['**/*.{js,jsx}'],
    extends: [
      js.configs.recommended,
      reactHooks.configs.flat.recommended,
      reactRefresh.configs.vite,
    ],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
      parserOptions: {
        ecmaVersion: 'latest',
        ecmaFeatures: { jsx: true },
        sourceType: 'module',
      },
    },
    rules: {
      'no-unused-vars': ['error', { varsIgnorePattern: '^[A-Z_]' }],
    },
  },
])
```

### `libraryfrontend/.gitignore`

```gitignore
# Logs
logs
*.log

node_modules
dist

# Editor directories and files
.vscode/*
!.vscode/extensions.json
.idea
.DS_Store
```

### `libraryfrontend/public/vite.svg` (trimmed)

```xml
<svg xmlns="http://www.w3.org/2000/svg" ...>...Vite SVG content...</svg>
```

---

If you'd like any of the following next steps, tell me which and I'll implement it:
- Add numbered inline comments next to each code line for a strict line-by-line mapping.
- Modify `LibraryEdit` to support direct navigation by fetching by `:id`.
- Improve backend error responses and input validation.
- Export this full explanation as syntax-highlighted HTML or a PDF.

File created: `CODE_EXPLANATION_FULL.md` in the repository root.
