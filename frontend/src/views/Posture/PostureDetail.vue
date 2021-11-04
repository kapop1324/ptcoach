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
      <div v-if="id==2">
        <!-- <div v-for="(item,i) in detail"  v-bind:key="i">
        <Ex1 v-bind:step="detail[i].step"></Ex1>
        </div> -->
        <Ex2 v-on:sendStep="updateStep"></Ex2>
      </div>
      
      </div>
      <!-- <Description v-for="(item,i) in detail" v-bind:path="i.path" v-bind:desc="i.desc" v-bind:key="i"></Description> -->
      <div class="pos-content-step" >
        <!-- Ex1으로 부터 stat_num받아서 넘겨주기 -->
        <Step v-for="(item,i) in detail" v-bind:newstep="newstep" v-bind:num="detail[i].step" v-bind:key="i"></Step>
      </div>

      <!-- <Res/> -->
    </div>
</template>

<script>
import Ex2 from '@/components/Vedio/Ex2.vue'
// import Description from '@/components/Detail/Description.vue'
import Step from '@/components/Detail/Step.vue'
export default {
  name: 'Detail',
  components:{
    Ex2: Ex2,
    // Description,
    Step: Step,
  },
  data(){
    return{
      id:this.$route.params.id,
      detail:[{path:"", desc:"", step:1},{path:"", desc:"", step:2}],
      newstep:1,
    }
  },
  methods: {
    updateStep(step){
      this.newstep=step;
      console.log("step 넘어옴 :" +step);
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
  width: 72%;
  height: 64%;  
  top: 24%;
  left: 13%;
  position: absolute;
  // border: solid black;
  margin: 10px;
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