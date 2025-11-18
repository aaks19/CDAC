const express=require("express")
const path=require("path");
const session=require("express-session")
const bodyParser=require("body-parser")
const loginroutes=require("./router/loginroutes")
const app=express()



//configure application
app.set("view engine","ejs")
app.set("views",path.join(__dirname,"views"))  

app.use(express.static("public"))
app.use(bodyParser.urlencoded({extended:false}))


//initialize session object
app.use(
  session({
    secret: "mysecretkey",
    resave: false,
    saveUninitialized: false
  })
);

//handle routes
app.use("/login",loginroutes)

app.listen(3333,()=>{
    console.log("running at port 3333")
})
