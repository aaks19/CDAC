import React,{useState} from 'react'

export default function CourseFormComponent(props) {
    const [formdetails,setformdetails]=useState({cname:""})

    const handlechange=(event)=>{
        setformdetails({...formdetails,cname:event.target.value})
    }

    const addcourse=()=>{
        if(formdetails.cname.trim().length===0){
            alert("coursename cannot be empty")
        }else{
            props.insertCourse(formdetails.cname)
            setformdetails({...formdetails,cname:""})
        }
    }

    const deleteCourse=()=>{
        if(formdetails.cname==="" || formdetails.cname.trim().length===0){
            alert("no course to delete")
        }else{
            props.deleteCourse(formdetails.cname);
            setformdetails({...formdetails,cname:""})
        }
    }

    const updateCourse=()=>{
        if(formdetails.cname==="" || formdetails.cname.trim().length===0){
            alert("no course to delete")
        }else{
            var newname = prompt("Enter new course name:")
            props.updateCourse(formdetails.cname,newname)
            setformdetails({...formdetails,cname:""})
        }
    }

    

    return (
        <div>
            <form>
                <div className="form-group">
                    <label>Course Name</label>
                    <input 
                        type="text" 
                        className="form-control"
                        value={formdetails.cname}
                        onChange={handlechange}
                    />
                </div>

                <button type="button" className="btn btn-primary" onClick={addcourse}>Add</button>
                <button type="button" className="btn btn-danger mx-2" onClick={deleteCourse}>Delete</button>
                <button type="button" className="btn btn-warning" onClick={updateCourse}>Update</button>
            </form>
        </div>
    )
}
