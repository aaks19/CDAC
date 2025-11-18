//import all required libraries
const express=require("express")
//create a object of express
const app=express()
//add all common middleware function
app.use(function(req,resp,next){
   console.log("in first middleware function")
   console.log(req.url+"-----"+req.method)
   next()
})

app.use(function(req,resp,next){
   console.log("in second middleware function")
   console.log("these middlewares gets executed for all rquests")
     next()
})
 

app.get("/hello",(req,resp)=>{
    resp.send("<h1>Hello world!!!</h1>")
})

app.get("/welcome",(req,resp)=>{
    var str="<h1>Welcome to expressjs code</h1>"
    str=str+"<h2>express code is cleaner and easier to write than nodejs</h2>"
    resp.send(str)
    
})


app.listen(3333,()=>{
    console.log("running at port number 3333")
})

