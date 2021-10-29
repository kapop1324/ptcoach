import http from '@/util/http-common';
//import store from '@/vuex/store.js';

const Login = (data, callback, errorCallback) => {
    
    http.post('/user/login',data
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const Join = (data, callback, errorCallback) => {
    
    http.post('/user/join',data
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const UserInfo = (data, callback, errorCallback) => {
    
    http.get('/user/userinfo',
        {
            params: {
                email : data.email
            }
        }
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const IdCheck = (data, callback, errorCallback) => {
    
    http.get('/user/idcheck',
        {
            params: {
                email : data.email
            }
        }
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const Leave = (data, callback, errorCallback) => {
    
    http.delete('/user/leave',
        {
            params: {
                email : data.email
            }
        }
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const UserApi = {

    Login: (data, callback, errorCallback) => Login(data, callback, errorCallback),
    Join: (data, callback, errorCallback) => Join(data, callback, errorCallback),
    UserInfo: (data, callback, errorCallback) => UserInfo(data, callback, errorCallback),
    IdCheck: (data, callback, errorCallback) => IdCheck(data, callback, errorCallback),
    Leave: (data, callback, errorCallback) => Leave(data, callback, errorCallback),

};

export default UserApi;