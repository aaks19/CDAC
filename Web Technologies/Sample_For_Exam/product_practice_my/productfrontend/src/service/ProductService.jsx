import axios from 'axios';
const baseUrl ='http://localhost:3333';

class ProductService{
    getAllProduct(){
        return axios.get(baseUrl+"/product/products")
    }

    addProduct(product){
        console.log("in add product");
        let myHeader = {'content-Type':'application/json'}
        return axios.post(baseUrl+"/product/products/"+product.pid,product,{headers:myHeader})
    }

    updateProduct(product){
        console.log("in update product");
        let myHeader = {'content-Type':'application/json'}
        return axios.put(baseUrl+"/product/products/"+product.pid,product,{headers:myHeader})
    }

    deleteProduct(id){
        return axios.delete(baseUrl+"/product/products/"+id)
    }
}

export default new ProductService();