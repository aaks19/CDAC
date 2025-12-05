import React from "react";
import { useState } from "react";
import FoodService from "../service/FoodService";
import { useEffect } from "react";
import { Link } from "react-router-dom";

export default function FoodList() {
  const [foodarr, setfoodarr] = useState([]);
  const [searchtxt, setsearchtxt] = useState("");

  const fetchData = async () => {
    var result = await FoodService.getFoods();
    console.log(result);
    setfoodarr(result.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const deleteFood = (id) => {
    FoodService.deleteFood(id)
      .then(() => {
        fetchData();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleChange = (e) => {
    setsearchtxt(e.target.value);
  };
  return (
    <div>
      <label htmlFor="search">Search Food : </label>
      <input
        type="text"
        name="searchtxt"
        id="search"
        value={searchtxt}
        onChange={handleChange}
      />
      <br />
      <br />
      <Link to="/form">
        <button>Add Button</button>
      </Link>
      <br />
      <br />
      {/* {foodarr.filter((f)=>f.fname.toLowerCase().includes(searchtxt.toLowerCase())).map(f=>(
            <div key={f.fid}>
                {f.fid} | {f.fname} | {f.price} | {f.qty}
                <Link to={`edit/${f.fid}`} state={{fooddata: f}}>
                <button>Edit</button>
                </Link>
                <button onClick={()=>deleteFood(f.fid)}>Delete</button>
            </div>
        ))} */}

      <table>
        <thead>
          <tr>
            <th scope="col">Food Id</th>
            <th scope="col">Food Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {foodarr.map((f) => (
            <tr key={f.fid}>
              <td>{f.fid}</td>
              <td>{f.fname}</td>
              <td>{f.qty}</td>
              <td>{f.price}</td>
              <td>
                <Link to={`/edit/${f.fid}`} state={{fooddata: f}}>
                <button type="button" name="edit" id="edit">Edit</button>
                </Link>

                <button type="button" name="delete" id="delete" onClick={()=>deleteFood(f.fid)}>Delete</button>
              </td>

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
