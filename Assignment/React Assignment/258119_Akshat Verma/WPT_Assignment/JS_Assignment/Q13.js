let words = ["apple", "banana", "orange", "grape", "melon"];
let reversedWords = words.map(word => word.split('').reverse().join(''));
reversedWords.sort();
console.log("Reversed & Sorted Words:", reversedWords);
