// Check if triangle is equilateral
function isEquilateral(a, b, c) {
    return a === b && b === c;
}

// Perimeter of triangle
function calcPerimeter(a, b, c) {
    return a + b + c;
}

module.exports = {
    isEquilateral,
    calcPerimeter
};