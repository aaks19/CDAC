const express=require("express")
//using express router object
const router=express.Router()


router.get("/getproduct",(req,resp)=>{
     resp.send("<h2>in getproduct productRouter</h2>")
})

router.get("/displayproduct",(req,resp)=>{
     resp.send("<h2>in displayproduct productRouter</h2>")
})

module.exports=router