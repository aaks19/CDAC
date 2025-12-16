const products = [
    { id: 101, name: "Apple", category: "Fruits", price: 120, quantity: 10 },
    { id: 102, name: "Banana", category: "Fruits", price: 60, quantity: 25 },
    { id: 103, name: "Carrot", category: "Vegetables", price: 40, quantity: 30 },
    { id: 104, name: "Potato", category: "Vegetables", price: 30, quantity: 50 },
    { id: 105, name: "Milk", category: "Dairy", price: 50, quantity: 15 },
    { id: 106, name: "Cheese", category: "Dairy", price: 150, quantity: 5 }
];

// 1. Add a new product
products.push({ id: 107, name: "chair", category: "furniture", price: 3000, quantity: 20 });

// 2. Remove the last product
products.pop();

// 3. Add a new product at the beginning
products.unshift({ id: 100, name: "Desk", category: "furniture", price: 2000, quantity: 10 });

// 4. Remove the first product
products.shift();

// 5. Display products from 2nd to 6th (slice indices 1 to 6)
console.log("Products from 2nd to 6th:", products.slice(1, 6));

// 6. Remove two products starting from index 2
products.splice(2, 2);
console.log("After removing 2 products from index 2:", products);

// 7. Find product with id = 103
const prod103 = products.find(p => p.id === 103);
console.log("Product with id=103:", prod103);

// 8. Check if any product has quantity less than 5
const anyQtyLess5 = products.some(p => p.quantity < 5);
console.log("Any product with quantity < 5:", anyQtyLess5);

// 9. Check if all products have quantity greater than 0
const allQtyPositive = products.every(p => p.quantity > 0);
console.log("All products quantity > 0:", allQtyPositive);

// 10. Find index of product named "Milk"
const milkIndex = products.findIndex(p => p.name === "Milk");
console.log("Index of Milk:", milkIndex);

// 11. Verify whether "Cheese" exists in product names
const productNames = products.map(p => p.name);
const hasCheese = productNames.includes("Cheese");
console.log("Cheese exists:", hasCheese);

// 12. Create array of product names
console.log("Product names:", productNames);

// 13. Create array of prices
const prices = products.map(p => p.price);
console.log("Prices:", prices);

// 14. Increase price of all products by 10%
const increasedPrices = products.map(p => ({ ...p, price: (p.price * 1.1).toFixed(2) }));
console.log("Products with 10% increased price:", increasedPrices);

// 15. New array of products with category "Fruits"
const fruits = products.filter(p => p.category === "Fruits");
console.log("Fruits:", fruits);

// 16. Sort products by price ascending
const sortedByPrice = [...products].sort((a, b) => a.price - b.price);
console.log("Sorted by price:", sortedByPrice);

// 17. Sort products by name alphabetically
const sortedByName = [...products].sort((a, b) => a.name.localeCompare(b.name));
console.log("Sorted by name:", sortedByName);

// 18. Reverse the sorted array
const reversedArray = [...sortedByName].reverse();
console.log("Reversed sorted array:", reversedArray);
