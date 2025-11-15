const fs=require("fs")

const rs=fs.createReadStream("mydata.txt")
const ws=fs.createWriteStream("mydata333.txt")

//pipe will read data from read stream and write it to destination, 
// i.e writestream
rs.pipe(ws);