// 코스운동설명
<template>
  <div class="contents">
      <div class="title"><h1>{{exerciseList[step].exercise_name}}</h1></div>        
      <div class="imageDiv"><img src='@/assets/squat_sample_remove.png' alt="스쿼트 샘플이미지"/></div>
      <div class="Desc">
        <p v-for="image in exerciseList[step].exercise_image" :key="image.idx">
          {{image.desc}}
        </p>
      </div>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="course-content-step">
        <Exe @click= 'changeStep' v-for="(exercise,i) in exerciseList" v-bind:num="i+1" v-bind:key="exercise.idx"></Exe>
      </div>
      <!-- 시작 버튼 누르면 courseDetail페이지로 -->
      <div class="start-btn">
        <div class="start" @click="toCourse"> 시작하기 </div>
      </div>  
    </div>
</template>

<script>
import Exe from '@/components/Detail/Exe.vue'

export default {
  name: 'CourseExp',
  components:{
    Exe: Exe,
  },
  data(){
    return {
      step: 0,
      src(){
      //return `http://i5c102.p.ssafy.io/api/feed/${this.feed.images[0].newname}`
      return this.exerciseList[this.step].exercise_image.path;
      },
      exerciseList: [{idx:1, course_name:'코스 1', step:'1', exercise_name:'lunge', 
                      exercise_image:[{idx:0, path:"http://k5c201.p.ssafy.io/image/thumbnail/lunge.png", step:"1", desc:"1. 어깨 넓이로 발을 벌리고 양 팔은 몸에 가볍게 붙인다."}, 
                      {idx:1, path:"@/assets/squat_sample_remove.png", step:"2", desc:"2. 숨을 들이쉬면서 무릎을 굽혀 허리를 내린다."}]},
      {idx:2, course_name:'코스 2', step:'2', exercise_name:'lunge', 
                      exercise_image:[{idx:0, path:"", step:"1", desc:"1. 어깨 넓이로 발을 벌리고 양 팔은 몸에 가볍게 붙인다."}, 
                      {idx:1, path:"", step:"2", desc:"2. 숨을 들이쉬면서 무릎을 굽혀 허리를 내린다."}]},
      // desc: `1. 어깨 넓이로 발을 벌리고 양 팔은 몸에 가볍게 붙인다.
      //       2. 숨을 들이쉬면서 무릎을 굽혀 허리를 내린다.
      //       3. 숨을 뱉으며 원래 자세로 돌아온다.`
      ],
    }
  },
  methods: {
    toCourse(){
      this.$router.push('/course/1');
    },
    changeStep(){
      // this.step = i;
    },
  },
  computed: {
    // src(){
    //   //return `http://i5c102.p.ssafy.io/api/feed/${this.feed.images[0].newname}`
    //   return this.exerciseList[this.step].exercise_image.path;
    // },
  },

}
</script>

<style lang="scss" scoped>
@import '@/styles/common.scss';

h1 {
  font-size: 32px;
}

.contents {
  // width: 100%;
  text-align: center;
  padding: 20px;
  margin: 15px;
}

.imageDiv {
  width: 30%;
  height: 40%;
  display: inline-block;  
  // top: 24%;
  // left: 13%;
  // position: absolute;

}

.course-content-step {
  top: 24%;
  right: 12%;
  position: absolute;
}

.start-btn{
  top: 75%;
  height: 40px;
  width: 120px;
  right: 12%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $logo-color;
  text-align: center;
  cursor: pointer;
  color: white;
}

.start {
  padding: 8px;
}
</style>