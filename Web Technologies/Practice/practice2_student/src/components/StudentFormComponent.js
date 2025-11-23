import React, { useState } from 'react'

export default function StudentFormComponent(props) {
    const [formDetail, setFormDetail] = useState({sname:""})

    const handleChange=(event)=>{
        setFormDetail({...formDetail,sname:event.target.value})
    }

    const addStudent=(snm)=>{
        if(formDetail.sname.trim().length === 0){
            alert("student name can't be empty")
        }else{
            props.insertStudent(formDetail.sname)
            setFormDetail({...formDetail,sname:""})
        }
    }

    const removeStudent=(snm)=>{
        if(formDetail.sname.trim().length === 0){
            alert("student name can't be empty")
        }else{
            props.removeStudent(formDetail.sname)
            setFormDetail({...formDetail,sname:""})
        }
    }

    const updateStudent=()=>{
         if(formDetail.sname.trim().length === 0){
            alert("student name can't be empty")
        }else{
            var newStudent = prompt("Enter new student : ")
            props.updateStudent(formDetail.sname,newStudent)
            setFormDetail({...formDetail, sname:""})
        }
    }
  return (
    <>
    <form>
        <div className="form-group">
            <label>Student name:</label>
            <input type="text" className="form-control" value={formDetail.sname} onChange={handleChange} />
        </div>
        <button type="button" onClick={addStudent}>Add Student</button>
        <button type="button" onClick={removeStudent}>Remove Student</button>
        <button type="button" onClick={updateStudent}>Update Student</button>


    </form>
    </>
  )
}
