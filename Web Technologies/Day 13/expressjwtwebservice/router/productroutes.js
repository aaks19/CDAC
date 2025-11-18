const express=require("express")
const router=express.Router();
const productcontroller=require("../controller/productController")
const {authenticateJWT}=require("../middleware/jwttokendetails")

//read all products
router.get("/products",authenticateJWT,productcontroller.getAllProducts)

router.get("/products/:id",authenticateJWT,productcontroller.getById)

router.post("/products/:id",authenticateJWT,productcontroller.insertProduct)

router.put("/products/:id",authenticateJWT,productcontroller.updateProduct)

router.delete("/products/:id",authenticateJWT,productcontroller.deleteProduct)

module.exports=router;