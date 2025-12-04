const connection = require("../databaseconnection/DbConfig")

exports.getAllBooks=(req,resp)=>{
    connection.query("select * from library",(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:results})
        }
    })
}

exports.addBook=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("insert into library values (?,?,?,?,?)",[id,bname,bauthor,price,year],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:"book added"})
        }
    })
}

exports.updateBooks=(req,resp)=>{
    const {id, bname, bauthor, price, year} = req.body;
    connection.query("update library set bname=?, bauthor=?, price=? , year=? where id=?",[bname,bauthor,price,year,id],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:results})
        }
    })
}

exports.deleteBook=(req,resp)=>{
    connection.query("delete from library where id=?",[req.params.id],(err,results,fields)=>{
        if(err){
            console.log("Error in fetching"+err);
        }else{
            console.log(results);
            resp.json({data:"deleted success"})
        }
    })
}