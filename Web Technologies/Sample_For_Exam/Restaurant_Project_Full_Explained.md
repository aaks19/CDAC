# 🍽️ Restaurant Management System — FULLY EXPLAINED CODE (Backend + Frontend)
This document contains the **entire corrected code**, **detailed explanations**, and a **step‑by‑step flow** for your Restaurant CRUD project.

---

# 🚀 1. BACKEND (Node.js + Express + MySQL)

Folder structure:
```
backend/
 ├─ app.js
 ├─ routes/
 │   └─ restaurantroute.js
 ├─ controller/
 │   └─ restaurantcontroller.js
 └─ databaseconfig/
     └─ dbconfig.js
```

---

## 📌 `app.js` — Main Server File
```js
const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const cors = require('cors')

const restaurantroute = require('./routes/restaurantroute')

app.use(cors())
app.use(bodyParser.json())

app.use("/restaurant", restaurantroute)

app.listen(3333, () => {
    console.log("server running on port 3333");
})
```

### ✅ Explanation  
| Line | Meaning |
|------|---------|
| `const express...` | Imports Express library |
| `const app = express()` | Creates Express app |
| `cors()` | Allows frontend to access backend |
| `bodyParser.json()` | Parses JSON bodies |
| `app.use('/restaurant', route)` | Mounts restaurant routes |
| `app.listen(3333)` | Starts server at port 3333 |

---

## 📌 `routes/restaurantroute.js` — Routing Layer
```js
const express = require('express')
const router = express.Router()

const restaurantcontroller = require('../controller/restaurantcontroller')

router.get("/restaurants", restaurantcontroller.getAllRestaurants)
router.post("/restaurants", restaurantcontroller.insertRestaurant)
router.put("/restaurants/:id", restaurantcontroller.updateRestaurant)
router.delete("/restaurants/:id", restaurantcontroller.deleteRestaurantById)

module.exports = router;
```

### ✅ Explanation  
This file defines all API endpoints:
- `GET /restaurant/restaurants`
- `POST /restaurant/restaurants`
- `PUT /restaurant/restaurants/:id`
- `DELETE /restaurant/restaurants/:id`

---

## 📌 `databaseconfig/dbconfig.js` — MySQL Connection
```js
const mysql = require('mysql2')

const db = mysql.createConnection({
    host:"localhost",
    user:"root",
    password:"aks123",
    database:"expressdb"
})

db.connect((err)=>{
    if(err){
        console.log("Error in mysql");
    }
    else{
        console.log("Connection established");
    }
})

module.exports = db
```

### ✅ Explanation  
Creates a MySQL connection that all controllers reuse.

---

## 📌 `controller/restaurantcontroller.js` — CRUD Logic

### ▶ GET All Restaurants
```js
exports.getAllRestaurants=(req,resp)=>{
    connection.query("select * from restaurants",(err,result)=>{
        if(err){
            console.log(err);
        }else{
            resp.json({data:result})
        }
    })
}
```

### ▶ INSERT Restaurant
```js
exports.insertRestaurant=(req,resp)=>{
    const {id,rname,phone,rlocation} = req.body;
    connection.query("insert into restaurants values(?,?,?,?)",
    [id,rname,phone,rlocation],
    (err)=>{
        if(err){ console.log(err); }
        else{ resp.json({data:"restaurant added"}) }
    })
}
```

### ▶ UPDATE Restaurant
```js
exports.updateRestaurant=(req,resp)=>{
    const {id,rname,phone,rlocation} = req.body;
    connection.query("update restaurants set rname=?, phone=?, rlocation=? where id=?",
    [rname,phone,rlocation,id],
    (err,result)=>{
        if(err){ console.log(err); }
        else{ resp.json({data:result}) }
    })
}
```

### ▶ DELETE Restaurant
```js
exports.deleteRestaurantById=(req,resp)=>{
    connection.query("delete from restaurants where id=?",
    [req.params.id],
    (err)=>{
        if(err){ console.log(err) }
        else{ resp.json({data:"deleted"}) }
    })
}
```

---

# 🎨 2. FRONTEND (React)

Folder:
```
frontend/src/
 ├─ main.jsx
 ├─ App.jsx
 ├─ service/restaurantservice.js
 ├─ pages/
 │   ├─ RestaurantTable.jsx
 │   ├─ RestaurantForm.jsx
 │   └─ RestaurantEdit.jsx
```

---

## 📌 `main.jsx`
```js
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import {BrowserRouter} from 'react-router-dom'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <BrowserRouter>
    <StrictMode>
      <App />
    </StrictMode>
  </BrowserRouter>
)
```

---

## 📌 `App.jsx`
```js
import { Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import RestaurantTable from './pages/restauranttable'
import RestaurantForm from './pages/restaurantform'
import RestaurantEdit from './pages/RestaurantEdit'

function App() {
  return (
    <>
     <Routes>
      <Route path='/' element={<Navigate replace to={"/table"} />}></Route>
      <Route path='/table' element={<RestaurantTable/>}></Route>
      <Route path='/form' element={<RestaurantForm/>}></Route>
      <Route path='/edit/:id' element={<RestaurantEdit/>}></Route>
     </Routes>
    </>
  )
}
export default App
```

---

