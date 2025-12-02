import axios from 'axios';
const baseUrl = "http://localhost:3333";

class RestaurantService{
    getAllRestaurant(){
        return axios.get(baseUrl+"/restaurant/restaurants");
    }

    addRestaurant(restaurant){
        let myHeader = {'content-Type':'application/json'};
        return axios.post(baseUrl+"/restaurant/restaurants",restaurant,{headers:myHeader})
    }
    
}

export default new RestaurantService();