
const PI = Math.PI;

// Area of a circle
function calcArea(radius) {
    return PI * radius * radius;
}

// Circumference of a circle
function calcCircumference(radius) {
    return 2 * PI * radius;
}

// Diameter of a circle
function calcDiameter(radius) {
    return 2 * radius;
}

module.exports = {
    calcArea,
    calcCircumference,
    calcDiameter
};