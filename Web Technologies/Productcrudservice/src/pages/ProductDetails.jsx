import React from 'react'
import { useParams } from 'react-router-dom';

export default function ProductDetails() {
    //used to retriev the path parameters
    const params=useParams();
  return (
    <div>
        <h2>You selected Product {params.id}</h2>

    </div>
  )
}
