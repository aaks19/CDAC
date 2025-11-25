import React, { useEffect, useState } from 'react'
import productservice from '../service/productservice';
import { useLocation, useNavigate } from 'react-router-dom';

export default function ProductEdit() {

    const navigate = useNavigate();
    const location = useLocation();
    const [formdetail, setformdetail] = useState({pid:"", pname:"", qty:"", price:"", mfgdate:""})

    useEffect(()=>{
        setformdetail(location.state.proddata)
    },[])

    const handleChange=(event)=>{
        var {name,value} = event.target;
        setformdetail({...formdetail,[name]:value})
    }

    const updateProduct=(event)=>{
        event.preventDefault();
        if(formdetail.pname===""||formdetail.qty===""||formdetail.price===""||formdetail.mfgdate===""){
            console.log("field can't be empty");
        }else{
            productservice.updateProduct(formdetail)
            .then((result)=>{
                console.log(result);
                navigate("/table")
            }).catch((err)=>{
                console.log(err);
            })
        }
    }
  return (
    <div>
      <form name='myform' onSubmit={updateProduct}>
        <div className="form-group">
            <label htmlFor="pid">Product ID : </label>
            <input type="text" name="pid" id="pid" className='form-control' value={formdetail.pid} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="pname">Product Name : </label>
            <input type="text" name="pname" id="pname" className='form-control' value={formdetail.pname} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="qty">Qty : </label>
            <input type="text" name="qty" id="qty" className='form-control' value={formdetail.qty} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="price">Price : </label>
            <input type="text" name="price" id="price" className='form-control' value={formdetail.price} onChange={handleChange} />
        </div>

        <div className="form-group">
            <label htmlFor="mfgdate">MFGDate : </label>
            <input type="date" name="mfgdate" id="mfgdate" className='form-control' value={formdetail.mfgdate} onChange={handleChange} />
        </div>

        <button type="submit" name='update' id='update' value="update" >Update Product</button>
      </form>
    </div>
  )
}
