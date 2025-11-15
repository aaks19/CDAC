const readline=require('readline')
const fs=require('fs')

var rl=readline.createInterface({
    input:fs.createReadStream("mydata.txt")
})
 var cnt=0
rl.on("line",(str)=>{
    cnt++;
    console.log(cnt+": "+str)
    //add logic for word count
})

rl.on('close',()=>{
    console.log(" total lines "+cnt);
    //display total number of words
})
