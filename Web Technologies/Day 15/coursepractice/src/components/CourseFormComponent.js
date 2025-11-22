import React,{useState} from 'react'

export default function CourseFormComponent(props) {
  
    const [formdetails,setformdetails]=useState({cname:""})
    // gets called for onChange event
    const handlechange=(event)=>       {
        console.log("in handlechange")
        setformdetails({...formdetails,cname:event.target.value})}

        const addcourse=()=>{
            if(formdetails.cname==="" || formdetails.cname.trim())
        }
        
  return (
    <div>
        <form>
            <div className="form-group">
                <label htmlFor="course">Course Name</label>
                <input type="text" className="form-control" name="cname" id="course"
                value={formdetails.cname}
                onChange={handlechange}/>
            </div>
              <button type="button" id="add" name="add" className="btn btn-primary" >Add course</button> &nbsp;&nbsp;&nbsp;

              <button type="button" id="del" name="del" className="btn btn-primary" >Delete course</button>&nbsp;&nbsp;&nbsp;


              <button type="button" id="update" name="update" className="btn btn-primary">update course</button>&nbsp;&nbsp;&nbsp;
        </form>
    </div>
  )
}