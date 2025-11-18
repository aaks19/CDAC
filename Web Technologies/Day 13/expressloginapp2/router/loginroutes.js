const express=require("express")
const router=express.Router();
const lController=require("../controller/loginController")


//login url
router.get("/",lController.getLoginForm)
router.post("/validateuser",lController.validateuserdetails)

//registeration url
router.get("/register",lController.getregistrationForm)
router.post("/registeruser",lController.registeruser)

//forgot password url
router.get("/forgotPassword",lController.getForgotPassword)
router.post("/updatepassword",lController.updateNewPassword)

router.get("/logout",lController.logoutuser)
 
module.exports=router;