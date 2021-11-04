import http from '@/util/http-common';

const SaveRecord = (data, callback, errorCallback) => {
    
    http.post('/record/save',data)
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const GetCourseList = (callback, errorCallback) => {
    
    http.get('/course/list')
    .then((res) => callback(res))
    .catch((err) => errorCallback(err));
};

const CourseApi = {

    SaveRecord: (data, callback, errorCallback) => SaveRecord(data, callback, errorCallback),
    GetCourseList: (callback, errorCallback) => GetCourseList(callback, errorCallback),

};

export default CourseApi;