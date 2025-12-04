# Library Management System - Complete Project Documentation

This document provides a comprehensive overview of the entire Library Management System project, including all backend and frontend code with detailed line-by-line explanations.

---

## Table of Contents
1. [Backend - Server.js (Consolidated)](#backend---serverjs-consolidated)
2. [Backend - Package.json](#backend---packagejson)
3. [Frontend - Service Layer](#frontend---service-layer)
4. [Frontend - Pages](#frontend---pages)
5. [Frontend - App Router](#frontend---app-router)
6. [Project Flow & Architecture](#project-flow--architecture)

---

## Backend - Server.js (Consolidated)

This is the main backend file that contains the database connection, configuration, and all API routes (previously split into separate files).

### Complete Code

```javascript
const express = require("express");
const mysql = require("mysql2");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

// DB Connection
const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "aks123",
  database: "expressdb"
});

db.connect((err) => {
  if (err) {
    console.log("Database error:", err);
  } else {
    console.log("MySQL Connected");
  }
});

// GET all books
app.get("/library/books", (req, res) => {
  db.query("SELECT * FROM library", (err, results) => {
    if (err) return res.status(500).json(err);
    res.json(results);
  });
});

// ADD a book
app.post("/library/books", (req, res) => {
  const { id, bname, bauthor, price, year } = req.body;

  db.query(
    "INSERT INTO library (id, bname, bauthor, price, year) VALUES (?, ?, ?, ?, ?)",
    [id, bname, bauthor, price, year],
    (err, result) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Added", id: result.insertId });
    }
  );
});

// UPDATE book
app.put("/library/books/:id", (req, res) => {
  const { bname, bauthor, price, year } = req.body;

  db.query(
    "UPDATE library SET bname=?, bauthor=?, price=?, year=? WHERE id=?",
    [bname, bauthor, price, year, req.params.id],
    (err, results) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Updated" });
    }
  );
});

// DELETE book
app.delete("/library/books/:id", (req, res) => {
  db.query(
    "DELETE FROM library WHERE id=?",
    [req.params.id],
    (err, results) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Deleted" });
    }
  );
});

// Start server
app.listen(3333, () => console.log("Server running on port 3333"));
```

### Line-by-Line Explanation

**Lines 1-3: Import Dependencies**
```javascript
const express = require("express");
const mysql = require("mysql2");
const cors = require("cors");
```
- **express**: Web framework for building HTTP server and APIs
- **mysql2**: Node.js driver for MySQL database connections
- **cors**: Middleware to allow cross-origin requests from frontend

**Lines 5-7: Initialize Express App & Middleware**
```javascript
const app = express();
app.use(cors());
app.use(express.json());
```
- Creates Express application instance
- `app.use(cors())`: Enables CORS so frontend (different port) can make API calls
- `app.use(express.json())`: Parses incoming JSON request bodies and makes them available in `req.body`

**Lines 9-17: Database Connection Configuration**
```javascript
const db = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "aks123",
  database: "expressdb"
});
```
- Creates MySQL connection object with:
  - **host**: MySQL server location (localhost = same machine)
  - **user**: MySQL username (root)
  - **password**: MySQL password (aks123)
  - **database**: Database name (expressdb)

**Lines 19-26: Connect to Database**
```javascript
db.connect((err) => {
  if (err) {
    console.log("Database error:", err);
  } else {
    console.log("MySQL Connected");
  }
});
```
- Attempts to connect to MySQL database
- If error occurs, logs the error
- If successful, logs "MySQL Connected" to console

**Lines 29-35: GET Endpoint - Fetch All Books**
```javascript
app.get("/library/books", (req, res) => {
  db.query("SELECT * FROM library", (err, results) => {
    if (err) return res.status(500).json(err);
    res.json(results);
  });
});
```
- **Route**: `GET /library/books`
- **SQL Query**: `SELECT * FROM library` - fetches all records from library table
- **Error Handling**: If query fails, returns HTTP 500 status with error details
- **Response**: Returns array of all books directly (e.g., `[{id: 1, bname: "Book1", ...}, ...]`)

**Lines 38-50: POST Endpoint - Add New Book**
```javascript
app.post("/library/books", (req, res) => {
  const { id, bname, bauthor, price, year } = req.body;

  db.query(
    "INSERT INTO library (id, bname, bauthor, price, year) VALUES (?, ?, ?, ?, ?)",
    [id, bname, bauthor, price, year],
    (err, result) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Added", id: result.insertId });
    }
  );
});
```
- **Route**: `POST /library/books`
- **Destructuring**: Extracts id, bname, bauthor, price, year from request body
- **Parameterized Query**: Uses `?` placeholders to prevent SQL injection attacks
- **Parameters Array**: `[id, bname, bauthor, price, year]` replaces placeholders in order
- **Success Response**: Returns `{message: "Book Added", id: insertId}` where insertId is the auto-generated ID

**Lines 53-65: PUT Endpoint - Update Book**
```javascript
app.put("/library/books/:id", (req, res) => {
  const { bname, bauthor, price, year } = req.body;

  db.query(
    "UPDATE library SET bname=?, bauthor=?, price=?, year=? WHERE id=?",
    [bname, bauthor, price, year, req.params.id],
    (err, results) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Updated" });
    }
  );
});
```
- **Route**: `PUT /library/books/:id` (`:id` is URL parameter)
- **Parameters**: Gets book details from body, book id from URL
- **SQL Update**: Updates specific fields for the book matching the id
- **Note**: id from URL (`req.params.id`) is placed last in the parameters array to match the WHERE clause
- **Response**: Returns success message if update succeeds

**Lines 68-79: DELETE Endpoint - Remove Book**
```javascript
app.delete("/library/books/:id", (req, res) => {
  db.query(
    "DELETE FROM library WHERE id=?",
    [req.params.id],
    (err, results) => {
      if (err) return res.status(500).json(err);
      res.json({ message: "Book Deleted" });
    }
  );
});
```
- **Route**: `DELETE /library/books/:id`
- **Purpose**: Deletes a book record from database by id
- **Response**: Returns success message if deletion succeeds

**Lines 82: Start Server**
```javascript
app.listen(3333, () => console.log("Server running on port 3333"));
```
- Starts the Express server on port 3333
- Logs message when server is ready to receive requests

### Database Schema

The `library` table has the following structure:
```
Column  | Type    | Description
--------|---------|---------------------------------------------
id      | INT     | Primary key, unique identifier for each book
bname   | VARCHAR | Book name/title
bauthor | VARCHAR | Author name
price   | DECIMAL | Book price
year    | DATE    | Publication year or date
```

---

## Backend - Package.json

### Complete Code

```json
{
  "name": "librarybackend",
  "version": "1.0.0",
  "main": "server.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "start": "node server.js"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "description": "",
  "dependencies": {
    "body-parser": "^2.2.1",
    "cors": "^2.8.5",
    "express": "^5.2.1",
    "mysql2": "^3.15.3"
  }
}
```

### Explanation

- **name**: Package identifier ("librarybackend")
- **version**: Project version (1.0.0)
- **main**: Entry point for the application (server.js)
- **scripts**:
  - `"start": "node server.js"` - Command to run: `npm start`
- **dependencies**: 
  - **express**: Web framework (v5.2.1)
  - **mysql2**: MySQL driver (v3.15.3)
  - **cors**: Cross-origin resource sharing middleware (v2.8.5)
  - **body-parser**: JSON body parsing (v2.2.1)

---

## Frontend - Service Layer

### File: `src/service/LibraryService.jsx`

This service file handles all HTTP communication with the backend API.

### Complete Code

```jsx
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

### Line-by-Line Explanation

**Lines 1-2: Import & Configuration**
```jsx
import axios from 'axios'
const baseUrl = "http://localhost:3333";
```
- **axios**: HTTP client library for making API requests
- **baseUrl**: Backend server URL (localhost:3333 where Node.js server runs)

**Lines 4-23: LibraryService Class**

**Method 1: getAllBooks()**
```jsx
getAllBooks(){
    return axios.get(baseUrl+"/library/books");
}
```
- **HTTP Method**: GET
- **Endpoint**: `http://localhost:3333/library/books`
- **Purpose**: Fetches all books from database
- **Returns**: Promise with array of books

**Method 2: addBook(book)**
```jsx
addBook(book){
    let myHeader = {'content-Type':'application/json'}
    return axios.post(baseUrl+"/library/books",book,{headers:myHeader})
}
```
- **HTTP Method**: POST
- **Endpoint**: `http://localhost:3333/library/books`
- **Parameter**: `book` object (id, bname, bauthor, price, year)
- **Header**: Specifies JSON content type
- **Purpose**: Sends new book data to backend for insertion

**Method 3: updateBook(book)**
```jsx
updateBook(book){
    let myHeader = {'content-Type':'application/json'}
    return axios.put(baseUrl+"/library/books/"+book.id,book,{headers:myHeader})
}
```
- **HTTP Method**: PUT
- **Endpoint**: `http://localhost:3333/library/books/{book.id}`
- **Parameter**: `book` object with updated details
- **Purpose**: Updates existing book record
- **Note**: Uses book.id from the object to construct URL

**Method 4: deleteBook(id)**
```jsx
deleteBook(id){
    return axios.delete(baseUrl+"/library/books/"+id);
}
```
- **HTTP Method**: DELETE
- **Endpoint**: `http://localhost:3333/library/books/{id}`
- **Parameter**: Book id to delete
- **Purpose**: Removes book from database

**Line 25: Export Singleton**
```jsx
export default new LibraryService();
```
- Creates single instance of LibraryService class
- Exported as default so other components can import it
- Ensures all components use same service instance

---

## Frontend - Pages

### Page 1: LibraryList.jsx

Displays all books with search functionality, and buttons to add, edit, or delete books.

#### Complete Code

```jsx
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService'
import { Link, Navigate } from 'react-router-dom'

export default function LibraryList() {
    const [book, setbook] = useState([])
    const [ searchtxt, setsearchtxt] = useState("")

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData= async()=>{
        var result = await LibraryService.getAllBooks();
        console.log(result);
        setbook(result.data)
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

    const handleChange=(e)=>{
        setsearchtxt(e.target.value)
    }

  return (
    <div>

        <label htmlFor="search">Search Book : </label>
        <input type="text" name="searchtxt" id="search" value={searchtxt} onChange={handleChange} /><br />
        <br />

        <Link to="/form">
        <button>Add Books</button>
        </Link>
      {book.filter((b)=>b.bname.toLowerCase().includes(searchtxt.toLowerCase())).map(b=>(
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

#### Line-by-Line Explanation

**Lines 1-5: Imports**
```jsx
import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService'
import { Link, Navigate } from 'react-router-dom'
```
- **React**: Core React library
- **useEffect**: Hook to run side effects (fetch data on component mount)
- **useState**: Hook to manage component state (books list, search text)
- **LibraryService**: Custom service for API calls
- **Link, Navigate**: Router components for navigation

**Lines 8-9: State Management**
```jsx
const [book, setbook] = useState([])
const [ searchtxt, setsearchtxt] = useState("")
```
- **book**: Stores array of all books fetched from database
- **searchtxt**: Stores current search text entered by user

**Lines 11-13: useEffect Hook**
```jsx
useEffect(()=>{
    fetchData();
},[])
```
- Runs once when component mounts (empty dependency array `[]`)
- Calls `fetchData()` to load all books from database
- Similar to `componentDidMount()` in class components

**Lines 15-19: fetchData Function**
```jsx
const fetchData= async()=>{
    var result = await LibraryService.getAllBooks();
    console.log(result);
    setbook(result.data)
}
```
- **async/await**: Handles asynchronous API call
- Calls `LibraryService.getAllBooks()` to fetch books
- `result.data` contains the array of books
- `setbook(result.data)` updates state with fetched books
- Console logs result for debugging

**Lines 21-29: deleteBook Function**
```jsx
const deleteBook=(id)=>{
    LibraryService.deleteBook(id)
    .then(()=>{
        fetchData();
    })
    .catch((err)=>{
        console.log(err);
    })
}
```
- Called when user clicks Delete button
- Calls backend delete API
- **then()**: If successful, refetches all books to update list
- **catch()**: If error, logs it to console

**Lines 31-33: handleChange Function**
```jsx
const handleChange=(e)=>{
    setsearchtxt(e.target.value)
}
```
- Updates search text state as user types in search box
- `e.target.value` gets the current input value

**Lines 36-39: Search Box JSX**
```jsx
<label htmlFor="search">Search Book : </label>
<input type="text" name="searchtxt" id="search" value={searchtxt} onChange={handleChange} /><br />
<br />
```
- Text input for searching books by name
- **value={searchtxt}**: Displays current search text
- **onChange={handleChange}**: Updates state on each keystroke

**Lines 41-44: Add Books Button**
```jsx
<Link to="/form">
<button>Add Books</button>
</Link>
```
- Link to add new book form page (`/form` route)

**Lines 45-56: Display & Filter Books**
```jsx
{book.filter((b)=>b.bname.toLowerCase().includes(searchtxt.toLowerCase())).map(b=>(
    <div key={b.id}>
        {b.id} | {b.bname} | {b.bauthor} | {b.price} | {b.year} 

        <Link to={`/edit/${b.id}`} state={{bookdata : b}}>
        <button>Edit</button>
        </Link>

        <button onClick={()=>deleteBook(b.id)}>Delete</button>
    </div>
))}
```
- **filter()**: Filters books based on search text
  - `b.bname.toLowerCase().includes(searchtxt.toLowerCase())` - case-insensitive search
- **map()**: Renders each filtered book
- **Display**: Shows id, name, author, price, year
- **Edit Link**: Passes current book data via `state={{bookdata: b}}`
- **Delete Button**: Calls deleteBook with book id

---

### Page 2: LibraryForm.jsx

Form for adding new books to the library.

#### Complete Code

```jsx
import React from "react";
import { useState } from "react";
import LibraryService from "../service/LibraryService";
import { Navigate, useNavigate } from "react-router-dom";

export default function LibraryForm() {
  const [formdetail, setformdetail] = useState({
    id: "",
    bname: "",
    bauthor: "",
    price: "",
    year: "",
  });
  const navigate = useNavigate();
  const handleChange = (e) => {
    const { name, value } = e.target;
    setformdetail({ ...formdetail, [name]: value });
  };

  const addBook = (e) => {
    e.preventDefault();
    if (
      formdetail.id === "" ||
      formdetail.bname === "" ||
      formdetail.bauthor === "" ||
      formdetail.price === "" ||
      formdetail.year === ""
      || formdetail.price<=0
    ) {
      alert("Field cant be empty");
    } else {
      LibraryService.addBook(formdetail).then(() => {
        alert("Book added");
        navigate("/");
      });
    }
  };

  return (
    <div>
      <form onSubmit={addBook}>
        <label>Book id</label>
        <input
          type="text"
          name="id"
          id="id"
          value={formdetail.id}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Book Name</label>
        <input
          type="text"
          name="bname"
          id="bname"
          value={formdetail.bname}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Author Name</label>
        <input
          type="text"
          name="bauthor"
          id="bauthor"
          value={formdetail.bauthor}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Price</label>
        <input
          type="text"
          name="price"
          id="price"
          value={formdetail.price}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Year</label>
        <input
          type="date"
          name="year"
          id="year"
          value={formdetail.year}
          onChange={handleChange}
        />
        <br />
        <br />

        <button type="submit">Add Book</button>
      </form>
    </div>
  );
}
```

#### Line-by-Line Explanation

**Lines 1-4: Imports**
```jsx
import React from "react";
import { useState } from "react";
import LibraryService from "../service/LibraryService";
import { Navigate, useNavigate } from "react-router-dom";
```
- **useState**: For managing form state
- **LibraryService**: To call addBook API
- **useNavigate**: To redirect after successful addition

**Lines 7-13: Form State**
```jsx
const [formdetail, setformdetail] = useState({
  id: "",
  bname: "",
  bauthor: "",
  price: "",
  year: "",
});
```
- Single state object stores all form fields
- Each field initialized as empty string
- More efficient than multiple useState calls

**Line 14: useNavigate Hook**
```jsx
const navigate = useNavigate();
```
- Gets navigation function to redirect user after book is added
- Used to go back to home page (`navigate("/")`) after successful submission

**Lines 15-18: handleChange Function**
```jsx
const handleChange = (e) => {
    const { name, value } = e.target;
    setformdetail({ ...formdetail, [name]: value });
};
```
- **name**: Name attribute of the input field
- **value**: What user typed
- **spread operator `...formdetail`**: Keeps other fields unchanged
- **[name]: value**: Updates only the changed field using computed property name

**Lines 20-35: addBook Function**
```jsx
const addBook = (e) => {
    e.preventDefault();
    if (
      formdetail.id === "" ||
      formdetail.bname === "" ||
      formdetail.bauthor === "" ||
      formdetail.price === "" ||
      formdetail.year === ""
      || formdetail.price<=0
    ) {
      alert("Field cant be empty");
    } else {
      LibraryService.addBook(formdetail).then(() => {
        alert("Book added");
        navigate("/");
      });
    }
};
```
- **e.preventDefault()**: Prevents default form submission (page reload)
- **Validation**: Checks if any field is empty or price is ≤ 0
- **If invalid**: Shows alert message
- **If valid**: 
  - Calls `LibraryService.addBook()` with form data
  - Shows success alert
  - Redirects to home page using `navigate("/")`

**Lines 37-96: JSX Form**
- Controlled form inputs (value and onChange bound to state)
- Five input fields: id, book name, author name, price, year
- Year uses `type="date"` for date picker
- Submit button triggers `addBook` function

---

### Page 3: LibraryEdit.jsx

Form for editing existing books.

#### Complete Code

```jsx
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

#### Line-by-Line Explanation

**Lines 1-5: Imports**
```jsx
import React from 'react'
import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LibraryService from '../service/LibraryService';
import { useEffect } from 'react';
```
- **useLocation**: Gets passed book data from LibraryList component
- **useNavigate**: For redirecting after update
- **useEffect**: To populate form with existing book data

**Lines 8-9: State & Navigation**
```jsx
const [formdetail, setformdetail] = useState({id:"",bname:"",bauthor:"",price:"",year:""})
const navigate = useNavigate();
```
- Form state initialized with empty values (will be populated from location.state)
- Navigation hook for redirect

**Line 10: useLocation Hook**
```jsx
const location = useLocation();
```
- Gets location object which contains `state.bookdata` passed from LibraryList
- Contains the book object to edit

**Lines 11-14: handleChange Function**
```jsx
const handleChange=(e)=>{
    const {name, value} = e.target;
    setformdetail({...formdetail,[name]:value})
}
```
- Same as in LibraryForm
- Updates form state as user changes fields

**Lines 16-18: useEffect Hook**
```jsx
useEffect(()=>{
    setformdetail(location.state.bookdata)
},[])
```
- Runs once when component mounts
- Populates form with existing book data from location.state
- Receives data passed from LibraryList component

**Lines 20-30: updateBook Function**
```jsx
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
```
- Validates all fields are filled
- Calls `LibraryService.updateBook()` with modified data
- Redirects to home after successful update
- **Note**: Same as addBook, but calls updateBook service method instead

**Lines 32-58: JSX Form**
- Same fields as LibraryForm
- Pre-populated with existing book data from useEffect
- Submit button labeled "Add Book" (should ideally be "Update Book")

---

## Frontend - App Router

### File: `src/App.jsx`

Main application component that sets up routing between pages.

#### Complete Code

```jsx
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

#### Line-by-Line Explanation

**Lines 1-5: Imports**
```jsx
import './App.css'
import LibraryEdit from './pages/LibraryEdit'
import LibraryForm from './pages/LibraryForm'
import LibraryList from './pages/LibraryList'
import {Route, Routes} from 'react-router-dom'
```
- **App.css**: Styling for the app
- **Page components**: LibraryEdit, LibraryForm, LibraryList
- **Route, Routes**: React Router components for client-side routing

**Lines 7-17: App Component & Routes**
```jsx
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
```
- **h1**: Application title displayed on all pages
- **Route 1**: `path='/'` renders `LibraryList` component (home/list view)
- **Route 2**: `path='/form'` renders `LibraryForm` component (add new book)
- **Route 3**: `path='/edit/:id'` renders `LibraryEdit` component (edit existing book)
  - `:id` is URL parameter (e.g., `/edit/5` to edit book with id 5)

---

## Project Flow & Architecture

### Data Flow Diagram

```
USER INTERACTION
     ↓
Frontend Components (React)
     ↓
LibraryService (Axios HTTP calls)
     ↓
Backend API (Express routes)
     ↓
MySQL Database
     ↓
Response back to Frontend
     ↓
UI Updates
```

### Complete User Journey

#### 1. View Books (Home Page)
```
User visits app → App.jsx routes to '/' → LibraryList component
→ useEffect triggers → fetchData() called → LibraryService.getAllBooks()
→ axios.get("/library/books") → Backend processes GET request
→ DB query: SELECT * FROM library → Returns all books
→ Books displayed in list with Edit/Delete buttons
```

#### 2. Add New Book
```
User clicks "Add Books" button → Links to '/form' → LibraryForm component
→ User fills form (id, name, author, price, year)
→ Form onChange updates formdetail state
→ User clicks Submit → addBook() validates input
→ LibraryService.addBook(formdetail)
→ axios.post("/library/books", data) → Backend processes POST request
→ DB query: INSERT INTO library VALUES(...) → Book added
→ Success alert → navigate("/") → Back to home list
```

#### 3. Edit Book
```
User clicks "Edit" on a book → Links to '/edit/5' with state={bookdata: book}
→ LibraryEdit component mounts
→ useEffect triggers → Sets formdetail from location.state.bookdata
→ Form pre-populated with current book details
→ User modifies fields → handleChange updates formdetail state
→ User clicks Submit → updateBook() validates
→ LibraryService.updateBook(formdetail)
→ axios.put("/library/books/5", data) → Backend processes PUT request
→ DB query: UPDATE library SET... WHERE id=5 → Book updated
→ Success alert → navigate("/") → Back to home list
```

#### 4. Delete Book
```
User clicks "Delete" button → deleteBook(id) called
→ LibraryService.deleteBook(id)
→ axios.delete("/library/books/5") → Backend processes DELETE request
→ DB query: DELETE FROM library WHERE id=5 → Book removed
→ fetchData() called → List refreshed
→ Deleted book no longer appears in list
```

### Architecture Summary

**Backend Architecture**
- **Single server.js file** containing:
  - MySQL database connection configuration
  - All CRUD API endpoints
  - Error handling with proper HTTP status codes
- **Port**: 3333
- **Database**: MySQL with library table
- **API Endpoints**:
  - GET /library/books
  - POST /library/books
  - PUT /library/books/:id
  - DELETE /library/books/:id

**Frontend Architecture**
- **React SPA** with client-side routing
- **Service Layer**: LibraryService encapsulates all API calls
- **Pages**:
  - LibraryList: Display & search books
  - LibraryForm: Add new books
  - LibraryEdit: Modify existing books
- **Port**: 5173 (Vite dev server) or 3000 (production)

**Technology Stack**
- **Backend**: Node.js, Express, MySQL2, CORS
- **Frontend**: React 19, React Router DOM, Axios, Vite
- **Communication**: HTTP REST APIs with JSON payload

---

## Setup & Running Instructions

### Backend Setup
```bash
cd librarybackend
npm install
npm start
# Server runs on http://localhost:3333
```

### Frontend Setup
```bash
cd libraryfrontend
npm install
npm run dev
# App runs on http://localhost:5173
```

### Database Setup
```sql
CREATE DATABASE expressdb;
USE expressdb;

CREATE TABLE library (
  id INT PRIMARY KEY,
  bname VARCHAR(255),
  bauthor VARCHAR(255),
  price DECIMAL(10, 2),
  year DATE
);
```

---

## Summary

This Library Management System demonstrates a complete full-stack application:
- **Backend**: Consolidated Node.js/Express server with all routes, DB connection, and CRUD operations
- **Frontend**: React application with routing, forms, list view, and search functionality
- **Database**: MySQL for persistent data storage
- **Communication**: RESTful API with JSON payloads

All business logic and API communication flows through the LibraryService, maintaining clean separation of concerns.
