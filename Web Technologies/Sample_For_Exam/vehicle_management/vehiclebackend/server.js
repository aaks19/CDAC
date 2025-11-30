const express = require('express')
const app = express();
const bodyParser = require('body-parser')
const cors = require('cors')

const vehicleRoute = require('./routes/vehicleroutes')

app.use(cors())
app.use(bodyParser.json())

app.use("/vehicle",vehicleRoute)

app.listen(3333,()=>{
    console.log("Server started on port 3333");
})