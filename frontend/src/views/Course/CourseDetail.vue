// 코스 운동 페이지
// 각 운동별 횟수: ?회
// '시작'버튼 누르면 시계start, '완료' 누르면 시계end
<template>
    <div class="course-content-main">
      <div class="course-vedio">
      <Vedio/>
      </div>
      <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지">
      <div class="clock">
      <Clock ref="clock"/>
      </div>
      <div class="comment"> 멀리서 측면으로 서주세요</div>
      <!-- <div class="course-title">
        <div>{{course}}코스1</div>
      </div> -->
      <!-- 운동별로 정해진 반복 세트(set)와 횟수(count) 들어옴 -->
      <div class="set-count">
        <div class="cont">{{set}}0세트  {{count}}0회</div>
        <div id="chart">
        <apexchart type="radialBar" height="150" :options="chart.chartOptions" :series="chart.series"></apexchart>
        </div>    
      </div>   

      <div class="course-content-step">        
        <Exe v-for="(item,i) in items" v-bind:num="i" v-bind:key="i"></Exe>
      </div>
      <!-- 시작 버튼 누르면 시계 시작 -->
      <div class="clear-btn">
        <div class="clear" v-on:click="start">{{msg}}</div>
      </div>  
    </div>
</template>

<script>
import Vedio from '@/components/Detail/Vedio.vue'
import Exe from '@/components/Detail/Exe.vue'
import Clock from '../../components/Detail/Clock.vue'
import VueApexCharts from 'vue-apexcharts'
export default {
  name: 'Detail',
  components:{
    Vedio,
    Exe: Exe,
    Clock,
    apexchart:VueApexCharts,
  },
  data(){
    return{
      items:['1','2','3'],
      msg:"시작",
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
    }
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
.comment {
  top: 18%;
  left: 40%;
  position: absolute;  
  font-size: 24px;
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
  right: 20%;
  position: absolute;  
  font-size: 30px;  
}
.set-count {
  top: 26%;
  right: 21%;
  width: 150px;
  position: absolute;  
  font-size: 24px;
  text-align: right;  
}
#chart {
  top: 100%;
  left: 40%;
  position: absolute;
  margin: 4px;  
}
.clear-btn{
  top: 78%;
  height: 40px;
  width: 100px;
  right: 20%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $orange-color;
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