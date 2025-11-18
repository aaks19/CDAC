const express=require("express")
const router=express.Router();
const lController=require("../controller/loginController")

router.get("/",lController.getLoginForm)
router.post("/validateuser",lController.validateuserdetails)


module.exports=router;