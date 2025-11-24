import React, { useState, useEffect } from 'react'
import { Link, Outlet } from 'react-router-dom'
import ProductService from '../service/ProductService';

export default function ProductTable() {
  const [parr, setparr] = useState([]);

  useEffect(() => {
    fetchdata();
  }, []);

  const fetchdata = async () => {
    try {
      const result = await ProductService.getAllProducts();
      // expecting backend shape: { data: { data: [ ... ] } }
      setparr(result.data.data);
    } catch (err) {
      console.log(err);
      setparr([]);
    }
  };

  const deleteproduct = (id) => {
    if (!window.confirm('Delete product?')) return;
    ProductService.deleteProduct(id)
      .then(() => fetchdata())
      .catch(err => console.log(err));
  };

  return (
    <div className="container mt-4">
      <Link to="/form">
        <button className="btn btn-primary mb-3">Add new Product</button>
      </Link>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ProductId</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>MfgDate</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {parr.map(prod => (
            <tr key={prod.pid}>
              <th scope="row">{prod.pid}</th>
              <td>{prod.pname}</td>
              <td>{prod.qty}</td>
              <td>{prod.price}</td>
              <td>{prod.mfgdate}</td>
              <td>
                <Link to={`/edit/${prod.pid}`} state={{ proddata: prod }}>
                  <button className="btn btn-info btn-sm me-2">Edit</button>
                </Link>

                <button
                  className="btn btn-danger btn-sm me-2"
                  onClick={() => deleteproduct(prod.pid)}
                >
                  Delete
                </button>

                <Link to={`/table/details/${prod.pid}`} state={{ proddata: prod }}>
                  <button className="btn btn-success btn-sm">View</button>
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Important for nested route /table/details/:id */}
      <Outlet />
    </div>
  );
}
