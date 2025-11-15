const http=require('http')

//create server

const server=http.createServer((req,resp)=>{
    console.log(req.url+"-----"+req.method)
    if(req.url==="/home" && req.method==="GET"){
        resp.write("<h1>In home page</h1>")
        resp.end("<h2>Welcome to nodejs programming</h2>")
    }else{
        resp.write("<h1>In about us page</h1>")
        resp.end("<h2>Welcome to nodejs programming</h2>")
    }
})

server.listen(3131,()=>{
    console.log("server is running on port 3131")
})