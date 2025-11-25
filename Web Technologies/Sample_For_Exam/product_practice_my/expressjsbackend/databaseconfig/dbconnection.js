const mysql = require('mysql2')

const db = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:"aks123",
    database:"expressdb"
})

db.connect((err)=>{
    if(err){
        console.log(err);
    }else{
        console.log("Connection success")
    }
})

module.exports = db;