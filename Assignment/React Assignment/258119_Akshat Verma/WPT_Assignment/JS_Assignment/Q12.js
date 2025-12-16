// Rectangle
function Rectangle(length, width){
    this.length = length;
    this.width = width;
    this.area = function(){ return this.length * this.width; };
    this.perimeter = function(){ return 2 * (this.length + this.width); };
}

// Circle
function Circle(radius){
    this.radius = radius;
    this.area = function(){ return Math.PI * this.radius * this.radius; };
    this.circumference = function(){ return 2 * Math.PI * this.radius; };
}

let rect = new Rectangle(5, 10);
let circ = new Circle(7);

console.log(`Rectangle - Length: ${rect.length}, Width: ${rect.width}, Area: ${rect.area()}, Perimeter: ${rect.perimeter()}`);
console.log(`Circle - Radius: ${circ.radius}, Area: ${circ.area().toFixed(2)}, Circumference: ${circ.circumference().toFixed(2)}`);
