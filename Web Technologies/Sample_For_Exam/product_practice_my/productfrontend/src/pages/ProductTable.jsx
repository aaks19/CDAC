import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import productservice from "../service/productservice";

export default function ProductTable() {
  const [parr, setparr] = useState([]);
    const [searchtxt, setsearchtxt] = useState("")

  useEffect(() => {
    fetchdata();
  }, []);

  const fetchdata = async () => {
    var result = await productservice.getAllProduct();
    console.log(result.data);
    setparr(result.data.data);
  };

  const deleteProduct=(id)=>{
    productservice.deleteProduct(id)
    .then((result)=>{
        fetchdata();
    })
    .catch((err)=>{
        console.log("error in deleting");
    })
  }

  const handleChange=(ev)=>{
    setsearchtxt(ev.target.value);
  }

  const filterProd = parr.filter(prod=>prod.pname.toLowerCase().includes(searchtxt.toLowerCase()));

  return (
    <div>
<label htmlFor="search">Search Product : </label>
<input type="text" name="searchtxt" id="search" value={searchtxt} onChange={handleChange} />
<br /><br />

      <Link to="/form">
        <button type="button" name="add" id="add">
          Add new product
        </button>
      </Link>
      <br />
      <br />
      <table>
        <thead>
          <tr>
            <th scope="col">ProductId</th>
            <th scope="col">Product Name</th>
            <th scope="col">quantity</th>
            <th scope="col">price</th>
            <th scope="col">mfgdate</th>
            <th>action</th>
          </tr>
        </thead>
        <tbody>
          {filterProd.map((prod) => (
            <tr key={prod.pid}>
              <th scope="row">{prod.pid}</th>
              <td>{prod.pname}</td>
              <td>{prod.qty}</td>
              <td>{prod.price}</td>
              <td>{prod.mfgdate}</td>
              <td>
                <Link to={`/edit/${prod.pid}`} state={{ proddata: prod }}>
                  <button type="button" name="edit" id="edit">
                    Edit
                  </button>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                </Link>
                <Link
                  to={`/table/details/${prod.pid}`}
                  state={{ proddata: prod }}
                >
                  <button type="button" name="view" id="view">
                    View
                  </button>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                </Link>
                <button type="button" name="delete" id="delete" onClick={()=>{deleteProduct(prod.pid)}}>
                  Delete
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
