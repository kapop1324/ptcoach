// 코스 운동 페이지
// 각 운동별 횟수: ?회
// '시작'버튼 누르면 시계start, '완료' 누르면 시계end
// 운동 넘어갈때 마다 store에 시간 저장하고 초기화 
//코스1 6241
<template>
    <div class="course-content-main">
      <div class="course-vedio">
        <!-- exList의 idx에 해당하는 컴포넌트 실행 -->
        <div v-for="(item,i) in exList.length" v-bind:key="i">
            <CourEx2 v-if="exList[i].idx==1" 
              v-on:Count="upCount" v-on:Set="upSet" v-on:Graph="showGraph"></CourEx2>
            <CourEx3 v-if="exList[i].idx==6" ></CourEx3>  
          <!-- <div v-if="exList[i].idx==6">
            <CourEx6 v-on:sendStep="updateStep"></CourEx6>
          </div>
          <div v-if="exList[i].idx==2">
            <CourEx2 v-on:sendStep="updateStep"></CourEx2>
          </div> -->
          </div>
      </div>
          <div class="course-content-step">        
            <CourStep v-for="(item,i) in exList.length" v-bind:newstep="newstep" v-bind:num="exList[i].idx" v-bind:key="i"></CourStep>
          </div>
      <!-- <div class="clock">
        <Clock ref="clock"/>
      </div> -->
      <CourDesc v-if="desc_step==newstep"></CourDesc>
      
      <div class="set-count">
        <div class="cont">{{set}}세트 {{count}}회</div>
        <div id="chart">
        <apexchart type="radialBar" height="150" :options="chart.chartOptions" :series="chart.series"></apexchart>
        </div>    
      </div>   


      <!-- <div class="clear-btn">
        <div class="clear" v-on:click="start">{{msg}}</div>
      </div>   -->

    </div>
</template>

<script>
//import Clock from '../../components/Detail/Clock.vue'
import VueApexCharts from 'vue-apexcharts'
import CourDesc from '@/components/Detail/CourDesc.vue'
import CourStep from '@/components/Detail/CourStep.vue'
import CourEx2 from '@/components/CourVedio/CourEx2.vue'
import CourEx3 from '@/components/CourVedio/CourEx3.vue'
export default {
  name: 'Detail',
  components:{
    CourEx2:CourEx2,
    CourEx3:CourEx3,
    //Clock,
    apexchart:VueApexCharts,
    CourDesc:CourDesc,
    CourStep: CourStep,

  },
  data(){
    return{
      id: 0,
      msg:"시작",
      newstep:1,
      desc_step:1,
      set:0,
      count:0,
      chart: {
        series: [0],
        chartOptions: {
        chart: {
          height: 150,
          type: 'radialBar',
        },
        plotOptions: {
          radialBar: {
              hollow: {
              size: '70%',
            }
          },
        },
        labels: ['count'],
        },      
      },
      // ex_list:[
      // {
      //   idx: 0,
      //   exercise_image:{
      //     "coursename": "",
      //     "path": "",
      //     "image_step": 0,
      //     "desc": "",
      //     "exercise_idx": 0,
      //     "exercise_name": ""
      //   },
      // }]
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
  },
  created() {
    console.log(this.exList)
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
  top: 42%;
  right: 18%;
  position: absolute;
}
</style>