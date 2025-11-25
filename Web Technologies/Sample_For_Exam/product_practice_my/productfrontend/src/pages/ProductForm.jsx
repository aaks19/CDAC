import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import productservice from '../service/productservice';

export default function ProductForm() {

    const navigate = useNavigate();
    const [formdetail, setformdetail] = useState({pid:"", pname:"", qty:"", price:"", mfgdate:""})

    const addProduct=(event)=>{
        event.preventDefault()
        if(formdetail.pid==="" || formdetail.pname===""|| formdetail.qty===""||formdetail.price===""||formdetail.mfgdate===""){
            alert("can't be empty")
        }else{
            productservice.addProduct(formdetail)
            .then((result)=>{
                console.log(result);
                navigate("/table")
            })
            .catch((err)=>{
                console.log(err);
            })
        }
    }

    const handleChange=(event)=>{
        var {name,value} = event.target;
        setformdetail({...formdetail,[name]:value})
    }

  return (
    <div>
      <form name='myform' onSubmit={addProduct}>
        <div className="form-group">
            <label htmlFor="pid">Product ID : </label>
            <input type="text" name="pid" id="pid" className='form-control' value={formdetail.pid} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="pname">Product Name : </label>
            <input type="text" name="pname" id="pname" className='form-control' value={formdetail.pname} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="qty">Product ID : </label>
            <input type="text" name="qty" id="qty" className='form-control' value={formdetail.qty} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="price">Product ID : </label>
            <input type="text" name="price" id="price" className='form-control' value={formdetail.price} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="mfgdate">Product ID : </label>
            <input type="date" name="mfgdate" id="mfgdate" className='form-control' value={formdetail.mfgdate} onChange={handleChange} />
        </div>

        <button type="submit" name='add' id='add' value="add" >AddProduct</button>
      </form>
    </div>
  )
}
