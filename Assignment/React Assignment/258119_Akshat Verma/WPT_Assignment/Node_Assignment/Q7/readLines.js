const fs = require("fs");
const readline = require("readline");

// Create a stream to read the file
const fileStream = fs.createReadStream("sample.txt");

// readline interface to read file line by line
const rl = readline.createInterface({
    input: fileStream,
    crlfDelay: Infinity
});

let lineNumber = 1;

// Read each line
rl.on("line", (line) => {
    console.log(`${lineNumber}: ${line}`);
    lineNumber++;
});