let now = new Date();
let options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
let formattedDate = now.toLocaleDateString('en-US', options);

let hours = now.getHours();
let greeting = hours < 12 ? "Good Morning" :
               hours < 17 ? "Good Afternoon" : "Good Evening";

let endOfYear = new Date(now.getFullYear(), 11, 31);
let daysLeft = Math.ceil((endOfYear - now)/(1000*60*60*24));

console.log(`Today is ${formattedDate}, Welcome, and ${greeting} to You.`);
console.log(`Number of days left till end of year: ${daysLeft}`);
