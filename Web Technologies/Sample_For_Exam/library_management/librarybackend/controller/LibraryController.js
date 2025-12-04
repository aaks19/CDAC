const connection = require("../databaseconnection/DbConfig")

exports.getAllBooks=(req,resp)=>{
    connection.query("select * from library",(err,result,field)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(result);
            resp.json({data:result})
        }
    })
}

exports.addBook=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("insert into library values (?,?,?,?,?)",[id,bname,bauthor,price,year],(err,result,field)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(result);
            resp.json({data:"book added"})
        }
    })
}

exports.updateBooks=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("update library set bname=?, bauthor=?, price=? , year=? where id=?",[bname,bauthor,price,year,id],(err,result,field)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(result);
            resp.json({data:result})
        }
    })
}

exports.deleteBook=(req,resp)=>{
    connection.query("delete from library where id=?",[req.params.id],(err,result,field)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(result);
            resp.json({data:"deleted success"})
        }
    })
}