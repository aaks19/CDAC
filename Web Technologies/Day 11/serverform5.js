const http=require('http')
const fs=require('fs')
const url=require('url')
const m1=require("./module1")

const server=http.createServer((req,resp)=>{
    console.log(req.url+"-----"+req.method)
    q={pathname:url,query:{num1:34,num2:45,btn:"add"}}
    var q=url.parse(req.url,true)
    console.log(q)
    switch(q.pathname){
        case "/form":
            var rs=fs.createReadStream("public/calcform.html")
            rs.pipe(resp);
            break;
        case "/submit-form":
            resp.write(q.query.num1+"------"+q.query.num2) 
            var num1=parseInt(q.query.num1)
            var num2=parseInt(q.query.num2)
            if(q.query.btn==="add"){
              var ans=m1.addition(num1,num2)
              resp.end("Addition : "+ans) 
            }else if(btn==="fact"){
                //o/p factorial
            }
            break;
        case "/aboutus":
            resp.write("<h1>in aboutus page</h1>")
            break;
    }
})

server.listen(3232,()=>{
    console.log("server is running at port 3232");
})