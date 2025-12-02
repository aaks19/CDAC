import axios from 'axios';

const baseUrl = "http://localhost:3333";

class RestaurantService{
    getAllRestaurnt(){
        return axios.get(baseUrl+"/restaurant/restaurants")
    }
}