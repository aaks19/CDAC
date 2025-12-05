const express  = require('express')
const mysql = require('mysql2')
const cors = require('cors')

const app = express();

app.use(cors())
app.use(express.json())

//db connection
const db = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'aks123',
    database:'expressdb'
})

db.connect((err)=>{
    if(err){
        console.log(err);
    }else{
        console.log("DB connected...");
    }
})

app.get("/food/foods",(req,resp)=>{
    db.query("select * from foodorder",(err,results)=>{
        if(err){
            console.log("error in getting data "+err);
        }else{
            resp.json(results);
        }
    })
})

app.post("/food/foods",(req,resp)=>{
    const {fid,fname,price,qty}=req.body;
    db.query("insert into foodorder values(?,?,?,?)",[fid,fname,price,qty],(err,results)=>{
        if(err){
            console.log("Error in inserting "+err);
        }else{
            resp.json({data:results})
        }
    })
})

app.put("/food/foods/:id",(req,resp)=>{
    const {fid,fname,price,qty} = req.body;
    db.query("update foodorder set fname=?,price=?,qty=? where fid=?",[fname,price,qty,fid],(err,results)=>{
        if(err){
            console.log("Error in updating "+err);
        }else{
            resp.json({data:results})
        }
    })
})

app.delete("/food/foods/:id",(req,resp)=>{
    db.query("delete from foodorder where fid=?",[req.params.id],(err,results)=>{
        if(err){
            console.log("Error in deleting "+err);
        }else{
            resp.json({data:"deleted"})
        }
    })
})


app.listen(3333,()=>{
    console.log("started at port 3333");
})

