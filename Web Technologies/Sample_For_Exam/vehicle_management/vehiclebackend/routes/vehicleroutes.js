const express = require('express')
const router = express.Router();

const vehicleController = require("../controller/vehiclecontroler")

router.get("/vehicles",vehicleController.getAllVehicles);

router.post("/vehicles",vehicleController.insertVehicle);

router.put("/vehicles/:id",vehicleController.updateVehicle)

module.exports = router