import http from '@/util/http-common';

const GetPostureList = (callback, errorCallback) => {
    
  http.get('/exercise/list')
  .then((res) => callback(res))
  .catch((err) => errorCallback(err));
};

const PostureApi = {

  GetPostureList: (callback, errorCallback) => GetPostureList(callback, errorCallback),

};

export default PostureApi;