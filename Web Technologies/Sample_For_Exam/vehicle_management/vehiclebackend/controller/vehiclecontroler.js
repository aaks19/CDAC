const connection = require("../databasecontroller/dbconfig")

exports.getAllVehicles=(req,resp)=>{
    connection.query("select * from myvehicle",(err,result,field)=>{
        if(err){
            console.log("Error in retrieving data");
        }else{
            console.log(result);
            resp.json({data:result})
        }
    })
}

exports.insertVehicle=(req,resp)=>{
    const {id,vname,price,mfgdate} = req.body;
    connection.query("insert into myvehicle values(?,?,?,?)",[id,vname,price,mfgdate],(err,result,field)=>{
        if(err){
            console.log("error occured in inserting");
        }else{
            console.log("Data added");
            resp.json({data:"data added"})
        }
    })
}

exports.updateVehicle=(req,resp)=>{
    const {id,vname,price,mfgdate} = req.body;
    connection.query("update myvehicle set vname=?, price=?, mfgdate=? where id=?",[vname,price,mfgdate,id],(err,result,field)=>{
        if(err){
            console.log("error in updating");
        }else{
            console.log("Updated successfully");
            resp.json({data:"Updated"})
        }
    })
}

exports.deleteVehicle=(req,resp)=>{
    connection.query("delete from myvehicle where id=?",[req.params.id],(err,result)=>{
        if(err){
            console.log("Error occured "+err);
        }else{
            resp.json({message:"deleted"})
        }
    })
}