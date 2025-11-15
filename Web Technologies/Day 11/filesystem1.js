const fs=require("fs");
console.log("before file read sync")
var data=fs.readFileSync("mydata.txt")
console.log(data.toString());
console.log("after file read sync")