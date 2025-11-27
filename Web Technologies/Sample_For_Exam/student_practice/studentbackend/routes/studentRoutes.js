const express = require('express')
const router = express.Router();

const studentController = require("../controller/studentcontroller");

router.get("/students",studentController.getAllStudents)

router.post("/students",studentController.insertStudent)

router.put("/students/:id",studentController.updateStudent)

router.delete("/students/:id",studentController.deleteStudent)

module.exports = router;
