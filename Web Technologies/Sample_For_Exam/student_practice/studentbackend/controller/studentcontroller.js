const connection = require("../dbconfig/databasecongig")


exports.getAllStudents=(req,resp)=>{
    connection.query("select * from students",(err,result,field)=>{
        if(err){
            console.log("error Occured");
        }
        else{
            console.log(result);
            resp.json({data:result});
        }
    })    
}

exports.insertStudent=(req,resp)=>{
    const {sid,sname,marks,admissiondate} = req.body;
    connection.query("insert into students values(?,?,?,?)",[sid,sname,marks,admissiondate],(err,result,field)=>{
         if(err){
            console.log("error Occured");
        }
        else{
            console.log("Data added");
            resp.json({data:"data added"})
        }    
    })
}

exports.updateStudent=(req,resp)=>{
    const {sid, sname, marks, admissiondate} = req.body;
    connection.query("update students set sname=? , marks=? , admissiondate=? where sid=?",[sname,marks,admissiondate,sid],(err,result,field)=>{
        if(err){
            console.log("error occured");
        }else{
            console.log("data updated");
            resp.json({data:result})
        }
    })
}

exports.deleteStudent=(req,resp)=>{
    connection.query("delete from students where sid=?",[req.params.id],(err,result)=>{
        if(err){
            console.log("error");
        }else{
            resp.json({message:"deleted success"})
        }
    })
}




