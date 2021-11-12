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
import CourseApi from "../../api/CourseApi"

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
    this.$router.reload()
    this.getCourseList()
  },
  methods: {
    getCourseList() {
      CourseApi.GetCourseList(
        res => {
          this.courseList = res.data.courseList
          this.$store.dispatch("getCourseList", res.data.courseList);
        },
        error => {
          console.log(error)
          console.log('운동코스 에러')
        }
      )
    },
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