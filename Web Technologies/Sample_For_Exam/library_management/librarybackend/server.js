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
