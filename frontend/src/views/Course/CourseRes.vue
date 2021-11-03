// 코스 운동 결과 페이지
<template>
  <div class='contents'>
    <div><h1>오늘의 운동 결과</h1></div>
    <!-- <div id="comment">{{exercise}} 자세 이상! </div> -->
    <div id="comment">
      <p v-for="course in courseListFilter" :key="course.coursecode">
        {{course.exercise_name}} 자세 이상!
      </p>
      <!-- 아직 perfect부분 해결이 안됨, 언제 값을 변경해줄 수 있지 -->
      <p v-if="perfect"> 잘 하고 있어요! </p> 
      <p v-else> 조금 더 노력해보아요! </p>
    </div>
    <div class="results">
      <course-res-item v-for="course in courseList"
      :key="course.coursecode" :course="course"/>
    </div>
    <button id="okBtn" @click="toHome">확인</button>
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
      excercise: '스쿼트',
      perfect: true,
      courseList: [{coursecode:1, exercise_name:'squat', accuracy: 88},
      {coursecode:2, exercise_name:'lunge', accuracy: 70 }],
      
    }
  },
  methods: {
    toHome(){
      this.$router.push('/');
    },
  },
  computed: {
    courseListFilter: function() {
      return this.courseList.filter(function (course) {
        // if(course.accuracy < 80) this.perfect = false
        return course.accuracy < 80
      })
    },
  }
}
</script>

<style lang="scss" scoped>
@import '@/styles/common.scss';

* {
  // font-weight: bold;
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
  margin: 15px;
  background: white;
  border-radius: 20px;
}

.results {
  display: flex;
  padding: 40px;
  justify-content: center;
  
}

#okBtn {
  background: $light-color;
  border: none;
  border-radius: 10px;
  padding: 10px 15px;
  font-family: 'SBAggroL';
}
</style>