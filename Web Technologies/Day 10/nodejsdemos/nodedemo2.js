const fs=require('fs');

fs.readFile("mydata1.txt",(err,data)=>{
    if(err){
        console.log(err);
    }else{
        console.log(data.toString())
        console.log("--------------------------")
    }
})
fs.readFile("mydata.txt",(err,data)=>{
    if(err){
        console.log(err);
    }else{
        console.log(data.toString())
         console.log("--------------------------")
    }

})


setImmediate(()=>{
    console.log("in setImmediate callback1111")
})
setTimeout(()=>{
    console.log("in settimeoutcallback")
},0)

setTimeout(()=>{
    console.log("in settimeoutcallback for 200ms")
},200)
setImmediate(()=>{
    console.log("in setImmediate 2222")
})
console.log("in console.log") //top lvel 


