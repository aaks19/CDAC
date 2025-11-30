import React from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import vehicleservice from '../service/vehicleservice';

export default function VehicleForm() {
    const navigate = useNavigate();
    const [formdetail,setformdetail] = useState({id:"",vname:"",price:"",mfgdate:""})

    const addVehicle=(event)=>{
        event.preventDefault();
        if(formdetail.id===""||formdetail.vname===""||formdetail.price===""||formdetail.mfgdate===""){
            alert("Values can't be empty")
        }else{
            vehicleservice.addVehicle(formdetail)
            .then((result)=>{
                console.log("Vehicle added "+result);
                navigate('/table')
            })
            .catch((err)=>{
                console.log(err);
                
            })
        }
    }


    const handleChange=(event)=>{
        var {name,value}=event.target;
        setformdetail({...formdetail,[name]:value})
    }
  return (
    <div>
      <form name='myform' onSubmit={addVehicle}>
        <div className="form-group">
            <label htmlFor="id">Vehicle ID</label>
            <input type="text" name="id" id="id" className='form-control' value={formdetail.id} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="vname">Vehicle Name</label>
            <input type="text" name="vname" id="vname" className='form-control' value={formdetail.vname} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="price">Price</label>
            <input type="text" name="price" id="price" className='form-control' value={formdetail.price} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="mfgdate">MFG Date</label>
            <input type="date" name="mfgdate" id="mfgdate" className='form-control' value={formdetail.mfgdate} onChange={handleChange}/>
        </div>

    <button type="submit" name='add' id='add' value='add'>Add Vehicle</button>
      </form>
    </div>
  )
}
