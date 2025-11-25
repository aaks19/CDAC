const express=require("express")
const router=express.Router();
const productcontroller=require("../controller/productController")
// const {authenticateJWT}=require("../middleware/jwttokendetails")

//read all products
router.get("/products",productcontroller.getAllProducts)

router.get("/products/:id",productcontroller.getById)

router.post("/products/:id",productcontroller.insertProduct)

router.put("/products/:id",productcontroller.updateProduct)

router.delete("/products/:id",productcontroller.deleteProduct)

module.exports=router;