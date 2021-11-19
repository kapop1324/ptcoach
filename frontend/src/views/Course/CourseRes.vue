// 코스 운동 결과 페이지
<template>
  <div class='contents'>
    <div><h1>오늘의 운동 결과</h1></div>
    <div id="comment">
      <p v-for="(exercise,i) in exerciseListFilter" :key="i">
        {{exercise.exercise_name}} 자세 이상!
      </p>
      <p v-if="perfect"> 잘 하고 있어요! </p> 
      <p v-else> 조금 더 노력해보아요! </p>
    </div>
    <div class="results">
      <course-res-item v-for="(exercise,i) in exerciseRecord"
      :key="i" :exercise="exercise" :num="i"/>
    </div>
    <button id="okBtn" @click="toCourseList">확인</button>
  </div>
</template>

<script>
import CourseResItem from '@/components/CourseResItem.vue'
import CourseApi from '@/api/CourseApi.js'

export default {
  name: 'CourseRes',
  components: {
    CourseResItem
  },
  data(){
    return {
      exerciseList: [{
          idx:'', 
          exercise_image:[
            {
              coursename: "",
              path: "",
              image_step: 0,
              desc: "",
              exercise_idx: 0,
              exercise_name: "",
            },
          ],
        }],
      exerciseRecord: [],
      exerciseListFilter: [],
    }
  },
  created() {
    this.exerciseList = this.$store.getters.getExerciseList;
    // console.log(this.exerciseList)
    this.exerciseRecord = this.$store.getters.getRecord;
    // console.log(this.exerciseList);
    this.setExerciseFilter();

    //email이 비어있으면 바로 홈으로, 아니면 post(저장) 후에 홈으로
    if(this.isLogin){
      let record = [];
      for(let i=0; i<this.exerciseRecord.length; i++){
        record.push({useremail: this.email, 
        courseidx:this.exerciseList[i].exercise_image[0].course_idx,
        time: this.exerciseRecord[i].time,
        accuracy:this.exerciseRecord[i].accuracy })
      }
      
      if(!(this.email == null || this.email == '')){
        CourseApi.SaveRecord(
          record,
          res => {
            if(res.data.message == "success"){
              alert("기록이 저장되었습니다.");
            }else{
              console.log(record)
              alert("기록 저장 실패");
            }
          }
        )
      }else{
        alert("기록저장은 로그인 후 가능합니다.")
      }
    }
  },
  methods: {
    toCourseList(){
      this.$router.push({name:'CourseLists'});
    },
    setExerciseFilter() {
      let record = [];
      console.log(this.exerciseRecord.length)
      console.log(this.exerciseRecord)
      if(this.exerciseRecord){
        for(let i=0; i<this.exerciseList.length; i++){
          if(this.exerciseRecord[i].accuracy < 80){
            record.push({
            course_idx: this.exerciseList[i].exercise_image[0].course_idx,
            exercise_name: this.exerciseList[i].exercise_image[0].exercise_name,
            accuracy: this.exerciseRecord[i].accuracy
          });
          }
        }
      }
      this.exerciseListFilter = record;
    }
  },
  computed: {
    email() {
      return this.$store.getters.getLoginEmail;
    },
    isLogin(){
      return this.$store.getters.getIsLogin;
    },
    perfect() {
      let flag = true;
      for(let i=0; i<this.exerciseRecord.length; i++){
        if(this.exerciseRecord[i].accuracy < 80){
          flag = false;
          break;
        }
      }
      return flag
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
  cursor: pointer;
  font-family: 'SBAggroL';
}
</style>