import http from '@/util/http-common';

const GetPostureList = (callback, errorCallback) => {
    
  http.get('/exercise/list')
  .then((res) => callback(res))
  .catch((err) => errorCallback(err));
};

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

  GetPostureList: (callback, errorCallback) => GetPostureList(callback, errorCallback),
  PostureDetail: (data, callback, errorCallback) => PostureDetail(data, callback, errorCallback),


};

export default PostureApi;