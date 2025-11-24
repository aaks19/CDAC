import React,{useEffect,useState} from 'react'
import {useNavigate,useLocation} from 'react-router-dom'
import ProductService from '../service/ProductService'

export default function ProductEdit() {
  const navigate=useNavigate()
  const location=useLocation()
  const [formdetails,setformdetails]=useState({pid:"",pname:"",qty:"",price:"",mfgdate:""})

  useEffect(()=>{
    if (location && location.state && location.state.proddata) {
      setformdetails(location.state.proddata)
    }
  },[location])

  const handlechange=(event)=>{
    var {name,value}=event.target
    setformdetails({...formdetails,[name]:value})
  }

  const updateproduct=(event)=>{
      event.preventDefault()
    if(formdetails.pid==="" || formdetails.pname==="" || formdetails.price==="" || formdetails.qty==="" || formdetails.mfgdate===""){
       alert("pls add all fields")
    }else{
      ProductService.updateProduct(formdetails)
      .then((result)=>{
           console.log(result);
           navigate("/table")
      })
      .catch((err)=>{
         console.log(err)
      })
    }
  }

  return (
    <div className="container mt-4">
      <div className="card p-3">
      <h5>Edit Product</h5>
      <form name="myfrm" onSubmit={updateproduct}>
          <div className="mb-2">
            <label htmlFor="pid">Product Id</label>
            <input type="text" className="form-control" id="pid" name="pid" 
            value={formdetails.pid}
            onChange={handlechange}
            readOnly/>          
          </div>

          <div className="mb-2">
            <label htmlFor="pname">Product Name</label>
            <input type="text" className="form-control" id="pname" name="pname" 
            value={formdetails.pname}
            onChange={handlechange}/>          
          </div>

          <div className="mb-2">
            <label htmlFor="qty">Product Quantity</label>
            <input type="number" className="form-control" id="qty" name="qty"
            value={formdetails.qty}
            onChange={handlechange} />          
          </div>

          <div className="mb-2">
            <label htmlFor="price">Product Price</label>
            <input type="number" className="form-control" id="price" name="price" 
            value={formdetails.price}
            onChange={handlechange}/>          
          </div>

          <div className="mb-3">
            <label htmlFor="mfgdate">Product MFGDate</label>
            <input type="date" className="form-control" id="mfgdate" name="mfgdate" 
            value={formdetails.mfgdate}
            onChange={handlechange}/>          
          </div>

   <button type="submit" name="Update" id="Update" value="Update" className="btn btn-primary">Update Product</button>
  </form>
  </div>
  </div>
  )
}
