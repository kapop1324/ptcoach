import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/views/Home.vue'

import CourseExp from '@/views/Course/CourseExp.vue'
import CourseLists from '@/views/Course/CourseLists.vue'
import CourseDetail from '@/views/Course/CourseDetail.vue'
import CourseRes from '@/views/Course/CourseRes.vue'
import Dashboard from '@/views/Mypage/Dashboard.vue'
import Mypage from '@/views/Mypage/Mypage.vue'
import PostureLists from '@/views/Posture/PostureLists.vue'
import PostureDetail from '@/views/Posture/PostureDetail.vue'
import Login from '@/views/User/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },

  {
    path: '/course',
    name: 'CourseLists',
    component: CourseLists,
  },
  {
    path: '/course/exp/:id',
    name: 'CourseExp',
    component: CourseExp,
  },
  {
    path: '/course/:id',
    name: 'CourseDetail',
    component: CourseDetail,
  },
  {
    path: '/course/res/:id',
    name: 'CourseRes',
    component: CourseRes,
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: Dashboard,
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
  },
  {
    path: '/posture',
    name: 'PostureLists',
    component: PostureLists,
  },
  {
    path: '/posture/:id',
    name: 'PostureDetail',
    component: PostureDetail,
  },
  {
    path: '/user/login',
    name: 'Login',
    component: Login,
  },  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
