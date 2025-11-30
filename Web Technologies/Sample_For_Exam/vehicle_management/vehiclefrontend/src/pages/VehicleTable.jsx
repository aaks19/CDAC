import React, { useEffect, useState } from 'react'

import { Link } from 'react-router-dom';
import vehicleservice from '../service/vehicleservice';

export default function VehicleTable() {

    const [vehiclearr, setvehiclearr] = useState([]);

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData = async()=>{
        var result = await vehicleservice.getAllVehicles();
        console.log(result);
        setvehiclearr(result.data.data)        
    }

  return (
    <div>
        <Link to={'/form'}>
        <button type="button" name='add' id='add'>Add Vehicle</button>
        </Link>

        <br /><br />
        <table>
            <thead>
                <tr>
                    <th scope='col'>vehicle id</th>
                    <th scope='col'>Name</th>
                    <th scope='col'>Price</th>
                    <th scope='col'>MFG Date</th>
                    <th>action</th>
                </tr>
            </thead>
            <tbody>
                {vehiclearr.map((vehicle)=>(
                    <tr key={vehicle.id}>
                        <th scope='row'>{vehicle.id}</th>
                        <td>{vehicle.vname}</td>
                        <td>{vehicle.price}</td>
                        <td>{vehicle.mfgdate}</td>
                        <td>
                            <Link to={`/edit/${vehicle.id}`} state={{vehicledata:vehicle}}>
                            <button type="button" name='edit' id='edit'>Edit</button>
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
