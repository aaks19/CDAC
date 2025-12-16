const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// Hardcoded users array (username,password)
const users = [
    "john,john123",
    "alice,alice456",
    "bob,bob789"
];

// Serve login page
app.get("/", (req, res) => {
    res.sendFile(path.join(__dirname, "login.html"));
});

// Middleware to validate login
function validateLogin(req, res, next) {
    const { username, password } = req.body;

    // Check password length
    if (password.length < 6) {
        return res.sendFile(path.join(__dirname, "failure.html"));
    }

    // Check username,password in array
    const found = users.some(user => {
        const [u, p] = user.split(",");
        return u === username && p === password;
    });

    if (found) {
        next(); // Proceed to success
    } else {
        res.sendFile(path.join(__dirname, "failure.html"));
    }
}

// Handle login
app.post("/login", validateLogin, (req, res) => {
    res.sendFile(path.join(__dirname, "success.html"));
});

// Start server
app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});
