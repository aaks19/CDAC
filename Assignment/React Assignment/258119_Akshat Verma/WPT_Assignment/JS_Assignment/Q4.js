function calculator(a, b, operator) {
    switch(operator) {
        case '+': return a + b;
        case '-': return a - b;
        case '*': return a * b;
        case '/': return b !== 0 ? a / b : "Error: Division by zero";
        default: return "Invalid operator";
    }
}

let a = parseFloat(prompt("Enter first number:"));
let b = parseFloat(prompt("Enter second number:"));
let op = prompt("Enter an operator (+, -, *, /):");

alert("Result: " + calculator(a, b, op));
