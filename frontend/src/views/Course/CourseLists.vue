// 코스 선택 페이지
<template>
  <div>
    <div class="title">
      우리집 PT 선생의<br>추천 코스 운동
    </div>
    <div class="cos-div" v-for="(course, idx) in courseList" :key="idx">
      <CourseListItem :courseInfo="course" />
    </div>
  </div>
</template>

<script>
import CourseListItem from "@/components/CourseListItem.vue"

export default {
  name: 'CourseLists',
  components: {
    CourseListItem,
  },
  data (){
    return {
      courseList: [
        {id:1, name: '코스 1', course: ['운동1', '운동2', '운동3', '운동4']},
        {id:2, name: '코스 2', course: ['운동1', '운동2', '운동3', '운동4']},
        {id:3, name: '코스 3', course: ['운동1', '운동2', '운동3', '운동4']},
        {id:4, name: '코스 4', course: ['운동1', '운동2', '운동3', '운동4']},
      ],
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.title {
  margin-top: 30px;
  margin-bottom: 50px;
  font-size: 1.7rem;
  line-height: 40px;
}
.cos-div {
  margin-top: 20px;
  margin-bottom: 20px;
}

</style>