const mysql = require("mysql2")

//create database connection
const db=mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'aks123',
    database:'expressdb'
})

db.connect((err)=>{
    if(err){
        console.log(err)
    }else{
        console.log("connection done");
    }
})

module.exports = db;