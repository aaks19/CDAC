// Create a user defined local module calc.js that exposes the following functions: add(a,b),
// subtract(a,b), multiply(a,b), divide(a,b), square(a), sum(a,b,c ... )

// Create a client application that invokes each of these methods

//1. addition
exports.add = (a, b) => {
  console.log("in addition");
  return a + b;
};

//2. Subtract
exports.sub = (a, b) => {
  console.log("in subtraction");
  return a - b;
};

// 3. Multiply
exports.multiply = (a, b) => {
  console.log("in Multiply");
  return a * b;
};

// 4. Divide
exports.divide = (a, b) => {
  if (b !== 0) {
    console.log("in Division");
    return a / b;
  }
  console.log("Can't divide by 0");
};

//5. Square
exports.square=(a)=>{
    console.log("in Square")
    return a*a;
}

exports.sum=(a,b,...c)=>{
    console.log("in Sum")
    var vals = [a,b,...c];
    var total = vals.reduce((acc,vsum) => {acc+vsum},0)
    return total;
}