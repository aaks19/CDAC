const PI = Math.PI;

function calcArea(radius) {
    return PI * radius * radius;
}

function calcCircumference(radius) {
    return 2 * PI * radius;
}

module.exports = { calcArea, calcCircumference };