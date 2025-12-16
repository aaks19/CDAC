const express = require("express");
const bodyParser = require("body-parser");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// Serve form
app.get("/", (req, res) => {
    res.send(`
        <h2>Prime Number Checker</h2>
        <form method="POST">
            <label>Enter a number:</label>
            <input type="number" name="number" required />
            <button type="submit">Check</button>
        </form>
    `);
});

// Handle form submission
app.post("/", (req, res) => {
    const num = parseInt(req.body.number);
    let isPrime = true;

    if (num <= 1) isPrime = false;
    else {
        for (let i = 2; i <= Math.sqrt(num); i++) {
            if (num % i === 0) {
                isPrime = false;
                break;
            }
        }
    }

    const message = isPrime
        ? `${num} is a prime number`
        : `${num} is NOT a prime number`;

    res.send(`
        <h2>Result:</h2>
        <p>${message}</p>
        <a href="/">Check another number</a>
    `);
});

// Start server
app.listen(3000, () => {
    console.log("Server running at http://localhost:3000");
});