## 📌 `service/restaurantservice.js`
```js
import axios from 'axios';
const baseUrl = "http://localhost:3333";

class RestaurantService{

    getAllRestaurant(){
        return axios.get(baseUrl+"/restaurant/restaurants");
    }

    addRestaurant(restaurant){
        let myHeader = {'Content-Type':'application/json'};
        return axios.post(baseUrl+"/restaurant/restaurants",restaurant,{headers:myHeader})
    }

    updateRestaurant(restaurant){
        let myHeader = {'Content-Type':'application/json'}
        return axios.put(baseUrl+"/restaurant/restaurants/"+restaurant.id, restaurant,{headers:myHeader})
    }

    deleteRestaurantById(id){
        return axios.delete(baseUrl+"/restaurant/restaurants/"+id);
    }
}
export default new RestaurantService();
```

---

## 📌 `RestaurantTable.jsx`
```js
import React, { useEffect, useState } from 'react'
import {Link} from 'react-router-dom'
import restaurantservice from '../service/restaurantservice';

export default function RestaurantTable() {
    const [restaurantarr , setrestaurantarr] = useState([]);

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData = async()=>{
        var result = await restaurantservice.getAllRestaurant();
        setrestaurantarr(result.data.data);        
    }

    const deleteRestaurant=(id)=>{
        restaurantservice.deleteRestaurantById(id)
        .then(()=>{
            fetchData();
        })
        .catch((err)=> console.log(err))
    }

  return (
    <div>
        <Link to={'/form'}>
        <button>Add Restaurant</button>
      </Link>

      <table>
        <thead>
            <tr>
                <th>Restaurant Id</th>
                <th>Restaurant Name</th>
                <th>Phone</th>
                <th>Location</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            {restaurantarr.map((restaurant)=>(
                <tr key={restaurant.id}>
                    <td>{restaurant.id}</td>
                    <td>{restaurant.rname}</td>
                    <td>{restaurant.phone}</td>
                    <td>{restaurant.rlocation}</td>
                    <td>
                        <Link to={`/edit/${restaurant.id}`} state={{restaurantdata:restaurant}}>
                            <button>Edit</button>
                        </Link>
                        <button onClick={()=>deleteRestaurant(restaurant.id)}>Delete</button>
                    </td>
                </tr>
            ))}
        </tbody>
      </table>
    </div>
  )
}
```

---

## 📌 `RestaurantForm.jsx`
```js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import restaurantservice from "../service/restaurantservice";

export default function RestaurantForm() {
    const navigate = useNavigate();
    const [formdetail, setformdetail] = useState({
        id: "",
        rname: "",
        phone: "",
        rlocation: "",
    });

    const addRestaurant = (event) => {
        event.preventDefault();

        if (!formdetail.id || !formdetail.rname || !formdetail.phone || !formdetail.rlocation) {
            alert("values cant be empty");
            return;
        }

        restaurantservice.addRestaurant(formdetail)
        .then(()=> navigate("/table"))
        .catch(err => console.log(err));
    };

    const handleChange = (event) =>{
        var {name,value} = event.target;
        setformdetail({...formdetail,[name]:value})
    }

    return (
      <form onSubmit={addRestaurant}>
        <input name="id" value={formdetail.id} onChange={handleChange} placeholder="ID"/>
        <input name="rname" value={formdetail.rname} onChange={handleChange} placeholder="Restaurant Name"/>
        <input name="phone" value={formdetail.phone} onChange={handleChange} placeholder="Phone"/>
        <input name="rlocation" value={formdetail.rlocation} onChange={handleChange} placeholder="Location"/>
        <button>Add Restaurant</button>
      </form>
    );
}
```

---

## 📌 `RestaurantEdit.jsx`
```js
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import restaurantservice from '../service/restaurantservice';

export default function RestaurantEdit() {

    const navigate = useNavigate();
    const location = useLocation();

    const [formdetail, setformdetail] = useState({
        id: "",
        rname: "",
        phone: "",
        rlocation: "",
    });

    useEffect(()=>{
        setformdetail(location.state.restaurantdata)
    },[])

    const updateRestaurant = (event) => {
        event.preventDefault();

        if (!formdetail.id || !formdetail.rname || !formdetail.phone || !formdetail.rlocation) {
            alert("values cant be empty");
            return;
        }

        restaurantservice.updateRestaurant(formdetail)
        .then(()=> navigate("/table"))
        .catch(err => console.log(err));
    };

    const handleChange = (event)=>{
        var {name,value} = event.target;
        setformdetail({...formdetail,[name]:value})
    }

    return (
      <form onSubmit={updateRestaurant}>
        <input name="id" value={formdetail.id} onChange={handleChange} readOnly/>
        <input name="rname" value={formdetail.rname} onChange={handleChange}/>
        <input name="phone" value={formdetail.phone} onChange={handleChange}/>
        <input name="rlocation" value={formdetail.rlocation} onChange={handleChange}/>
        <button>Update Restaurant</button>
      </form>
    )
}
```

---

# 📦 PROJECT FLOW — How Files Work Together

```
React UI  →  Axios Service  →  Express Route  →  Controller  →  MySQL DB
```

### Flow Example: Add Restaurant  
1. User fills form → clicks submit  
2. `RestaurantForm.jsx` calls `restaurantservice.addRestaurant()`  
3. Axios sends POST → `/restaurant/restaurants`  
4. Route triggers `insertRestaurant`  
5. Controller inserts into DB  
6. Response returns → React navigates back to table  

---

# 🎯 IMPROVEMENTS  
- Add form validation  
- Add toast notifications  
- Use Sequelize ORM  
- Add pagination  
- Use connection pooling instead of single connection  

---

# ✅ END OF DOCUMENT  
This `.md` is now complete, clean, and suitable for GitHub or PPT explanation.

