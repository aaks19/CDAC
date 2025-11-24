import React from 'react'
import { useState,useEffect} from 'react'
import {Link,Outlet} from 'react-router-dom'
import ProductService from '../service/ProductService';


export default function ProductTable() {
  const [parr,setparr]=useState([]);
  //initialization useEffect, it is same as lifecycle method 
  //componentDidMount
  //this is the best place for API calls
  useEffect(()=>{
       fetchdata()
  },[])
  
  const fetchdata= async ()=>{
    //ProductService.getAllProducts().then((result)=>{setparr(result.data)}).catch((err)=>{
    //console.log(err)})
    var result=await ProductService.getAllProducts();
    console.log(result.data)
    setparr(result.data.data)
  }

  const deleteproduct=(id)=>{
    ProductService.deleteProduct(id)
    .then((result)=>{
      fetchdata();
    })
    .catch((err)=>{})

  }
  return (
    <div>
      {/* after clicking on button url will change to /form and form will apear on the screen */}
      <Link to="/form">
      <button type="button" className="btn btn-primary" name="add" id="add">Add new Product</button>
      </Link>
      <br/><br/>
        <table className="table table-striped">
  <thead>
     <tr>
      <th scope="col">ProductId</th>
      <th scope="col">Product Name</th>
      <th scope="col">Quantity</th>
      <th scope="col">Price</th>
      <th scope="col">MfgDate</th>
      <th>action</th>
    </tr>
  </thead>
  <tbody>
     {parr.map(prod=><tr key={prod.pid}>
      <th scope="row">{prod.pid}</th>
      <td>{prod.pname}</td>
      <td>{prod.qty}</td>
      <td>{prod.price}</td>
      <td>{prod.mfgdate}</td>
      <td>
       <Link to={`/edit/${prod.pid}`} state={{proddata:prod}}>
        <button type="button" name="edit" id="edit" className="btn btn-info">edit</button>&nbsp;&nbsp;&nbsp;&nbsp;
</Link> 
        <button type="button" name="delete" id="delete" className="btn btn-danger" onClick={()=>{deleteproduct(prod.pid)}}>delete</button>&nbsp;&nbsp;&nbsp;&nbsp;
<Link to={`/table/details/${prod.pid}`} state={{proddata:prod}}>
        <button type="button" name="View" id="View" className="btn btn-success">View</button>&nbsp;&nbsp;&nbsp;&nbsp;
</Link>
      </td>
    </tr>)}
  
  </tbody>
</table>
<div>
  <Outlet></Outlet>
</div>
    </div>
  )
}
