const connection = require("../databaseconfig/dbconnection")

exports.getAllProducts=(req,resp)=>{
    connection.query("select * from myproducts",(err,result,field)=>{
        if(err){
            console.log("error Occured");
        }
        else{
            console.log(result);
            resp.json({data:result});
        }
    })    
}

exports.getById=(req,resp)=>{
    connection.query("select * from myproduct where pid = ?",[req.params.id],(err,result,field)=>{
        if(err){
            console.log("Error occured");
        }else{
            console.log(result)
            resp.json({data:result[0]})
        }
    })
}

exports.insertProduct=(req,resp)=>{
    const {pid,pname,qty,price,mfgdate} = req.body;
    connection.query("insert into myproducts values(?,?,?,?,?)",[pid,pname,qty,price,mfgdate],(err,result,field)=>{
       if(err){
            console.log("Error occured");
        }else{
            console.log("data added :"+result)
            resp.json({data:"data added"})
        } 
    })
}


exports.updateProduct=(req,resp)=>{
    const {pid,pname,qty,price,mfgdate} = req.body;
    connection.query("update myproducts set pname=? , qty=? , price=? , mfgdate=? where pid=?",[pname,qty,price,mfgdate,pid],(err,result,field)=>{
        if(err){
            console.log("error occured");
        }else{
            resp.json({data:result})
        }
    })
}


exports.deleteProduct=(req,resp)=>{
    connection.query("delete from myproducts where pid=?",[req.params.id],(err,result,field)=>{
        if(err){
            console.log("Error in deleting");
        }else{
            resp.json({message:"Deleted successfully"})
        }
    })
}