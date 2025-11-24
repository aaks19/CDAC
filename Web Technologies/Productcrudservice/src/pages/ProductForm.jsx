import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import ProductService from "../service/ProductService";

export default function ProductForm() {
  const { id } = useParams();  // read id from URL
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    pid: "",
    pname: "",
    qty: "",
    price: "",
    mfgdate: ""
  });

  // If ID exists → we are editing → load data
  useEffect(() => {
    if (id) {
      const existingProduct = ProductService.viewProduct(parseInt(id));
      if (existingProduct) {
        setProduct(existingProduct);
      }
    }
  }, [id]);

  // handle input
  const handleChange = (e) => {
    setProduct({ ...product, [e.target.name]: e.target.value });
  };

  // handle save
  const handleSubmit = (e) => {
    e.preventDefault();

    if (id) {
      // EDIT PRODUCT
      ProductService.editProduct(product);
    } else {
      // ADD NEW PRODUCT
      ProductService.addProduct(product);
    }

    navigate("/table"); // redirect back
  };

  return (
    <div className="container">
      <h2>{id ? "Edit Product" : "Add Product"}</h2>

      <form onSubmit={handleSubmit}>

        <label>Product ID</label>
        <input
          type="number"
          name="pid"
          value={product.pid}
          className="form-control"
          onChange={handleChange}
          disabled={id ? true : false}   // Block PID editing
        />

        <label>Product Name</label>
        <input
          type="text"
          name="pname"
          value={product.pname}
          className="form-control"
          onChange={handleChange}
        />

        <label>Quantity</label>
        <input
          type="number"
          name="qty"
          value={product.qty}
          className="form-control"
          onChange={handleChange}
        />

        <label>Price</label>
        <input
          type="number"
          name="price"
          value={product.price}
          className="form-control"
          onChange={handleChange}
        />

        <label>Mfg Date</label>
        <input
          type="date"
          name="mfgdate"
          value={product.mfgdate}
          className="form-control"
          onChange={handleChange}
        />

        <br />
        <button className="btn btn-primary" type="submit">
          {id ? "Update" : "Save"}
        </button>
      </form>
    </div>
  );
}
