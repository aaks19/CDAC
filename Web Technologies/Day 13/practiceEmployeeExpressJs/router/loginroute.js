const express = require('express')
const router = express.Router()
const control = require('../controller/logincontroller')

//login url
router.get("/",control.getLoginForm)
router.post("/validateuser",control.validateuserdetails)


//register url
router.get("/register",control.getresigterationform)
router.post("/registeruser",control.registeremployee)

module.exports = router;