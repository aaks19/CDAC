const express=require("express")
const router=express.Router();
const productcontroller=require("../controller/productController")
const {isValidUser,isAdminRole}=require("../middleware/checkvalidity")

//get all products
router.get("/getproducts",isValidUser,productcontroller.getAllProducts)

//show product form
router.get("/addproduct",isValidUser,productcontroller.showaddproductform)

//insert product into table
router.post("/insertproduct",isValidUser,productcontroller.insertProduct)

//display product in the form to update
router.get("/editproduct/:id",isValidUser,productcontroller.editProduct)

//update product in the table,
router.post("/updateproduct",isValidUser,productcontroller.updateProduct)


//delete product
router.get("/deleteproduct/:pid",isValidUser,productcontroller.deleteProduct)
module.exports=router