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

const MyRecord = (data, callback, errorCallback) => {
    
    http.get('/user/daily',
        {
            params: {
                email : data
            }
        }
    )
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const DailyRecord = (data, callback, errorCallback) => {
    
    http.get('/user/dailydetail',
        {
            params: {
                email: data.email,
                date: data.date,
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
    MyRecord: (data, callback, errorCallback) => MyRecord(data, callback, errorCallback),
    DailyRecord: (data, callback, errorCallback) => DailyRecord(data, callback, errorCallback),

};

export default UserApi;