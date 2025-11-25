const express = require('express')
const router = express.Router();

const productController = require("../controller/productcontroller")

router.get("/products",productController.getAllProducts)

router.get("/products/:id",productController.getById)

router.post("/products/:id",productController.insertProduct)

router.put("/products/:id",productController.updateProduct)

router.delete("/products/:id",productController.deleteProduct)

module.exports = router;