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
