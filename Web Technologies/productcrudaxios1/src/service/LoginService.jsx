import axios from 'axios';
const baseUrl="http://localhost:3333";
class LoginService{
    validateUSer(user){
        const myheader={'content-type':'application/json'}
       return axios.post(baseUrl+"/login/loginuser",user,{headers:myheader})
    }
}

export default new LoginService()