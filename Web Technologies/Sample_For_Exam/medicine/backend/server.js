const express = require('express')
const mysql = require('mysql2')
const cors = require('cors')

const app = express();
app.use(cors())
app.use(express.json())

const db = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'aks123',
    database:'expressdb'
})

db.connect(()=>{
    console.log("Connected to database");
})

app.get("/medicine/medicines",(req,resp)=>{
    db.query("select * from medicines",(err,result)=>{
        if(err){
            console.log(err);
        }else{
            resp.json(result);
        }
    })
})


app.post("/medicine/medicines",(req,resp)=>{
    const {id,name,type,qty,price} = req.body;
    db.query("insert into medicines values(?,?,?,?,?)",[id,name,type,qty,price],(err,result)=>{
        if(err){
            console.log(err);
        }else{
            resp.json({data:result});
        }
    })
})


app.put("/medicine/medicines/:id",(req,resp)=>{
    const {id,name,type,qty,price} = req.body;
    db.query("update medicines set name=?,type=?,qty=?,price=? where id=?",[name,type,qty,price,id],(err,result)=>{
        if(err){
            console.log(err);
        }else{
            resp.json({data:"updated"});
        }
    })
})

app.delete("/medicine/medicines/:id",(req,resp)=>{
    db.query("delete from medicines where id=?",[req.params.id],(err,result)=>{
        if(err){
            console.log(err);
        }else{
            resp.json({data:"deleted"});
        }
    })
})

app.listen(3333,()=>{
    console.log("Server sunning on port 3333");
    
})