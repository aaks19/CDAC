import React from "react";
import { useState } from "react";
import MedicineService from "../service/MedicineService";
import { useEffect } from "react";
import { Link } from "react-router-dom";

export default function MedicineList() {
  const [medarr, setmedarr] = useState([]);

  const fetchData = async () => {
    var result = await MedicineService.getAllMedicine();
    console.log(result);
    setmedarr(result.data);
  };

  const deleteMedicine=(id)=>{
    MedicineService.deleteMedicine(id)
    .then(()=>{
        console.log("deleted");
        fetchData();
    })
    .catch((err)=>{
        console.log(err);
        
    })
  }

  useEffect(() => {
    fetchData();
  }, []);
  return (
    <div>
        <Link to="/form">
      <button>Add Medicine</button>
      </Link>
      <br />
      <table>
        <thead>
            <tr>
                <th scope="col">Medicine Id</th>
                <th scope="col">Medicine Name</th>
                <th scope="col">Type</th>
                <th scope="col">Quantity</th>
                <th scope="col">Price</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            {medarr.map(m=>(
                <tr key={m.id}>
                    <td>{m.id}</td>
                    <td>{m.name}</td>
                    <td>{m.type}</td>
                    <td>{m.qty}</td>
                    <td>{m.price}</td>
                    <td>
                        <Link to={`/edit/${m.id}`} state={{meddata : m}}>
                        <button type="button" name="edit" id="edit">Edit</button>
                        </Link>
                        <button type="button" name="delete" id="delete" onClick={()=>deleteMedicine(m.id)}>Delete</button>
                    </td>
                </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
}
