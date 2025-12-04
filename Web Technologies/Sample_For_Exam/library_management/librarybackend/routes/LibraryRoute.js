const express = require('express')
const router = express.Router()

const librarycontroller = require('../controller/LibraryController')

router.get("/books",librarycontroller.getAllBooks)

router.post("/books",librarycontroller.addBook)

router.put("/books/:id",librarycontroller.updateBooks)

router.delete("/books/:id",librarycontroller.deleteBook)


module.exports = router;