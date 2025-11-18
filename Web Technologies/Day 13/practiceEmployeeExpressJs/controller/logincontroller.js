const connection = require("../databaseconfig/dbconnection")
const bcrypt = require('bcrypt')

//display login form
exports.getLoginForm=(req,resp)=>{
    resp.render("login")
}

//validate the user
exports.validateuserdetails=(req,resp)=>{
    //const email=req.body.email;
    //const password=req.body.password;
    //const btn=req.body.btn;
    const {email,password}=req.body;

    connection.query("select * from employee where email=?",[email],async (err,result)=>{
        if(err){
            console.log("error")
            console.log(err);
        }else{
            if(result.length===0){
                return resp.send("<h1>Invalid user</h1>")
            }else{
                var user=result[0];
                if(user.password===password){
                    req.session.user=user;
                    return resp.send("<h1>valid user</h1>")
                }else{
                    return resp.send("<h1>Invalid user</h1>")
                }
            }
        }
    })
}


//register employee
exports.getresigterationform=(req,resp)=>{
    resp.render('register')
}

exports.registeremployee = async(req,resp)=>{
    const {uname,email,password} = req.body

    connection.query("insert into employee(uname,email,password) value(?,?,?)",[uname,email,password],async (err)=>{
        if(err){
            console.log(err)
        }else{
            resp.redirect("/login")
        }
    })
}