import React from 'react'
import { useState } from 'react'
import FoodService from '../service/FoodService';
import { useLocation, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

export default function FoodEdit() {
    const [formdetail, setformdetail] = useState({fid:"",fname:"",price:"",qty:""})
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(()=>{
        setformdetail(location.state.fooddata)
    },[])

    const updateFood=(e)=>{
        e.preventDefault();
        if(formdetail.fid===""||formdetail.fname===""||formdetail.price===""||formdetail.qty===""){
            alert("Fields can't be blank")
        }else{
            FoodService.updateFood(formdetail)
            .then(()=>{
                alert("Updated")
                navigate('/')
            })
        }
    }
    const handleChange=(e)=>{
        const {name,value}=e.target;
        setformdetail({...formdetail,[name]:value})
    }
  return (
    <div>
        <form onSubmit={updateFood}>
            <label>Food Id</label>
            <input type="text" name="fid" id="fid" value={formdetail.fid} onChange={handleChange} /><br /><br />

            <label>Food Name</label>
            <input type="text" name="fname" id="fname" value={formdetail.fname} onChange={handleChange} /><br /><br />

            <label>Price</label>
            <input type="number" name="price" id="price" value={formdetail.price} onChange={handleChange} /><br /><br />

            <label>Quantity</label>
            <input type="number" name="qty" id="qty" value={formdetail.qty} onChange={handleChange} /><br /><br />

            <button type='submit'>Update food</button>
        </form>
    </div>
  )
}
