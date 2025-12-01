const connection = require("../databaseconfig/dbconfig")

exports.getAllRestaurants=(req,resp)=>{
    connection.query("select * from restaurants",(err,result,fields)=>{
        if(err){
            console.log(err);
        }else{
            console.log(result);
            resp.json({data:result})
        }
    })
}

exports.insertRestaurant=(req,resp)=>{
    const {id,rname,phone,rlocation} = req.body;
    connection.query("insert into restaurants values(?,?,?,?)",[id,rname,phone,rlocation],(err,result,field)=>{
        if(err){
            console.log(err);
        }else{
            console.log("inserted");
            resp.json({data:"restaurant added"})
        }
    })
}

exports.updateRestaurant=(req,resp)=>{
    const {id,rname,phone,rlocation} = req.body;
    connection.query("update restaurants set rname=?, phone=?, rlocation=? where id=?",[rname,phone,rlocation,id],(err,result,field)=>{
        if(err){
            console.log(err);
        }else{
            console.log("updated");
            resp.json({data:result})
        }
    })
}


exports.deleteRestaurant=(req,resp)=>{
    connection.query("delete from restaurants where id=?",[req.params.id],(err,result)=>{
        if(err){
            console.log(err)
        }else{
            resp.json({data:"deleted"})
        }
    })
}