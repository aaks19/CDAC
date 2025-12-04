import React from 'react'
import { useState } from 'react'
import axios from 'axios'

export default function AddEmployee() {
    const [name , setName] = useState("")
    const [position , setPosition] = useState("")
    const [salary, setSalary] = useState("")

    const submit= async (e)=>{
        e.preventDefault();
        await axios.post("http://localhost:5000/employee",{name,position,salary});
        alert("Employee added!!!")
    }
  return (
    <div>
    <input type="text" placeholder='name' onChange={(e)=>setName(e.target.value)}/>
    <input type="text" placeholder='position' onChange={(e)=>setPosition(e.target.value)}/>    
    <input type="number" placeholder='salary' onChange={(e)=>setSalary(e.target.value)}/> 

    <button onClick={submit}>add employee</button> 
      
    </div>
  )
}
