let a = parseFloat(prompt("Enter first positive number:"));
let b = parseFloat(prompt("Enter second positive number:"));
let c = parseFloat(prompt("Enter third positive number:"));

let largest = a;
if (b > largest) largest = b;
if (c > largest) largest = c;

alert("The largest number is: " + largest);
