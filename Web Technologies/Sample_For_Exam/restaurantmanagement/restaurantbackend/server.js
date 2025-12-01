const express = require('express')
const app = express()
const bodyParser = require('body-parser')
const cors = require('cors')

const restaurantroute = require('./routes/restaurantroute')

app.use(cors())
app.use(bodyParser.json())


app.use("/restaurant",restaurantroute)

app.listen(3333,()=>{
    console.log("server running on port 3333");
})