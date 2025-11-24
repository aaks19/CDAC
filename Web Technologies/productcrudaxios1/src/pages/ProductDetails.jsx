import React from 'react'
import { useParams,useLocation,Link } from 'react-router-dom';
import ProductService from '../service/ProductService';
import { useEffect, useState } from 'react';

export default function ProductDetails() {
    const params=useParams();
    const location=useLocation()
    const [product,setProduct]=useState({})

    useEffect(()=>{
      if (location && location.state && location.state.proddata) {
        // if product was passed via Link state, use it (faster)
        setProduct(location.state.proddata)
      } else {
        // otherwise fetch from backend
        ProductService.getById(params.id)
        .then((result)=>{
            setProduct({...result.data.data})
        })
        .catch((err)=>{
            console.log(err)
        })
      }
    },[params.id, location])

  return (
    <div className="container mt-3">
        <div className="card" style={{"width": "18rem"}}>
          <div className="card-body">
            <h5 className="card-title">Name : {product.pname}</h5>
            <h6 className="card-subtitle mb-2 text-muted">Id : {product.pid}</h6>
            <p className="card-text">Price: {product.price}</p>
            <p className="card-text">Qty: {product.qty}</p>
            <p className="card-text">MfgDate: {product.mfgdate}</p>
            <Link to="/table">
              <button type="button" className="btn btn-secondary">Back</button>
            </Link>
          </div>
        </div>
    </div>
  )
}
