const fs=require('fs')
//default buffersize 65536
//highWaterMark ---will help you to modify the default buffer size
//var rs=fs.createReadStream("mydata.txt","utf-8",{highWaterMark:20})
var rs=fs.createReadStream("mydata.txt","utf-8")
var ws=fs.createWriteStream("myfile.txt");
var value=""
//when buffer with readstream is full it generates data event
rs.on("data",(partialdata)=>{
    console.log("data event occur")
    value=value+partialdata;
    //generate write event to write from writestream to destination
    ws.write(partialdata) 
    fs.appendFile("mydatacopy.txt",partialdata,()=>{
        console.log("data appended")
    })

})
//when file ends readstream  generates end event
rs.on("end",()=>{
    //generate end event on writestream
    ws.end();
    console.log("end event occur")
    console.log(value);
    })