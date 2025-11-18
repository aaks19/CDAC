const connection=require("../databaseconfig/dbconnection")

exports.getAllProducts=(req,resp)=>{
   connection.query("select * from myproducts",(err,result,field)=>{
      if(err){
        resp.status(404).send("error occured" +JSON.stringify(err));
      }else{
        console.log(result);
        resp.render("showproduct",{productdata:result})
      }
   })
}

//show add new form
exports.showaddproductform=(req,resp)=>{
    resp.render("addproduct")
}

//insert product in the table
exports.insertProduct=(req,resp)=>{
   const {pid,pname,qty,price,mfgdate}=req.body
   connection.query("insert into myproducts values(?,?,?,?,?)",[pid,pname,qty,price,mfgdate],(err,result,field)=>{
     if(err){
        resp.status(500).send("data not inserted" +JSON.stringify(err));
      }else{
        console.log("data added"+result)
        resp.redirect("/product/getproducts")
      }
   })
}

//display data in the form for updation
exports.editProduct=(req,resp)=>{
    connection.query("select * from myproducts where pid=?",[req.params.id],(err,result,fields)=>{
        if(err){
            resp.status(500).send("Product not found")
        }else{
            result[0].mfgdate=result[0].mfgdate.toISOString().split('T')[0];
            resp.render("editproduct",{product:result[0]})
        }

    })
}

//update product in the table
exports.updateProduct=(req,resp)=>{
    const {pid,pname,qty,price,mfgdate}=req.body
    console.log("mfgdate"+mfgdate)
    connection.query("update myproducts set pname=?,qty=?,price=?,mfgdate=? where pid=?",[pname,qty,price,mfgdate,pid],(err,result,fields)=>{
        if(err){
            console.log(err)
            resp.status(500).send("Product not updated")
        }else{
            resp.redirect("/product/getproducts")
        }
    } )
}

//delete product from table
exports.deleteProduct=(req,resp)=>{
    connection.query("delete from myproducts where pid=?",[req.params.pid],(err,result)=>{
        if(err){
            resp.status(500).send(JSON.stringify(err))
        }else{
            resp.redirect("/product/getProducts")
        }
    })
}

