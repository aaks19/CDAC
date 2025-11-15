const fs=require('fs');

//check whether file exists
fs.exists("mydata.txt",(flag)=>{
    if(!flag){
        console.log("file does not exists")
    }else{
        //check status of the file
        fs.stat("mydata.txt",(err,status)=>{
            console.log(status)
        })
    }

})
fs.exists("mydata.txt",(flag)=>{
    if(!flag){
        console.log("file does not exists")
    }else{
        //open file in read mode
        fs.open("mydata.txt",(err,fd)=>{
            if(err){
                console.log(err);
            }else{
              var buff=Buffer.alloc(1024);
              buff.write("Hello");
              console.log("Buff:" +buff.toString())
   //read first 10 bytes from file and store in the buffer
   //fs.read(filedescriptor,buffer,buffer start positin, number of bytes read,file read positin, callback function)) 
             fs.read(fd,buff,0,10,null,function(err,bytesread,buffer){
                if(err){
                    console.log(err)
                }else{
                   console.log(bytesread);
                  console.log(buffer.toString());
                  fs.close(fd,(err)=>{
                    if(err){
                        console.log(err)
                    }else{
                    console.log("file closed")
                    }

                  })
                }
              })
            }

        })
    }
})//end of exists