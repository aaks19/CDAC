import axios from 'axios';

const baseurl = "http://localhost:3333";

class VehicleService{
    getAllVehicles(){
        return axios.get(baseurl+"/vehicle/vehicles");
    }

    addVehicle(vehicle){
        let myHeader = {'content-Type':'application/json'};
        return axios.post(baseurl+"/vehicle/vehicles", vehicle,{headers:myHeader})
    }

    updateVehicle(vehicle){
        let myHeader = {'content-Type':'application/json'};
        return axios.put(baseurl+"/vehicle/vehicles/"+vehicle.id, vehicle, {headers:myHeader})
    }
    
    deleteVehicle(id){
        return axios.delete(baseurl+"/vehicle/vehicles/"+id)
    }
}

export default new VehicleService();