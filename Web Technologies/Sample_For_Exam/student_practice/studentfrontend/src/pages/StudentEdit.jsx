import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import StudentService from '../services/StudentService';

export default function StudentEdit() {

    const navigate = useNavigate();
    const location = useLocation();
    const [formdetails, setformdetails] = useState({sid:"",sname:"",marks:"",admissiondate:""})

    useEffect(()=>{
        setformdetails(location.state.studdata)
    },[])

    const updateStudent=(event)=>{
        event.preventDefault()
        if(formdetails.sid===""||formdetails.sname===""||formdetails.marks===""||formdetails.admissiondate===""){
            alert("can't be empty");
        }else{
            StudentService.updateStudent(formdetails)
            .then((result)=>{
                console.log("Student added "+result);
                navigate("/table")
            })
            .catch((err)=>{
                console.log("error occured "+err);
            })            
        }
    }

    const handleChange=(event)=>{
        var {name, value}=event.target;
        setformdetails({...formdetails,[name]:value})
    }
  return (
    <div>
      <form name='myform' onSubmit={updateStudent}>
        <div className="form-group">
            <label htmlFor="sid">Student ID</label>
            <input type="text" name="sid" id="sid" className='form-control' value={formdetails.sid} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="sname">Student Name</label>
            <input type="text" name="sname" id="sname" className='form-control' value={formdetails.sname} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="marks">Student marks</label>
            <input type="text" name="marks" id="marks" className='form-control' value={formdetails.marks} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="admissiondate">Admission date</label>
            <input type="date" name="admissiondate" id="admissiondate" className='form-control' value={formdetails.admissiondate} onChange={handleChange} />
        </div>


        <button type="submit" name='add' id='add' value="add">Add Student</button>
      </form>
    </div>
  )
}
