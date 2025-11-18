const express = require('express')
const path = require("path")
const session = require("express-session")
const bodyParser = require("body-parser")
const loginroute = require("./router/loginroute")
const { log } = require('console')

const app = express()

// set view engine as ejs
app.set("view engine","ejs")
app.set("views",path.join(__dirname,"views"))

//use server static files from public directory
app.use(express.static("public"))
app.use(bodyParser.urlencoded({extended: false}))

//initialize session object
app.use(session({
    secret:"mysecretkey",
    resave: false,
    saveUninitialized: false
}))

app.use("/login",loginroute)


app.listen(3333,()=>{
    console.log("server is running on port 3333");
    
})


