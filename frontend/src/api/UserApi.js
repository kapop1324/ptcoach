import http from '@/util/http-common';
//import store from '@/vuex/store.js';

const Login = (data, callback, errorCallback) => {
    
    http.post('/user/login',data
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const UserApi = {

    Login: (data, callback, errorCallback) => Login(data, callback, errorCallback),
};

export default UserApi;