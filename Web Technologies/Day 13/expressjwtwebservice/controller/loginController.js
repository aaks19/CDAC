const connection=require("../databaseconfig/dbconnection")
const {generateToken}=require("../middleware/jwttokendetails")

exports.validateuser=(req,resp)=>{
    const {email,password}=req.body
    connection.query("select * from myusers where email=?",[email],(err,result)=>{
        if(err){
            resp.status(500).json({message:"Invalid credentials"})
        }else{
            const user=result[0];
            if(user.password===password){
                //pass only required information
            //const token=generateToken({id:user.uid,uname:user.uname,email:user.email})
            const token=generateToken(user)
               //resp.json({token:token})
               resp.json({token})
            }else{
                resp.status(500).json({message:"invalid credential"})
            }
        }
    })
}