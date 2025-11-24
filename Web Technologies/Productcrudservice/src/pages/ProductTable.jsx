import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import ProductService from '../service/ProductService';

export default function ProductTable() {
  const [parr, setparr] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    setparr([...ProductService.getAllProducts()]);
  }, []);

  const handleDelete = (pid) => {
    ProductService.deleteProduct(pid);
    setparr([...ProductService.getAllProducts()]);
  };

 const handleEdit = (pid) => {
  navigate(`/form/${pid}`);
};

  const handleView = (pid) => {
    navigate(`/table/details/${pid}`);
  };

  return (
    <div>
      <Link to="/form">
        <button className="btn btn-primary">Add new Product</button>
      </Link>

      <br/><br/>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ProductId</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>MfgDate</th>
            <th>action</th>
          </tr>
        </thead>
        <tbody>
          {parr.map(prod => (
            <tr key={prod.pid}>
              <td>{prod.pid}</td>
              <td>{prod.pname}</td>
              <td>{prod.qty}</td>
              <td>{prod.price}</td>
              <td>{prod.mfgdate}</td>
              <td>
                <button className="btn btn-info" onClick={() => handleEdit(prod.pid)}>edit</button>&nbsp;
                <button className="btn btn-danger" onClick={() => handleDelete(prod.pid)}>delete</button>&nbsp;
                <button className="btn btn-success" onClick={() => handleView(prod.pid)}>View</button>&nbsp;
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
