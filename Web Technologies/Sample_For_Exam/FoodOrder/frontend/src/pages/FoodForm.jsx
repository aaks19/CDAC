import React from 'react'
import { useState } from 'react'
import FoodService from '../service/FoodService';
import { useNavigate } from 'react-router-dom';

export default function FoodForm() {
    const [formdetail, setformdetail] = useState({fid:"",fname:"",price:"",qty:""})
    const navigate = useNavigate();

    const addFood=(e)=>{
        e.preventDefault();
        if(formdetail.fid===""||formdetail.fname===""||formdetail.price===""||formdetail.qty===""){
            alert("Fields can't be blank")
        }else{
            FoodService.addFood(formdetail)
            .then(()=>{
                alert("Added")
                navigate('/')
            })
        }
    }

    const resetForm=()=>{
        setformdetail({fid:"",fname:"",price:"",qty:""})
    }

    const handleChange=(e)=>{
        const {name,value}=e.target;
        setformdetail({...formdetail,[name]:value})
    }
  return (
    <div>
        <form onSubmit={addFood}>
            <label>Food Id</label>
            <input type="text" name="fid" id="fid" value={formdetail.fid} onChange={handleChange} /><br /><br />

            <label>Food Name</label>
            {/* Normal Input Text */}
            {/* <input type="text" name="fname" id="fname" value={formdetail.fname} onChange={handleChange} /><br /><br /> */}

            {/* DropDown */}
            {/* <select name='fname' id='fname' value={formdetail.fname} onChange={handleChange}>
                <option value="">select food</option>
                <option value="Pizza">pizza</option>
                <option value="Burger">Burger</option>
                <option value="Pasta">Pasta</option>

            </select><br /><br /> */}

            {/* radio button */}
            <input type="radio" name="fname" value="pizza" checked={formdetail.fname === 'pizza'} onChange={handleChange}/> Pizza
            <input type="radio" name="fname" value="a" checked={formdetail.fname === 'a'} onChange={handleChange}/> a
            <input type="radio" name="fname" value="b" checked={formdetail.fname === 'b'} onChange={handleChange}/> b
            <br /><br />

            <label>Price</label>
            <input type="number" name="price" id="price" value={formdetail.price} onChange={handleChange} /><br /><br />

            <label>Quantity</label>
            <input type="number" name="qty" id="qty" value={formdetail.qty} onChange={handleChange} /><br /><br />

            <button type='submit'>Add food</button>
                    <button type="button" onClick={resetForm}>Reset</button>

        </form>
    </div>
  )
}
