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

export default {
  name: 'CourseLists',
  components: {
    CourseListItem,
  },
  data (){
    return {
      courseList: [
        {id:1, name: '코스 1', course: [{exercise:'운동1', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동2', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동3', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동4', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},]},
        {id:2, name: '코스 2', course: [{exercise:'운동1', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동2', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동3', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동4', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},]},
        {id:3, name: '코스 3', course: [{exercise:'운동1', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동2', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동3', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동4', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},]},
        {id:4, name: '코스 4', course: [{exercise:'운동1', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동2', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동3', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},{exercise:'운동4', path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},]},
      ],
    }
  },
  // created() {

  // },
  methods: {

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