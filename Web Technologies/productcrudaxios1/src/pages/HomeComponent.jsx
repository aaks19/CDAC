import React,{useState} from 'react'
import {useNavigate} from 'react-router-dom'
import LoginService from '../service/LoginService'
export default function HomeComponent() {
  //to get data from the form
  const [formdetails,setformdetails]=useState({email:"",password:""})
  const [errors,seterrors]=useState({})
  const navigate=useNavigate()
  //function to handle onChange event, to change value of appropriate property
  const handlechange=(event)=>{
     const {name,value}=event.target;
     setformdetails({...formdetails,[name]:value})
  }
  const validateData=()=>{
     const myerror={};
     //validate email is not empty or spaces
     if(formdetails.email==="" || formdetails.email.trim().length===0){
      myerror.email="Email cannot be blank or spaces"
     }

     //validate password is not empty or spaces
     if(formdetails.password==="" || formdetails.password.trim().length===0){
      myerror.password="Email cannot be blank or spaces"
     }

     //assign all error messages to the state, and hence component will be rerendered
     seterrors(myerror)
     return myerror
  }
  const validateuser=()=>{
      const errors=validateData();
      //check whether key exists or not
      if(Object.keys(errors).length===0){
        LoginService.validateUSer(formdetails)
        .then((result)=>{
          console.log(result);
          sessionStorage.token=result.data.token;
          setformdetails({email:"",password:""})
          navigate("/table")
        })
        .catch((err)=>{
          console.log(err);
          setformdetails({email:"",password:""})
        })
      }
  }
  return (
    <div>
   
        <form>
  <div className="form-group">
    <label htmlFor="email">Email</label>
    <input type="email" className="form-control" id="email" name="email" 
    value={formdetails.email}
    onChange={handlechange}/>
    {errors.email}
    </div>
    
    <div className="form-group">
    <label htmlFor="password">Password</label>
    <input type="password" className="form-control" id="password" name="password"
     value={formdetails.password}
    onChange={handlechange}/>
    {errors.password}
    <button type="button" name="login" id="login" onClick={validateuser}>Login</button>
  </div>
      </form>
    </div>
  )
}
