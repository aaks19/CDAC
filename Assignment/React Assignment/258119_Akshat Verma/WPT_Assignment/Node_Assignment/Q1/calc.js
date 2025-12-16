// Add two numbers
function add(a, b) {
    return a + b;
}

// Subtract two numbers
function subtract(a, b) {
    return a - b;
}

// Multiply two numbers
function multiply(a, b) {
    return a * b;
}

// Divide two numbers
function divide(a, b) {
    if (b === 0) {
        return "Error: Division by zero";
    }
    return a / b;
}

// Square of a number
function square(a) {
    return a * a;
}

// Sum of any number of arguments
function sum(...numbers) {
    return numbers.reduce((acc, curr) => acc + curr, 0);
}

// Export functions
module.exports = {
    add,
    subtract,
    multiply,
    divide,
    square,
    sum
};