// 자세코칭 페이지(운동고유번호, 사진경로,단계,설명, 정확도)
//postureDetail:
// {idx:1(운동고유번호), name(운동명):"", detail:[{path:"", desc:"", step:1}
// 															,{path:"", desc:"", step:2}
// 															,{path:"", desc:"", step:3}]}
<template>
    <div class="pos-content-main" >
      <div class="title">운동1</div> 
      <div class="pos-vedio">
      <!-- step번호를 넘기면 이step에 해당하는 정확도만 가져오기         -->
      <div v-if="id==1">
        <div v-for="(item,i) in detail"  v-bind:key="i">
        <Ex1 v-bind:step="detail[i].step"></Ex1>
        </div>
      </div>
      
      </div>
      <Description v-for="(item,i) in detail" v-bind:path="i.path" v-bind:desc="i.desc" v-bind:key="i"></Description>
      <!-- step 개수에 따라서 component 만들기 -->
      <div class="pos-content-step">
        <Step v-for="(item,i) in detail" v-bind:num="i.step" v-bind:key="i"></Step>
      </div>
      <!-- <div class="result">{{acc}}% 일치합니다</div>   -->
      <div class="clear-btn">
        <div class="clear"> 완료 </div>
      </div>  
      <!-- <Res/> -->
    </div>
</template>

<script>
import Ex1 from '@/components/Vedio/Ex1.vue'
import Description from '@/components/Detail/Description.vue'
import Step from '@/components/Detail/Step.vue'
export default {
  name: 'Detail',
  components:{
    Ex1: Ex1,
    Description,
    Step: Step,
  },
  data(){
    return{
      id:this.$route.params.id,
      detail:[{path:"", desc:"", step:1},{path:"", desc:"", step:2},{path:"", desc:"", step:3}]
    }
  },

}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.pos-content-main {
  height: 38vw;
  width: 165vh;
  border-radius: 30px;
  background-color: $white-color;
}
.title {
  top: 16%;
  left: 14%;
  position: absolute;
  font-size: 38px;
  font-weight: bold;
}
.pos-vedio {
  width: 34%;
  height: 64%;  
  top: 24%;
  left: 13%;
  position: absolute;
  // border: solid black;
  margin: 10px;
}
.clear-btn {
  top: 75%;
  height: 40px;
  width: 100px;
  right: 16%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $logo-color;
  text-align: center;
}
.clear{
  padding-top: 8px;
}
.pos-content-step {
  top: 24%;
  right: 14%;
  position: absolute;
}
.result {
    position:absolute;
    top: 94%;
    left: 45%;
    font-size: 20px;
    color: $logo-color;
    font-weight: bold;
}
</style>