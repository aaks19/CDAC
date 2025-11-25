const express=require("express");
const router=express.Router();
const lcontroller=require("../controller/loginController")


router.post("/loginuser",lcontroller.validateuser)
module.exports=router;