const express=require("express")
const router=express.Router()


router.get("/getuser",(req,resp)=>{
     resp.send("<h2>in getuser userRouter</h2>")
})

router.get("/displayuser",(req,resp)=>{
     resp.send("<h2>in displayuser userRouter</h2>")
})

module.exports=router