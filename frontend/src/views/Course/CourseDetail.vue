// 코스 운동 페이지
<template>
    <div class="course-content-main">
      <div class="course-title">코스1 /</div>  
      <div class="title">스쿼트</div>        
      <div class="course-vedio">
      <Vedio/>
      </div>
      <div class="clock">
      <Clock/>
      </div>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="course-content-step">
        <Step v-for="(item,i) in items" v-bind:num="items[i]" v-bind:key="i"></Step>
      </div>
      <div class="clear"> 완료 </div>
    </div>
</template>

<script>
import Vedio from '@/components/Detail/Vedio.vue'
import Step from '@/components/Detail/Step.vue'
import Clock from '../../components/Detail/Clock.vue'
export default {
  name: 'Detail',
  components:{
    Vedio,
    Step: Step,
    Clock,
  },
  data(){
    return{
      items:['01','02','03']
    }
  }

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
.course-title {
  top: 14%;
  left: 28%;
  position: absolute;
  font-size: 38px;
}
.title {
  top: 14.5%;
  left: 37%;
  position: absolute;
  font-size: 32px;
  color:gray;
}
.course-vedio {
  width: 32%;
  height: 64%;  
  top: 22%;
  left: 28%;
  position: absolute;
  border: solid black;
  margin: 10px;
}
.clock {
  top: 22%;
  right: 20%;
  position: absolute;  
}
.clear {
  top: 75%;
  height: 40px;
  width: 100px;
  right: 20%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $orange-color;
  text-align: center;
}
.course-content-step {
  top: 42%;
  right: 18%;
  position: absolute;
}
</style>