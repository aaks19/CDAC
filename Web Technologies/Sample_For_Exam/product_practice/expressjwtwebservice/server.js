const express=require("express")
const app=express()
const bodyParser=require("body-parser")
// const loginroutes=require("./router/loginroutes")
const productroutes=require("./router/productroutes")

const cors = require('cors');



//configure application
  
//app.use(bodyParser.urlencoded({extended:false}))
app.use(cors());
app.use(bodyParser.json())
//handle routes
// app.use("/login",loginroutes)
app.use("/product",productroutes)

app.listen(3333,()=>{
    console.log("running at port 3333")
})

