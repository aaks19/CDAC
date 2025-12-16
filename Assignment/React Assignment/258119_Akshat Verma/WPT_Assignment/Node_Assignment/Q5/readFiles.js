const fs = require("fs");

// Function to count words
function countWords(text) {
    // Split on spaces, newlines, tabs, and filter empty strings
    return text.trim().split(/\s+/).length;
}

// Read both files asynchronously in parallel
fs.readFile("mydata.txt", "utf8", (err, data1) => {
    if (err) return console.log("Error reading mydata.txt:", err);

    fs.readFile("myfile.data", "utf8", (err, data2) => {
        if (err) return console.log("Error reading myfile.data:", err);

        // Count words
        const wordsFile1 = countWords(data1);
        const wordsFile2 = countWords(data2);

        // Display results
        console.log(`Number of words in mydata.txt: ${wordsFile1}`);
        console.log(`Number of words in myfile.data: ${wordsFile2}`);
    });
});