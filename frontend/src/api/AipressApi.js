import http from '@/util/http-common';


const Insert = (data, callback, errorCallback) => {
    http.post('/correction/save', data)
        .then((res) => callback(res))
        .catch((err) => errorCallback(err));
};

const AipressApi = {
    Insert: (data,callback,errorCallback) => Insert(data, callback, errorCallback),
};

export default AipressApi;
