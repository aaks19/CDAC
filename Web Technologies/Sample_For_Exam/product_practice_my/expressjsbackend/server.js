const express = require('express')
const app = express();

const bodyParser = require('body-parser')
const cors = require('cors')

const productRoutes = require("./router/productroutes")

app.use(cors())
app.use(bodyParser.json())

app.use("/product",productRoutes)

app.listen(3333,()=>{
    console.log("server live at port 3333");
})