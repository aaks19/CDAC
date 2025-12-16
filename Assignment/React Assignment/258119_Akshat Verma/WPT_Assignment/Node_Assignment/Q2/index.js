
const express = require("express");
const bodyParser = require("body-parser");
const mymodule = require("./mymodule");

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// Show form
app.get("/", (req, res) => {
    res.send(`
        <h2>Enter a Number</h2>
        <form method="POST">
            <input type="number" name="num" required />
            <button type="submit">Submit</button>
        </form>
    `);
});

// Handle form submission
app.post("/", (req, res) => {
    const num = parseInt(req.body.num);
    let output = "";

    if (num < 5) {
        output = `Factorial of ${num} is: ${mymodule.factorial(num)}`;
    } 
    else if (num > 5 && num < 10) {
        output = `<pre>${mymodule.printable(num)}</pre>`;
    } 
    else {
        output = mymodule.myprime(num)
            ? `${num} is a prime number`
            : `${num} is NOT a prime number`;
    }

    res.send(`
        <h2>Result:</h2>
        <p>${output}</p>
        <a href="/">Try Again</a>
    `);
});

// Server start
app.listen(3000, () => console.log("Server running on http://localhost:3000"));