import http from '@/util/http-common';

const PostureDetail = (data, callback, errorCallback) => {
    
    http.get('/exercise/detail',
    {
        params: {
            idx : data.id
        }
    }
)
.then((res) => callback(res))
.catch((err) => errorCallback(err));
};

const PostureApi = {

    PostureDetail: (data, callback, errorCallback) => PostureDetail(data, callback, errorCallback),

};

export default PostureApi;