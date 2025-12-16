function listEvenNumbers(n) {
    let i = 2;
    while (i <= n) {
        console.log(i);
        i += 2;
    }
}

let n = parseInt(prompt("Enter a number n:"));
listEvenNumbers(n);
