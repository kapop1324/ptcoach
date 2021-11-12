import http from '@/util/http-common';

const GetDashboard = (callback, errorCallback) => {
  // params: 유저 이메일로 변경할것
  // http.get('/dashboard/record', {params: {email: "rlaxogjs1234"}})
  http.get('/dashboard/record', {params: {email: "aihello"}})
  .then((res) => callback(res))
  .catch((err) => errorCallback(err));
};

const DashboardApi = {

  GetDashboard: (callback, errorCallback) => GetDashboard(callback, errorCallback),

};

export default DashboardApi;