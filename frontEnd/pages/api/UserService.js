import axios from 'axios'

const ACCOUNT_REST_API_URL = "http://localhost:8080"

class UserAPI{
    getUser(){
        axios.get(USER_REST_API_URL);
    }
}
export default new UserAPI()