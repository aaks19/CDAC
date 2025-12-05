import axios from 'axios'
const url = "http://localhost:3333";

class MedicineService{
    getAllMedicine(){
        return axios.get(url+"/medicine/medicines")
    }

    insertMedicine(meds){
        let myHeader = {'content-Type':'application/json'}
        return axios.post(url+"/medicine/medicines",meds,{headers:myHeader})
    }

    updateMedicine(meds){
        let myHeader = {'content-Type':'application/json'}
        return axios.put(url+"/medicine/medicines/"+meds.id,meds,{headers:myHeader})
    }

    deleteMedicine(id){
        return axios.delete(url+"/medicine/medicines/"+id)
    }
}

export default new MedicineService();