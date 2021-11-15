import http from '@/util/http-common';

const GetDashboard = (data, callback, errorCallback) => {
  // params: 유저 이메일로 변경할것
  // http.get('/dashboard/record', {params: {email: "rlaxogjs1234"}})
  http.get('/dashboard/record', {params: {email: data}})
  .then((res) => callback(res))
  .catch((err) => errorCallback(err));
};

const DashboardApi = {

  GetDashboard: (data, callback, errorCallback) => GetDashboard(data, callback, errorCallback),

};

export default DashboardApi;