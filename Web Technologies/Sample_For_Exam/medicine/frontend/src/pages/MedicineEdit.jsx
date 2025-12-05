import React from 'react'
import MedicineService from '../service/MedicineService';
import { useLocation, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';

export default function MedicineEdit() {
     const [formdetail, setformdetail] = useState({
    id: "",
    name: "",
    type: "",
    qty: "",
    price: "",
  });
  const navigate = useNavigate();
  const  location = useLocation();

  useEffect(()=>{
    setformdetail(location.state.meddata)
  },[])

  const updateMedicine = (e) => {
    e.preventDefault();
    if (
      formdetail.id === "" ||
      formdetail.name === "" ||
      formdetail.price === "" ||
      formdetail.type === "" ||
      formdetail.qty === "" ||
      formdetail.price <= 0 ||
      formdetail.qty <= 0
    ) {
        alert("Can't be empty")
    }else{
        MedicineService.updateMedicine(formdetail)
        .then(()=>{
            alert("medicine Updated")
            navigate('/')
        })
        .catch((err)=>{
            console.log(err);
            
        })
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setformdetail({ ...formdetail, [name]: value });
  };
  return (
    <div>
      <form onSubmit={updateMedicine}>
        <label>Id</label>
        <input
          type="text"
          name="id"
          id="id"
          value={formdetail.id}
          onChange={handleChange}
          readOnly
        /><br/><br/>

        <label>Medicine Name</label>
        <input
          type="text"
          name="name"
          id="name"
          value={formdetail.name}
          onChange={handleChange}
        /><br/><br/>

        <label>Medicine Type</label>
        <select
          name="type"
          id="type"
          value={formdetail.type}
          onChange={handleChange}
        >
          <option value="">Select Medicine Type</option>
          <option value="Homeopathy">Homeopathy</option>
          <option value="Aelophathy">Aelophathy</option>
          <option value="Ayurvedic">Ayurvedic</option>
        </select><br/><br/>

        <label>Quantity</label>
        <input
          type="number"
          name="qty"
          id="qty"
          value={formdetail.qty}
          onChange={handleChange}
        /><br/><br/>

        <label>Price</label>
        <input
          type="number"
          name="price"
          id="price"
          value={formdetail.price}
          onChange={handleChange}
        /><br/><br/>

        <button type="submit">Add Medicine</button>
      </form>
    </div>
  )
}
