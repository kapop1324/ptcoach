// 코스 운동 페이지
// 각 운동별 횟수: ?회
// '시작'버튼 누르면 시계start, '완료' 누르면 시계end
// 운동 넘어갈때 마다 store에 시간 저장하고 초기화 
<template>
    <div class="course-content-main">
      <div class="course-vedio">
      </div>
      <div class="clock">
        <Clock ref="clock"/>
      </div>
      <CourDesc v-if="desc_step==newstep" v-bind:path="cr_list[desc_step-1].path" v-bind:desc="cr_list[desc_step-1].desc"></CourDesc>
      <div class="set-count">
        <div class="cont">0세트 0회</div>
        <!-- <div class="cont">{{set}}0세트  {{count}}0회</div> -->
        <div id="chart">
        <apexchart type="radialBar" height="150" :options="chart.chartOptions" :series="chart.series"></apexchart>
        </div>    
      </div>   
      <div class="course-content-step">        
        <CourStep v-for="(item,i) in cr_list" v-bind:newstep="newstep" v-bind:num="cr_list[i].step" v-bind:key="i"></CourStep>
      </div>
      <!-- 시작 버튼 누르면 시계 시작 -->
      <div class="clear-btn">
        <div class="clear" v-on:click="start">{{msg}}</div>
      </div>  

    </div>
</template>

<script>
import Clock from '../../components/Detail/Clock.vue'
import VueApexCharts from 'vue-apexcharts'
import CourDesc from '@/components/Detail/CourDesc.vue'
import CourStep from '@/components/Detail/CourStep.vue'
export default {
  name: 'Detail',
  components:{
    Clock,
    apexchart:VueApexCharts,
    CourDesc:CourDesc,
    CourStep: CourStep,
  },
  data(){
    return{
      id: 0,
      msg:"",
      newstep:1,
      desc_step:1,
      chart: {
        series: [70],
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
    cr_list:[
        {
          course_name:"코스1",
          exercise_name:"팔벌려높이뛰기",
          exercise_idx:1,
          set:2,
          step:1,
          path:"",
        }   
      ]
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
    
  },

}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.course-content-main {
  height: 38vw;
  width: 165vh;
  border-radius: 30px;
  background-color: $light-color;
}
.course-vedio {
  width: 34%;
  height: 64%;  
  top: 26%;
  left: 13%;
  position: absolute;
  border: solid black;
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
  top: 28%;
  right: 35%;
  position: absolute;  
}
.course-title {
  top: 26%;
  position: absolute;  
  font-size: 30px;  
}
.set-count {
  top: 26%;
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
  top: 78%;
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
  top: 48%;
  right: 18%;
  position: absolute;
}
</style>