const http=require('http')

//create server

const server=http.createServer((req,resp)=>{
    console.log(req.url+"-----"+req.method)
    resp.write("<h1>hello</h1>")
    resp.write("<h2>Welcome to nodejs programming</h2>")
    resp.end("<h3>This is last line</h3>");

})

server.listen(3131,()=>{
    console.log("server is running on port 3131")
})