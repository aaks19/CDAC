const fs=require("fs");
var ws=fs.createWriteStream("mydata22.txt","utf-8");

var data1="this is data 1"
var data2="this is data 2";
ws.write(data1,(err)=>{
    if(err){
        console.log(err);
    }
})
ws.write(data2,(err)=>{
    if(err){
        console.log(err);
    }
})
ws.end()