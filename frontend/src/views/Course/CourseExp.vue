// 코스운동설명
<template>
  <div class="contents">
      <div class="title"><h1>{{exerciseList[step].exercise_image[0].exercise_name}}</h1></div>        
      <div class="imageDiv"><img :src="exerciseList[step].exercise_image[0].path" alt="샘플이미지"/></div>
      <div class="Desc" v-for="(exercise,i) in exerciseFilter" :key="i">
        <p v-for="(exercise,j) in exercise.exercise_image" :key="j">
          {{exercise.desc}}
        </p>
      </div>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="course-content-step">
        <Seq @change-step= 'changeStep' v-for="(exercise,i) in exerciseList" :num="i+1" :selected="step+1" :key="i"></Seq>
      </div>
      <!-- 시작 버튼 누르면 courseDetail페이지로 -->
      <div class="start-btn">
        <div class="start" @click="toCourse"> 시작하기 </div>
      </div>  
    </div>
</template>

<script>
import Seq from '@/components/Detail/Seq.vue'
import CourseApi from '@/api/CourseApi'

export default {
  name: 'CourseExp',
  components:{
    Seq: Seq,
  },
  data(){
    return {
      course_idx: '',
      step: 0,
      coursenameList: ["코스1","코스2"],
      courseList: [],
      exerciseList: [
        {
          idx:'', 
          exercise_image:[
            {
              coursename: "",
              path: "",
              image_step: 0,
              desc: "",
              exercise_idx: 0,
              exercise_name: "",
            }
          ],
        }
      ],
    }
  },
  created() {
    const course_idx = this.$route.params.id
    //courseList에서 id-1인 course의 coursename을 보내줘서 get해오면 됨
    this.course_idx = course_idx
    console.log(this.coursenameList[this.course_idx-1])
    CourseApi.FetchExerciseList(
      this.coursenameList[this.course_idx-1],
      res => {
        if(res.data.message == "success"){
          console.log(this.exerciseList);
          this.exerciseList = res.data.exerciseList;
          this.$store.dispatch("fetch_exercise_list", res.data.exerciseList);
        }else if(res.data.message == "fail"){
          alert("운동 정보 로드 실패");
        }else{
          alert("운동 정보 로드 실패");
        }
      },
      error => {
        alert("에러발생");
        console.log(error);
      }
    )
  },
  methods: {
    toCourse(){
      this.$router.push(`/course/${this.$route.params.id}`);
    },
    changeStep(i) {
      this.step = i-1;
    },
  },
  computed: {
    exerciseFilter: function() {
      return this.exerciseList.filter((exercise) => {
        return exercise.idx === (this.step+1);
      });
    },
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
  width: 40%;
  height: 30%;
  display: inline-block;  
}

.imageDiv img {
  width: 200px;
  height: 400px;
}

.course-content-step {
  top: 24%;
  right: 12%;
  position: absolute;
}

.start-btn{
  bottom: 5%;
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