<template>
    <div class="pos-content-main" >
      <div class="title">{{ex_list[0].exercisename}}</div> 
      <div class="pos-vedio">
      <div v-if="ex_list[0].idx==2">
        <Ex2 v-on:sendStep="updateStep"></Ex2>
      </div>
      </div>
      <Description v-if="desc_step==newstep" v-bind:path="ex_list[desc_step-1].path" v-bind:desc="ex_list[desc_step-1].desc"></Description>
      <div class="pos-content-step" >
        <Step v-for="(item,i) in ex_list" v-bind:newstep="newstep" v-bind:num="ex_list[i].step" v-bind:key="i"></Step>
      </div>
    </div>
</template>

<script>
import PostureApi from "../../api/PostureApi"
import Ex2 from '@/components/Vedio/Ex2.vue'
import Description from '@/components/Detail/Description.vue'
import Step from '@/components/Detail/Step.vue'
export default {
  name: 'PostureDetail',
  components:{
    Ex2: Ex2,
    Description:Description,
    Step: Step,
  },
  data(){
    return{
      id: 0,
      newstep:1,
      desc_step:1,
      ex_list:[
      ]
    }
  },
    created() {
        let data = {
            id:this.$route.params.id,
        };

        PostureApi.PostureDetail(
            data,
            res => {
            if(res.data.message == "success"){
              this.ex_list=res.data.exercise_list;
              console.log(this.ex_list);
            }else if(res.data.message == "fail"){
                alert("정보불러오기 실패");
            }else{
                alert("정보불러오기 실패");
            }
            },
            error => {
                alert("에러발생");
                console.log(error);
            }
        );
    },  
  
  
  methods: {
    updateStep(step){
      this.newstep=step;
      this.desc_step=step;
      console.log("step 넘어옴 :" +step);
    },    
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