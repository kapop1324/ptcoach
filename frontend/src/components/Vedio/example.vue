<template>
  <div class="about">
    <div class="vedio"><canvas id="canvas"></canvas></div>
    <div>
      <p class="speak">{{speak}}</p>
      <div class="title">Accuracy:</div> 
      <div class="bar" v-bind:style="{width:acc/2+'%'}">{{parseInt(acc)}}%</div>
    </div> 
    <div class="start-btn">
      <div class="start" @click="init()"> 시작 </div>
    </div> 
    <router-link to="/posture" class="clear-btn">
      <div class="clear"> 완료 </div>
    </router-link> 

  </div>
</template>


<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>

<script >
import * as tmPose from "@teachablemachine/pose";
import { mapState } from 'vuex'
import wait from "waait"

//런지

// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model provided by Teachable Machine export panel
const URL = " https://teachablemachine.withgoogle.com/models/0K-kDjfYw/";
let model, audio, webcam, ctx, labelContainer, maxPredictions;

export default {
  data: () => {
    return {
      speak :"",
      acc:100,
      step:0,
      clear : false,
      send_step : false,
      clear_sound : false,
      step_clear : false,
      check: false,
      interval: null,
    };
  },
  create(){
    console.log(this.stat)

    let data = {
      stat : this.$store.state.stat,
    };


  },
  destroyed() {
    clearInterval(this.interval);
    webcam.stop();
    audio.pause();
    
  },
  methods: {
    async init() {
      this.check = true;
      audio = new Audio(require('@/assets/audio/correction/5second.mp3'));
      console.log(audio)
      audio.play();
      for(var i = 5; i > 0; i--){
        this.speak = i+"초 후 시작 ⏳ "
        await wait(1000);
      }
      audio = new Audio(require('@/assets/audio/lunge/lungec0.mp3'));
      audio.play();
      this.speak = "카메라를 불러오고 있습니다."

      const modelURL = URL + "model.json";
      const metadataURL = URL + "metadata.json";

      model = await tmPose.load(modelURL, metadataURL);
      maxPredictions = model.getTotalClasses();

      const size = 400;
      const flip = true; // whether to flip the webcam
      webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
      await webcam.setup(); // request access to the webcam
      await webcam.play();
      this.interval = setInterval(this.loop, 10);
      
      const canvas = document.getElementById("canvas");
      canvas.width = size;
      canvas.height = size;
      ctx = canvas.getContext("2d");
      labelContainer = document.getElementById("label-container");
      console.log('finish')
    },


    async loop(timestamp) {
      if (this.check) {
        webcam.update(); // update the webcam frame
        await this.predict();
        // window.requestAnimationFrame(this.loop);
        console.log('loop', this.check)
      }
      else {
        webcam.stop()
        console.log('loop stop', this.check)
      }
    },

    async predict() {
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);


      //step0 
      if(this.step==0){

        this.speak = "카메라를 불러오고 있습니다."
        for(var i = 3; i > 0; i--){
          
          await wait(1000);
        }

        this.speak = "📢 정자세로 서주시기 바랍니다." 
        this.step++;
      }


      //step1 정자세로 서기
      if(this.step == 1 ){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
          var audio = new Audio(require('@/assets/audio/lunge/lungec1.mp3'));
          audio.play();
          await wait(1500);
        }
        
        if(prediction[0].probability.toFixed(2) == 1.0){
          var audio = new Audio(require('@/assets/audio/lunge/lungec3.mp3'));
          audio.play();
          this.speak = "📢 3초간 자세를 유지하세요.";
          this.step++;

            setTimeout(() => {
            
            this.send_step = false;
            this.step_clear = true;
          }, 3000);

        }else{
          
          this.speak = "📢 정자세로 서주시기 바랍니다.";
          this.acc = prediction[0].probability.toFixed(2) * 100;
          await wait(1000); 

        }

      }

      //step2 앉기
      if(this.step == 2 && this.step_clear == true){

        if(this.send_step == false){
          this.$emit("sendStep",this.step);
          this.send_step = true;
          this.speak = "📢 앉아주세요.";
          var audio = new Audio(require('@/assets/audio/lunge/lungec2.mp3'));
          audio.play();
          await wait(1500);
        
        }

        if(prediction[1].probability.toFixed(2) == 1.0){
          var audio = new Audio(require('@/assets/audio/lunge/lungec3.mp3'));
          audio.play();
          this.speak = "📢 3초간 자세를 유지하세요."
          this.step++;
          setTimeout(() => {
              
              this.send_step = false;
              this.step_clear = false;
              this.acc = prediction[1].probability.toFixed(2) * 100;
            }, 3000);

        }else if(prediction[2].probability.toFixed(2) == 1.0){
        
          this.speak = "📢 허리를 곧게 펴주세요.";
          var audio = new Audio(require('@/assets/audio/lunge/lungec4.mp3'));
          audio.play();
          await wait(1000); 
        }

        this.acc = prediction[1].probability.toFixed(2) * 100;

      }

      //step3 정자세로 서기
      if(this.step == 3 && this.step_clear == false){

        if(this.send_step == false){
          await wait(1000)
          var audio = new Audio(require('@/assets/audio/lunge/lungec6.mp3'));
          audio.play();
          this.$emit("sendStep",this.step);
          this.send_step = true;
          await wait(1500)

        }
        
        if(prediction[0].probability.toFixed(2) == 1.0 && this.clear == false){

          this.clear = true;
          this.acc = prediction[1].probability.toFixed(2) * 100;

        }else if(prediction[0].probability.toFixed(2) != 1.0 && this.clear == false){
          
          this.speak = "📢 정자세로 서주시기 바랍니다.";
          this.acc = prediction[0].probability.toFixed(2) * 100;

        }else if(this.clear == true){

          this.speak = "런지 클리어!⭐️ 완료를 눌러주세요!";
          this.acc = 100;
          if(this.clear_sound == false){
            var audio = new Audio(require('@/assets/audio/lunge/lungec5.mp3'));
              audio.play();
              this.clear_sound = true;
              this.step_clear = true;
  
          }         

        }

      }

      this.drawPose(pose);
  
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
.title {
    position:absolute;
    top: 128%;
    left: 12%;
    font-size: 14px;
    color: $logo-color;
    font-weight: bold;
}
.bar {
  position:absolute;
  top: 122%;
  left: 22%;
  background: $logo-color;
  width: 10%;
  display: block;
  margin: 20px 0;
  line-height:1.8em;
  border-radius:10px;
  text-align : center;
  color: white;
  font-size: 12px;
  padding: 3px;
}
.speak {
    top: 3%;
    left: 28%;
    position: absolute;
    font-size: 24px;
}
.vedio {
  top: 26%;
  position: absolute;
}
.stat {
    position:absolute;
    top: 92%;
    left: 0.2%;  
    font-size: 24px;
}
.start-btn {
  top: 88%;
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
.start{
  padding-top: 8px;
}
.clear-btn {
  top: 100%;
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
.clear{
  padding-top: 8px;
}
</style>
