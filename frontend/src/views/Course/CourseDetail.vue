// 코스 운동 페이지
// 각 운동별 횟수: ?회
// 운동 설명 어디에서 보여줄 지 
// '시작'버튼 누르면 시계start, '완료' 누르면 시계end
<template>
    <div class="course-content-main">
      <div class="course-title">코스1 /</div>  
      <div class="title">운동1</div>        
      <div class="course-vedio">
      <Vedio/>
      </div>
      <div class="clock">
      <Clock/>
      </div>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="course-content-step">
        <Exe v-for="(item,i) in items" v-bind:num="items[i]" v-bind:key="i"></Exe>
      </div>
      <!-- 시작 버튼 누르면 시계 시작, 글씨 완료로 바뀜 -->
      <div class="clear-btn">
        <div class="clear"> 완료 </div>
      </div>  
    </div>
</template>

<script>
import Vedio from '@/components/Detail/Vedio.vue'
import Exe from '@/components/Detail/Exe.vue'
import Clock from '../../components/Detail/Clock.vue'
export default {
  name: 'Detail',
  components:{
    Vedio,
    Exe: Exe,
    Clock,
  },
  data(){
    return{
      items:['1','2','3']
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
.clear-btn{
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
.clear{
  padding-top: 8px;
}
.course-content-step {
  top: 42%;
  right: 18%;
  position: absolute;
}
</style>