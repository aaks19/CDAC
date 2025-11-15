const http=require('http')
const fs=require("fs")

//function processdata(req,resp){}
//const server=http.createServer(processdata)
const server=http.createServer((req,resp)=>{
    console.log(req.url+"----"+req.method);
    resp.writeHead(200,{"content-type":"text/html"})
    switch(req.url){
        case "/home":
            const rs=fs.createReadStream("public/hello.html");
            rs.pipe(resp);
            break;
        case "/aboutus":
            resp.write("<h1>In aboutus page</h1>")
            resp.end();
            break;
    }

})

//server.listen(3232);

server.listen(3232,()=>{
  console.log("server started at port 3232")
})