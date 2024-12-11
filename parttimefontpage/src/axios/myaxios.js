import axios from 'axios';
const myaxios = axios.create({
    baseURL:'http://localhost:8080',
    timeout:5000
})

// myaxios.interceptors.request.use(
//     request=>{
//         let res = request.data;
//         if(request.config.responseType==='blob'){
//             return res;
//         }
//         if(typeof res==='string'){
//             res = JSON.parse(res);
//         }
//         return res;
//     },
//     error=>{
//         console.log("err:"+error)
//         return Promise.reject(error);
//     }
// )

export default myaxios