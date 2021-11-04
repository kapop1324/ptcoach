// 코스 선택 페이지
<template>
  <div id="courselist">
    <div class="title">
      우리집 PT 코치의<br>추천 코스 운동
    </div>
    <div class="cos-div" v-for="(course, idx) in courseList" :key="idx">
      <CourseListItem :courseInfo="course" />
    </div>
  </div>
</template>

<script>
import CourseListItem from "@/components/CourseListItem.vue"
// import CourseApi from "../../api/CourseApi"
import http from '@/util/http-common';

export default {
  name: 'CourseLists',
  components: {
    CourseListItem,
  },
  data (){
    return {
      courseList: []
    }
  },
  created() {
   this.getCourseList()
  },
  methods: {
    // store 접근 에러남
    // getCourseList() {
    //   CourseApi.GetCourseList(
    //     res => {
    //       console.log(res.data)
    //       this.courseList = res.data.courseList
    //       // this.$store.state.courseList = res.data.courseList
    //       this.$store.dispatch("getCourseList", res.data.courseList);
    //     },
    //     error => {
    //       console.log(error)
    //       console.log('에러')
    //     }
    //   )
    // },
    getCourseList() {
      http.get('/course/list')
      .then(res => {
        // console.log(res.data)
        this.courseList = res.data.courseList
      })
      .catch(err => {
        console.log(err)
      })
    }


  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

#courselist {
  padding-bottom: 10%;
}
.title {
  margin-top: 30px;
  margin-bottom: 70px;
  font-size: 1.7rem;
  line-height: 40px;
}
.cos-div {
  margin-top: 40px;
  margin-bottom: 40px;
}

</style>