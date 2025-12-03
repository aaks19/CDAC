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
    
    updateRestaurant(restaurant){
        let myHeader = {'content-Type':'application/json'}
        return axios.put(baseUrl+"/restaurant/restaurants/"+restaurant.id, restaurant,{header:myHeader})
    }

    deleteRestaurantById(id){
        return axios.delete(baseUrl+"/restaurant/restaurants/"+id);
    }
}

export default new RestaurantService();