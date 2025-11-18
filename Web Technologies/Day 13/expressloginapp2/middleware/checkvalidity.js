const express=require("express")

exports.isValidUser=(req,resp,next)=>{
    console.log("in isValid user middleware")
    if(!req.session.user){
        resp.redirect("/login")
    }else{
        next()
    }

} 