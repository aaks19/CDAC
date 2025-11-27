const express= require('express')
const app = express()
const bodyParser = require('body-parser')
const cors = require('cors')

const studentRoutes = require("./routes/studentRoutes");

app.use(cors())
app.use(bodyParser.json())

app.use("/student",studentRoutes)

app.listen(3333,()=>{
    console.log("Server started on port 3333");
})