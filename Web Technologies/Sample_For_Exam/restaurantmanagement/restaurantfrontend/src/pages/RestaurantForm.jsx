import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import restaurantservice from "../service/restaurantservice";


export default function RestaurantForm() {
    const navigate = useNavigate();
  const [formdetail, setformdetail] = useState({
    id: "",
    rname: "",
    phone: "",
    rlocation: "",
  });

  const addRestaurant = (event) => {
    event.preventDefault();
    if (
      formdetail.id === "" ||
      formdetail.phone === "" ||
      formdetail.price === "" ||
      formdetail.mfgdate === ""
    ) {
      alert("values cant be empty");
    } else {
      restaurantservice
        .addRestaurant(formdetail)
        .then((result) => {
          console.log("Restaurant Added" + result);
          navigate("/table");
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const handleChange = (event) =>{
    var {name,value} = event.target;
    setformdetail({...formdetail,[name]:value})
  }
  return (
    <div>
      <form name="myform" onSubmit={addRestaurant}>
        <div className="form-group">
            <label htmlFor="id">Restaurant Id</label>
            <input type="text" name="id" id="id" className="form-control" value={formdetail.id} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="id">Restaurant Name</label>
            <input type="text" name="rname" id="rname" className="form-control" value={formdetail.rname} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="id">Restaurant Phone</label>
            <input type="text" name="phone" id="phone" className="form-control" value={formdetail.phone} onChange={handleChange}/>
        </div>

        <div className="form-group">
            <label htmlFor="id">Restaurant Location</label>
            <input type="text" name="rlocation" id="rlocation" className="form-control" value={formdetail.rlocation} onChange={handleChange}/>
        </div>

        <button type="submit" name="add" id="add" value='add'>Add new restaurant</button>
      </form>
    </div>
  )
}

