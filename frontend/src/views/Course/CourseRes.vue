// 코스 운동 결과 페이지
<template>
  <div class='contents'>
    <div><h1>오늘의 운동 결과</h1></div>
    <div id="comment">멘트</div>
    <div class="results">
      <course-res-item v-for="course in courseList"
      :key="course.coursecode"/>
    </div>
  </div>
</template>

<script>
import CourseResItem from '@/components/CourseResItem.vue'

export default {
  name: 'CourseRes',
  components: {
    CourseResItem
  },
  data(){
    return {
      courseList: [{coursecode:1, coursename:'squat'},
      {coursecode:2, coursename:'lunge'}],
    }
  },
    CourseResItem

}
</script>

<style lang="scss" scoped>
@import '@/styles/common.scss';

* {
  font-weight: bold;
  font-size: 20px;
}

h1 {
  font-size: 32px;
}

.contents {
  width: 100%;
  text-align: center;
  padding: 20px;
  margin: 15px;
}

#comment {
  /* width: calc(100%-50%); */
  display: inline-block;
  width: 40%;
  height: 120px;
  padding: 50px;
  margin: 20px;
  background: $light-color;
  border-radius: 20px;
}

.results {
  display: flex;
  padding: 60px;
  justify-content: center;
  
}
</style>