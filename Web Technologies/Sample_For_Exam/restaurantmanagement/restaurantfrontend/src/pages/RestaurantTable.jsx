import React, { useEffect, useState } from 'react'
import {Link} from 'react-router-dom'
import restaurantservice from '../service/restaurantservice';

export default function RestaurantTable() {
    const [restaurantarr , setrestaurantarr] = useState([]);

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData = async()=>{
        var result = await restaurantservice.getAllRestaurant();
        console.log(result);
        setrestaurantarr(result.data.data);        
    }
  return (
    <div>
        <Link to={'/form'}>
        <button type="button" name='add' id='add'>Add Restaurant</button>
      </Link>
      <br /><br /><br />
      <table>
        <thead>
            <tr>
                <th scope='col'>restaurant Id</th>
                <th scope='col'>restaurant Name</th>
                <th scope='col'>Price</th>
                <th scope='col'>location</th>
            </tr>
        </thead>
        <tbody>
            {restaurantarr.map((restaurant)=>(
                <tr key={restaurant.id}>
                    <td>{restaurant.id}</td>
                    <td>{restaurant.rname}</td>
                    <td>{restaurant.price}</td>
                    <td>{restaurant.rlocation}</td>
                    <td>
                        <Link to={`/edit/${restaurant.id}`} state={{restaurantdata:restaurant}}>
                        <button type='button' name='edit' id='edit'>Edit</button>
                        </Link>

                        <button type="button" name='delete' id='delete'>Delete</button>
                    </td>
                </tr>
            ))}
        </tbody>
      </table>
    </div>
  )
}
