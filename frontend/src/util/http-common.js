import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'https://k5c201.p.ssafy.io/api', //백엔드 포트번호
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin' : '*',
    'Access-Control-Allow-Methods' : 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
    "Access-Control-Allow-Headers": "Origin, Content-Type",

  },
});
