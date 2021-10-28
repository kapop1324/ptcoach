// 자세코칭 페이지(운동 사진 고유번호, step개수, step별 이미지, 정확도 수치)
<template>
    <div class="pos-content-main">
      <div class="title">{{$route.query.name}}나무자세</div> 
      <!-- 의문점.. list형식으로 step1과 사진을 받아오는 걸까..? -->
      <Vedio/>
      <Description/>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="pos-content-step">
        <Step v-for="(item,i) in items" v-bind:num="items[i]" v-bind:key="i"></Step>
      </div>
      <div class="clear"> CLEAR </div>
      <Res/>
    </div>
</template>

<script>
import Vedio from '@/components/PostureDetail/Vedio.vue'
import Description from '@/components/PostureDetail/Description.vue'
import Step from '@/components/PostureDetail/Step.vue'
import Res from '@/components/PostureDetail/Result.vue'
export default {
  name: 'PostureDetail',
  components:{
    Vedio,
    Description,
    Step: Step,
    Res,
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

.pos-content-main {
  height: 38vw;
  width: 165vh;
  border-radius: 30px;
  background-color: $light-color;
}
.title {
  top: 14%;
  left: 12%;
  position: absolute;
  font-size: 38px;
  font-weight: bold;
}
.clear {
  top: 75%;
  height: 40px;
  width: 100px;
  right: 10%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $orange-color;
  text-align: center;
}
.pos-content-step {
  top: 22%;
  right: 8%;
  position: absolute;
}
</style>