const mysql = require('mysql2');

const db = mysql.createConnection({
    host: "localhost",
    user: 'root',
    password:"aks123",
    database:"expressdb"
})

db.connect((err)=>{
    if(err){
        console.log("Connection failed");
    }
    console.log("connection established");
    
})

module.exports = db;
