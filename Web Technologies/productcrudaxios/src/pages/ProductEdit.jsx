import React,{useEffect,useState} from 'react'
import {useNavigate,useLocation} from 'react-router-dom'
import ProductService from '../service/ProductService'
export default function ProductEdit() {
  const navigate=useNavigate()
  const location=useLocation()
  useEffect(()=>{
    setformdetails(location.state.proddata)
  },[])
  const [formdetails,setformdetails]=useState({pid:"",pname:"",qty:"",price:"",mfgdate:""})

const handlechange=(event)=>{
  //var name=event.target.name;
  //var value=event.target.value;
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
    <div>
      <form name="myfrm" onSubmit={updateproduct}>
          <div className="form-group">
            <label htmlFor="pid">Product Id</label>
            <input type="text" className="form-control" id="pid" name="pid" 
            value={formdetails.pid}
            onChange={handlechange}
            readOnly/>          
          </div>

          <div className="form-group">
            <label htmlFor="pname">Product Name</label>
            <input type="text" className="form-control" id="pname" name="pname" 
            value={formdetails.pname}
            onChange={handlechange}/>          
          </div>

          <div className="form-group">
            <label htmlFor="qty">Product Quantity</label>
            <input type="text" className="form-control" id="qty" name="qty"
            value={formdetails.qty}
            onChange={handlechange} />          
          </div>

          <div className="form-group">
            <label htmlFor="price">Product Price</label>
            <input type="text" className="form-control" id="price" name="price" 
            value={formdetails.price}
            onChange={handlechange}/>          
          </div>

          <div className="form-group">
            <label htmlFor="mfgdate">Product MFGDate</label>
            <input type="date" className="form-control" id="mfgdate" name="mfgdate" 
            value={formdetails.mfgdate}
            onChange={handlechange}/>          
          </div>

   <button type="submit" name="Update" id="Update" value="Update" className="btn btn-primary">Update Product</button>
  </form>
    </div>
  )
}
