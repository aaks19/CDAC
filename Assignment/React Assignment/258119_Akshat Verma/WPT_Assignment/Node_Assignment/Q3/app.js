
const circle = require('./circle');
const rectangle = require('./rectangle');
const triangle = require('./triangle');

console.log("=== Circle Functions ===");
console.log("Area:", circle.calcArea(5));
console.log("Circumference:", circle.calcCircumference(5));
console.log("Diameter:", circle.calcDiameter(5));

console.log("\n=== Rectangle Functions ===");
console.log("Area:", rectangle.calcArea(10, 4));
console.log("Perimeter:", rectangle.calcPerimeter(10, 4));

console.log("\n=== Triangle Functions ===");
console.log("Is Equilateral:", triangle.isEquilateral(5, 5, 5));
console.log("Perimeter:", triangle.calcPerimeter(5, 6, 7));