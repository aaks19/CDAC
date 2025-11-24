import axios from 'axios';
const baseUrl="http://localhost:3333";
class ProductService{
    
    getAllProducts(){
        //return a promise object
        return axios.get(baseUrl+"/product/products");
    }
    getById(id){
        //return a promise object
        return axios.get(baseUrl+"/product/products/"+id);
    }
     addProduct(product){
        console.log("in addproduct")
        let myheaders={'content-Type':'application/json'}//,'Atherization':"bearer"+sessionStorage.token}
        return axios.post(baseUrl+"/product/products/"+product.pid,product,{headers:myheaders});
       //this.prodarr.push(product)
     }

     updateProduct(product){
        console.log("in updateproduct")
        let myheaders={'content-Type':'application/json'}//,'Atherization':"bearer"+sessionStorage.token}
        return axios.put(baseUrl+"/product/products/"+product.pid,product,{headers:myheaders});
       

     }
     deleteProduct(id){
         return axios.delete(baseUrl+"/product/products/"+id)
     }
}

export default new ProductService()