// 코스 운동 페이지
<template>
    <div class="course-content-main">
      <!-- 의문점.. list형식으로 step1과 사진을 받아오는 걸까..? -->
      <div class="course-vedio">
      <Vedio/>
      </div>
      <div>
        
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
export default {
  name: 'Detail',
  components:{
    Vedio,
    Step: Step,
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
.course-vedio {
  width: 32%;
  height: 64%;  
  top: 22%;
  left: 28%;
  position: absolute;
  border: solid black;
  margin: 10px;
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