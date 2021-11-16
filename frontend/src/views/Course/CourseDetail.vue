//코스1 1246
//런지1 덤벨컬2 숄더프레스4 팔벌려6
<template>
    <div class="course-content-main">
      <div class="course-vedio">
            <CourEx1 v-if="exList[index].exercise_image[0].exercise_idx==1" v-on:Index="addIndex"></CourEx1>
            <CourEx2 v-if="exList[index].exercise_image[0].exercise_idx==2" v-on:Index="addIndex"></CourEx2>  
            <CourEx3 v-if="exList[index].exercise_image[0].exercise_idx==3" v-on:Index="addIndex"></CourEx3>
            <CourEx4 v-if="exList[index].exercise_image[0].coursename!='코스3' && exList[index].exercise_image[0].exercise_idx==4" v-on:Index="addIndex"></CourEx4>
            <CourEx4test v-if="exList[index].exercise_image[0].coursename=='코스3' && exList[index].exercise_image[0].exercise_idx==4" v-on:Index="addIndex"></CourEx4test>
            <CourEx5 v-if="exList[index].exercise_image[0].coursename!='코스3' && exList[index].exercise_image[0].exercise_idx==5" v-on:Index="addIndex"></CourEx5>
            <CourEx5test v-if="exList[index].exercise_image[0].coursename=='코스3' && exList[index].exercise_image[0].exercise_idx==5" v-on:Index="addIndex"></CourEx5test>
            <CourEx6 v-if="exList[index].exercise_image[0].exercise_idx==6" v-on:Index="addIndex"></CourEx6>
            <CourEx7 v-if="exList[index].exercise_image[0].exercise_idx==7" v-on:Index="addIndex"></CourEx7>  
      </div>
      <div class="course-content-step">        
        <CourStep v-for="(item,i) in exList" v-bind:newstep="index+1" v-bind:num="exList[i].idx" 
                          v-bind:name="exList[i].exercise_image[0].exercise_name" v-bind:key="i"></CourStep>
      </div>
        <CourDesc v-bind:newstep="index"></CourDesc>
    </div>
</template>

<script>
import CourDesc from '@/components/Detail/CourDesc.vue'
import CourStep from '@/components/Detail/CourStep.vue'
import CourEx2 from '@/components/CourVedio/CourEx2.vue'
import CourEx6 from '@/components/CourVedio/CourEx6.vue'
import CourEx4 from '@/components/CourVedio/CourEx4.vue'
import CourEx4test from '@/components/CourVedio/CourEx4test.vue'
import CourEx1 from '@/components/CourVedio/CourEx1.vue'
import CourEx3 from '@/components/CourVedio/CourEx3.vue'
import CourEx5 from '@/components/CourVedio/CourEx5.vue'
import CourEx5test from '@/components/CourVedio/CourEx5test.vue'
import CourEx7 from '@/components/CourVedio/CourEx7.vue'
export default {
  name: 'Detail',
  components:{
    CourEx2:CourEx2,
    CourEx1:CourEx1,
    CourEx6:CourEx6,
    CourEx4:CourEx4,
    CourEx4test:CourEx4test,   
    CourEx3:CourEx3,
    CourEx5:CourEx5,
    CourEx5test:CourEx5test,            
    CourEx7:CourEx7, 
    //Clock,
    //apexchart:VueApexCharts,
    CourDesc:CourDesc,
    CourStep: CourStep,

  },
  data(){
    return{
      id: 0,
      msg:"시작",
      newstep:1,
      //desc_step:1,
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
      index:0,
  }
    
  },  
  methods: {
    start(){
      if(this.msg=="시작"){
        this.msg="완료";
        this.$refs.clock.clockStart()
      } 
      else{
      this.msg="시작";
      this.$refs.clock.clockStop()
      } 
    },
    changeEx(step){
      this.newstep=step;
      console.log("step 넘어옴 :" +step);
    },
    upSet(set){
      this.set = set;
    },    
    upCount(count){
      this.count = count;
    },
    showGraph(acc){
      console.log("그래프 값"+acc);
      this.chart.series = acc;
    },
    addIndex(){
      this.index++;
      if(this.index==this.exList.length){
        this.$router.push(`/course/res/${this.$route.params.id}`);
      }
    },    
  },
  created() {
    console.log(this.exList.length)
  },
  computed: {
    exList() {
      return this.$store.getters.getExerciseList;
    },
  },


}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.course-content-main {
  height: 38vw;
  width: 165vh;
  border-radius: 30px;
  background-color: $bg-color;
}
.course-vedio {
  width: 72%;
  height: 64%;  
  top: 10%;
  left: 20%;
  position: absolute;
  // border: solid black;
  margin: 10px;
}
img {
    top: 44%;
    width: 20%;
    height: 42%;  
    left: 50%;
    position: absolute;
    //border: solid black;
    margin: 10px;
}
.clock {
  top: 16%;
  right: 35%;
  position: absolute;  
}
.course-title {
  top: 26%;
  position: absolute;  
  font-size: 30px;  
}
.set-count {
  top: 16%;
  right: 19%;
  width: 150px;
  position: absolute;  
  font-size: 24px;
  text-align: center;  
}
#chart {
  top: 100%;
  position: absolute;
}
.clear-btn{
  top: 87%;
  height: 40px;
  width: 100px;
  right: 20%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $logo-color;
  text-align: center;
}
.clear{
  padding-top: 8px;
}
.course-content-step {
  top: 52%;
  right: 20%;
  position: absolute;
}
</style>