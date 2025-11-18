const express=require("express");
const app=express();
const bodyparser=require("body-parser")
const m1=require("./module1")
bodyparser.urlencoded({extended:false})
//bodyparser.json()


app.use((req,resp,next)=>{
    console.log("in first middleware --->"+req.url)
    console.log("in first middleware --->"+req.method)
     next()
})

app.get("/form",(req,resp)=>{
    resp.sendFile("/public/form.html",{root:__dirname})

})

app.get("/submit_data",(req,resp)=>{
    if(req.query.btn==="add"){
     var n1=parseInt(req.query.num1)
     var n2=parseInt(req.query.num2)
     var ans=m1.addition(n1,n2);
    resp.send("<h1>in submit_data url"+req.query.num1+"+"+req.query.num2+"="+ans+"</h1>")
    }else{
        var n1=parseInt(req.query.num1)
        var ans=m1.factorial(n1);
    resp.send("<h1>in submit_data url"+req.query.num1+" factorial : "+ans+"</h1>")
    }
})

app.listen(3333,()=>{
    console.log("running at port 3333")
})