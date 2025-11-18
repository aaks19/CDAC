const connection=require("../databaseconfig/dbconnection")

exports.getAllProducts=(req,resp)=>{
    //if the token is valid, ideally we check user.role
    if(req.user.uname==='user1'){
            connection.query("select * from myproducts",(err,result,field)=>{
                if(err){
                    resp.status(404).json({message:"error occured" +JSON.stringify(err)});
                }else{
                    console.log(result);
                    resp.json({data:result})
                }
            })
    }else{
        resp.status(500).json({message:"invalid token"})
    }
}


//insert product in the table
exports.insertProduct=(req,resp)=>{
   const {pid,pname,qty,price,mfgdate}=req.body
   connection.query("insert into myproducts values(?,?,?,?,?)",[pid,pname,qty,price,mfgdate],(err,result,field)=>{
     if(err){
        resp.status(500).json({message:"data not inserted" +JSON.stringify(err)});
      }else{
        console.log("data added"+result)
        resp.json({data:"data added"})
      }
   })
}

//display data in the form for updation
exports.getById=(req,resp)=>{
    if(req.user.uname==="user1"){
    connection.query("select * from myproducts where pid=?",[req.params.id],(err,result,fields)=>{
        if(err){
            resp.status(500).json({message:"Product not found"})
        }else{
           console.log(result);

           //result[0].mfgdate=result[0].mfgdate.toISOString().split('T')[0];
            resp.json({data:result[0]})
        }

    })
}else{
    resp.status(500).json({"message":"invalid token"})
}
}

//update product in the table
exports.updateProduct=(req,resp)=>{
    if(req.user.uname==="user1"){
    const {pid,pname,qty,price,mfgdate}=req.body
    console.log("mfgdate"+mfgdate)
    connection.query("update myproducts set pname=?,qty=?,price=?,mfgdate=? where pid=?",[pname,qty,price,mfgdate,pid],(err,result,fields)=>{
        if(err){
            console.log(err)
            resp.status(500).json({message:"Product not updated"})
        }else{
            resp.json({data:result})
        }
    } )}else{
        resp.status(500).json({message:"invalid token"})
    }
}

//delete product from table
exports.deleteProduct=(req,resp)=>{
    if(req.user.uname==="user1"){
    connection.query("delete from myproducts where pid=?",[req.params.id],(err,result)=>{
        if(err){
            resp.status(500).json({message:JSON.stringify(err)})
        }else{
            resp.json({message:"deleted successfully"})
        }
    })
   }else{
    resp.status(500).json({message:"invalid token"})
   }
}

