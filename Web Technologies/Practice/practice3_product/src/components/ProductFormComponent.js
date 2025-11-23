import React, { useState } from "react";

export default function ProductFormComponent(props) {
  const [formDetails, setFormDetails] = useState({ pname: "" });

  const handleChange = (event) => {
    setFormDetails({ ...formDetails, pname: event.target.value });
  };

  const addProduct=(pnm)=>{
    if(formDetails.pname.trim() === 0){
        alert("no")
    }else{
        props.addProduct(formDetails.pname)
        setFormDetails({...formDetails, pname:""})
    }
  }
  return (
    <>
      <form>
        <div className="form-group">
          <label>Product Name : </label>
          <input
            type="text"
            className="form-control"
            value={formDetails.pname}
            onChange={handleChange}
          />
        </div>
        <button type="button" onClick={addProduct}>Add Product</button>
      </form>
    </>
  );
}
