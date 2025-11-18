//import all libraries
const express=require("express")
const app=express();
//configure express
//add all middlewares

app.use((req,resp,next)=>{
    console.log("in first middleware---->"+req.url);
    next()

})

//add code for url specific functions

app.get("/home",(req,resp)=>{
     resp.sendFile("/public/hello.html",{root:__dirname})
})
app.get("/welcome",(req,resp)=>{
    resp.sendFile("/public/welcome.html",{root:__dirname})
})
//start the server
app.listen(3333,()=>{
    console.log("running on port 3333")
})