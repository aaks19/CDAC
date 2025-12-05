import axios from 'axios'

const baseurl = "http://localhost:3333";

class FoodService{
    getFoods(){
        return axios.get(baseurl+"/food/foods");
    }

    addFood(food){
        let myHeader = {'content-Type':'application/json'}
        return axios.post(baseurl+"/food/foods",food,{headers:myHeader})
    }

    updateFood(food){
         let myHeader = {'content-Type':'application/json'}
        return axios.put(baseurl+"/food/foods/"+food.fid,food,{headers:myHeader})
    }

    deleteFood(id){
        return axios.delete(baseurl+"/food/foods/"+id)
    }
}

export default new FoodService();