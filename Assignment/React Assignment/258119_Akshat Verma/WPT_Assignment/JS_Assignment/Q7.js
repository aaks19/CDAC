function myPow(x, y) {
    let result = 1;
    for (let i = 0; i < y; i++) {
        result *= x;
    }
    return result;
}

let base = parseInt(prompt("Enter base x:"));
let exponent = parseInt(prompt("Enter exponent y:"));

alert(`${base}^${exponent} = ${myPow(base, exponent)}`);
