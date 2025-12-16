
const express = require("express");
const bodyParser = require("body-parser");
const circle = require("./circle");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// Display form
app.get("/", (req, res) => {
    res.send(`
        <h2>Circle Calculator</h2>
        <form action="/" method="POST">
            <label>Enter Radius:</label>
            <input type="number" name="radius" required>
            <button type="submit">Calculate</button>
        </form>
    `);
});

// Handle form submission
app.post("/", (req, res) => {
    const radius = parseFloat(req.body.radius);

    const area = circle.calcArea(radius);
    const circumference = circle.calcCircumference(radius);

    res.send(`
        <h2>Circle Calculations</h2>
        <p>Radius: ${radius}</p>
        <p>Area: ${area.toFixed(2)}</p>
        <p>Circumference: ${circumference.toFixed(2)}</p>
        <br><a href="/">Go Back</a>
    `);
});

// Start server
app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});