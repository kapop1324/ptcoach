<template>
  <div class="about">
    <div class="vedio"><canvas id="canvas"></canvas></div>
    <div>
      <p class="speak">{{speak}}</p>
    <div class="result">{{acc}}% 일치</div> 
    </div>
    <div class="start-btn2">
      <div class="start2" @click="init()"> 시작 </div>
    </div> 
    <router-link to="/posture" class="clear-btn2">
      <div class="clear2"> 완료 </div>
    </router-link> 

  </div>
</template>


<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>

<script >
import * as tmPose from "@teachablemachine/pose";
import { mapState } from 'vuex'
import wait from "waait"
//사이드스텝

// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model provided by Teachable Machine export panel
const URL = "https://teachablemachine.withgoogle.com/models/ePqP3PVQL/";
let model, webcam, ctx, labelContainer, maxPredictions;

export default {
  data: () => {
    return {
      speak :"",
      acc:0,
      step:0,
      clear : false,
      send_step : false,
    };
  },
  // props:{
  //   step: Array,
  // },
  create(){
    console.log(this.stat)

    let data = {
      stat : this.$store.state.stat,
    };


  },

  methods: {
    
    async init() {

      for(var i = 5; i > 0; i--){
        this.speak = i+"초간 기다려주시기 바랍니다"
        await wait(1000);
      }

      const modelURL = URL + "model.json";
      const metadataURL = URL + "metadata.json";

      // load the model and metadata
      // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
      // Note: the pose library adds a tmPose object to your window (window.tmPose)
      model = await tmPose.load(modelURL, metadataURL);
      maxPredictions = model.getTotalClasses();

      // Convenience function to setup a webcam
      const size = 400;
      const flip = true; // whether to flip the webcam
      webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
      await webcam.setup(); // request access to the webcam
      await webcam.play();
      window.requestAnimationFrame(this.loop);
      

      // append/get elements to the DOM
      const canvas = document.getElementById("canvas");
      canvas.width = size;
      canvas.height = size;
      ctx = canvas.getContext("2d");
      labelContainer = document.getElementById("label-container");
      
    },


    async loop(timestamp) {
      webcam.update(); // update the webcam frame
      await this.predict();
      window.requestAnimationFrame(this.loop);
    },

    async predict() {
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);


      //step0 
      if(this.step==0){
        this.speak = "정자세로 서주시기 바랍니다" 
        this.step++;
      }


      //step1 정자세로 서기
      if(this.step == 1 ){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
        }
        
        if(prediction[0].probability.toFixed(2) == 1.0){
          
          this.speak = "step1 클리어!";
          await wait(1000)
          this.step++;
          this.send_step = false;

        }else{
          
          this.speak = "정자세로 서주세요";
          this.acc = prediction[0].probability.toFixed(2) * 100;

        }

      }

      //step2 오른쪽으로 다리 이동
      if(this.step == 2){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
          this.speak = "오른쪽으로 다리를 벌려 주세요";
        }

        if(prediction[1].probability.toFixed(2) == 1.0){

          this.speak = "step2 클리어!";
          this.step++;
          this.send_step = false;

        }else if(prediction[5].probability.toFixed(2) == 1.0){

          this.speak = "다리를 너무 넓게 폈습니다.";

        }

        this.acc = prediction[1].probability.toFixed(2) * 100;

      }

      //step3 오른쪽에 정자세로 서기
      if(this.step == 3 ){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
        }
        
        if(prediction[2].probability.toFixed(2) == 1.0){
          
          this.speak = "step3 클리어!";
          await wait(1000);
          this.step++;
          this.send_step = false;

        }else{
          
          this.speak = "정자세로 서주세요";
          this.acc = prediction[2].probability.toFixed(2) * 100;

        }

      }


      //step5 중앙
      if(this.step == 4 ){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
        }
        
        if(prediction[0].probability.toFixed(2) == 1.0){
          
          this.speak = "step4 클리어!";
          await wait(1000)
          this.step++;
          this.send_step = false;

        }else{
          
          this.speak = "정자세로 서주세요";
          this.acc = prediction[0].probability.toFixed(2) * 100;

        }

      }

      //step6 왼쪽으로 다리 이동
      if(this.step == 5){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
          this.speak = "왼쪽으로 다리를 벌려 주세요";
        }

        if(prediction[3].probability.toFixed(2) == 1.0){

          this.speak = "step5 클리어!";
          this.step++;
          this.send_step = false;

        }else if(prediction[6].probability.toFixed(2) == 1.0){

          this.speak = "다리를 너무 넓게 폈습니다.";

        }

        this.acc = prediction[3].probability.toFixed(2) * 100;

      }

      //step7 왼쪽으로 전신이동
      if(this.step == 6){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
        }
        
        if(prediction[4].probability.toFixed(2) == 1.0){
          
          this.speak = "step6 클리어!";
          await wait(1000)
          this.step++;
          this.send_step = false;

        }else{
          
          this.speak = "정자세로 서주세요";
          this.acc = prediction[4].probability.toFixed(2) * 100;

        }

      }



      //step9 가운데 정자세로 서기
      if(this.step == 7){

        if(this.send_step == false){

          this.$emit("sendStep",this.step);
          this.send_step = true;

        }
        
        if(prediction[0].probability.toFixed(2) == 1.0 && this.clear == false){

          this.clear = true;

        }else if(prediction[0].probability.toFixed(2) != 1.0 && this.clear == false){
          
          this.speak = "정자세로 서주세요";
          this.acc = prediction[0].probability.toFixed(2) * 100;

        }else if(this.clear == true){

          this.speak = "사이드스텝 클리어! 완료를 눌러주세요!";
          this.acc = 100;

        }

      }

      this.drawPose(pose);
      await wait(100)
    },
    drawPose(pose) {
      if (webcam.canvas) {
        ctx.drawImage(webcam.canvas, 0, 0);
        // draw the keypoints and skeleton
        if (pose) {
          const minPartConfidence = 0.5;
          tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
          tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
        }
      }
    },
  }
  
};
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.vedio {
  top: 26%;
  position: absolute;
}
.speak {
  top: 3%;
  left: 26%;
  position: absolute;
  font-size: 25px;
}
.stat {
    position:absolute;
    top: 92%;
    left: 0.2%;  
    font-size: 24px;
}

.result {
    position:absolute;
    top: 122%;
    left: 38%;
    font-size: 30px;
    color: $logo-color;
    font-weight: bold;
}
.start-btn2 {
  top: 112%;
  height: 40px;
  width: 100px;
  right: 12%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $logo-color;
  text-align: center;
  color: white;
  cursor:pointer;
}
.start2{
  padding-top: 8px;
}
.clear-btn2 {
  top: 124%;
  height: 40px;
  width: 100px;
  right: 12%;
  position: absolute;
  font-size: 22px ;
  border-radius: 30px;
  background-color: $logo-color;
  text-align: center;
  color: white;
  cursor:pointer;
  text-decoration: none;

}
.clear2{
  padding-top: 8px;
}
</style>
