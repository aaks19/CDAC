const express = require('express')
const router = express.Router()

const restaurantcontroller = require('../controller/restaurantcontroller')

router.get("/restaurants",restaurantcontroller.getAllRestaurants)

router.post('/restaurants',restaurantcontroller.insertRestaurant)

router.put("/restaurants/:id",restaurantcontroller.updateRestaurant)

router.delete('/restaurants/:id',restaurantcontroller.deleteRestaurantById)

module.exports = router;