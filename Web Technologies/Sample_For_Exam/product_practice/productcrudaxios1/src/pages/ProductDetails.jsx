import React from 'react'
import { useState,useEffect } from 'react';
import { useParams,useLocation,Link} from 'react-router-dom';
import ProductService from '../service/ProductService';

export default function ProductDetails() {
    //used to retriev the path parameters
    const params=useParams();
    const location=useLocation()
    const [product,setProduct]=useState({})
    useEffect(()=>{
      //setProduct(location.state.proddata)

      ProductService.getById(params.id)
      .then((result)=>{
          setProduct({...result.data.data})
      })
      .catch((err)=>{
          console.log(err)
      })
    },[params.id])
  return (
    <div>
        <div className="card" style={{"width": "18rem"}}>
  <div className="card-body">
    <h5 className="card-title">Name : {product.pname}</h5>
    <h6 className="card-subtitle mb-2 text-muted">Id :{product.pid}</h6>
    <p className="card-text">Price: {product.price}</p>
    <p className="card-text">Qty: {product.qty}</p>
    <p className="card-text">MfgDate: {product.mfgdate}</p>
    <Link to="/table">
    <button type="button" name="back" id="back">Back</button>
    </Link>
  </div>
</div>

    </div>
  )
}
