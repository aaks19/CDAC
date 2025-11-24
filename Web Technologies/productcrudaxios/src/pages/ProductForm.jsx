import React,{useState} from 'react'
import {useNavigate} from 'react-router-dom'
import ProductService from '../service/ProductService'
export default function ProductForm() {
  const navigate=useNavigate()
  const [formdetails,setformdetails]=useState({pid:"",pname:"",qty:"",price:"",mfgdate:""})
//add product in the array
const addproduct=(event)=>{
  event.preventDefault()
  if(formdetails.pid==="" || formdetails.pname==="" || formdetails.price==="" || formdetails.qty==="" || formdetails.mfgdate===""){
     alert("pls add all fields")
  }else{
    //add product in the prodarr in ProductService
     ProductService.addProduct(formdetails)
     .then((result)=>{
      console.log(result);
    //clear form    setformdetails({pid:"",pname:"",qty:"",price:"",mfgdate:""})
//navigate to /table url
navigate("/table")
     })
     .catch((err)=>{
      console.log(err)
     });
     //clear the form
    
  }
    
}

const handlechange=(event)=>{
  //var name=event.target.name;
  //var value=event.target.value;
  var {name,value}=event.target
  setformdetails({...formdetails,[name]:value})
}

//const handlepname=(event)=>{
//  setformdetails({...formdetails,pname:event.target.value})
//}

  return (
    <div>
      <form name="myfrm" onSubmit={addproduct}>
          <div className="form-group">
            <label htmlFor="pid">Product Id</label>
            <input type="text" className="form-control" id="pid" name="pid" 
            value={formdetails.pid}
            onChange={handlechange}/>          
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

   <button type="submit" name="add" id="add" value="add" className="btn btn-primary">Add Product</button>
  </form>
    </div>
  )
}
