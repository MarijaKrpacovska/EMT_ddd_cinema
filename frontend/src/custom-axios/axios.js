import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*',

        "Access-Control-Allow-Credentials": "true",
        "Access-Control-Max-Age": "1800",
        "Access-Control-Allow-Headers": "content-type",
         "Access-Control-Allow-Methods": "PUT, POST, GET, DELETE, PATCH, OPTIONS"
    }
})

export default instance;
