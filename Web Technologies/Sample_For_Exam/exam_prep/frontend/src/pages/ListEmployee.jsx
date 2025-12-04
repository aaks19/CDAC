import React from 'react'
import axios from 'axios'
// import UpdateEmployee from "./UpdateEmployee"
import { useState } from 'react'
import { useEffect } from 'react';

export default function ListEmployee() {
    const [employees, setEmployees] = useState([]);
    // const [editing, setEditing] = useState(null);

    const getEmployee=()=>{
        axios.get("http://localhost:5000/employee")
        .then(res=> setEmployees(res.data))
        .catch(err=>alert("error : "+ err));
    };
       useEffect(() => {
  getEmployee();  // <-- q empty = direct get all employees
}, []);
   
  return (
    <div>
        <h2>Employee list</h2>

        <button onClick={getEmployee}>Get Employee</button>
 


        {employees.map(emp=>(
            <div key={emp.id}>
                {emp.name} | {emp.position} | {emp.salary}
                &nbsp;
                {/* <button onClick={()=>setEditing(emp)}>Edit</button> */}
            </div>
        ))}

  

        {/* {editing&& (
            <UpdateEmployee selected={editing} onUpdate={()=>{
                setEditing(null);
                getEmployee();
            }} />
        )} */}
      
    </div>
     
  )
}
