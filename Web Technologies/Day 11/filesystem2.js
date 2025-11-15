const fs=require('fs')
console.log("before file Read async")
fs.readFile("mydata.txt",(err,fdata)=>{
    if(err){
        console.log(err)
    }else{
      console.log(fdata.toString())
    }
    
})

console.log("after readfile async")
console.log("before readFilesync")
var data=fs.readFileSync("mydata.txt")
console.log(data.toString());
console.log("after readFilesync")