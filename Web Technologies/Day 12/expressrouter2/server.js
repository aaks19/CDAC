//import all libraries
const express=require("express");
const app=express();
const userrouter=require("./router/userRouter")
const prodrouter=require("./router/productRouter")
const bodyParser=require("body-parser")
//cofigure express application
//write middleware
bodyParser.urlencoded({extended:false})

//use router in separate file
app.use("/users",userrouter)
app.use("/products",prodrouter)
//start server
app.listen(3333,()=>{
    console.log("running at port 3333")
})