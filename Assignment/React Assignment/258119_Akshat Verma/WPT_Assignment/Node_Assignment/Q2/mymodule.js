
// Factorial function
function factorial(n) {
    let fact = 1;
    for (let i = 1; i <= n; i++) {
        fact *= i;
    }
    return fact;
}

// Check prime number
function myprime(n) {
    if (n <= 1) return false;
    for (let i = 2; i <= Math.sqrt(n); i++) {
        if (n % i === 0) return false;
    }
    return true;
}

// Printable table function
function printable(n) {
    let result = "";
    for (let i = 1; i <= 10; i++) {
        result += `${n} x ${i} = ${n * i}\n`;
    }
    return result;
}

module.exports = { factorial, myprime, printable };
