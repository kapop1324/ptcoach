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
import wait from 'waait';

const URL = "https://teachablemachine.withgoogle.com/models/Tt5WzWwqn/";
let model, webcam, ctx, labelContainer, maxPredictions;

export default {
  data: () => {
    return {
      stat: "",
      cnt: 0,
      success_cnt: 0,
      is_wrong: false,
      rate: 10,
      dialog : false,
      speak :"",
      acc:100,
      step:0,
      clear: false,
      stepb:false, //이전스탭의 acc상태 보여주기
      clear_sound : false, // 마지막 성공했을때 음성 틀어주기
    };
  },

  methods: {
    
    async init() {
      var audio = new Audio(require('@/assets/3audio/1.mp3'));
      audio.play();

      for(var i = 5; i>0; i--){
        this.speak = i+"초 후 시작 ⏳ "
        await wait(1000);
      }
      audio = new Audio(require('@/assets/3audio/2.mp3'));
      audio.play();
      await wait(1000);

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

    //덤벨로우
    async predict() {
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);

      //step 0
      if(this.step == 0){
        
        this.speak = "📢 정자세로 서주시기 바랍니다."
        this.step++;
      }

      //step1 stand 자세 취하기 

      if(this.step == 1){
        if(this.dialog == false){
          this.$emit("sendStep",this.step);
          this.dialog = true;
        }
        this.acc = prediction[0].probability.toFixed(2) * 100;

        if(prediction[0].probability.toFixed(2) == 1.0){
          this.speak = "📢 정자세 좋습니다 3초간 유지해주세요.";
          this.step++;
          this.acc = prediction[0].probability.toFixed(2) * 100;
          this.stat = "stand"

          setTimeout(() => {
            
            this.dialog = false;
            this.stepb = true;
          }, 3000);
        }else{
          this.speak = "📢 정자세로 서주시기 바랍니다.";
          this.acc = prediction[0].probability.toFixed(2) * 100;
        }
        
      }


        //step2 basic 자세
        if(this.step == 2 && this.stepb == true){
          
          if(this.dialog == false){
          this.$emit("sendStep",this.step);
          this.dialog = true;
          this.speak = "step1 클리어!⭐️"
          var audio = new Audio(require('@/assets/3audio/3.mp3'));
          audio.play();
          await wait(1000)
          this.stat = "basic"
        }

        this.acc = prediction[1].probability.toFixed(2) * 100;

        if(prediction[1].probability.toFixed(2) == 1.0 && this.stat == "basic"){
            this.speak = "📢 지금 상태를 3초간 유지해 주세요."
            this.step++;
            
            setTimeout(() => {
              
              this.dialog = false;
              this.stepb = false;
            }, 3000);
          }else if(prediction[4].probability.toFixed(2) == 1.0){
           
            this.speak = "📢 고개를 들고 허리를 펴주세요."
          }else if(prediction[5].probability.toFixed(2) == 1.0){
            this.speak = "📢 팔을 상체와 가깝게 붙이고 내려주세요."
          }
          
        }


        // step3 up 자세
        if(this.step == 3 && this.stepb == false){
          if(this.dialog == false){
          this.$emit("sendStep",this.step);
          this.dialog = true;
          this.speak = "step2 클리어!⭐️"
          var audio = new Audio(require('@/assets/3audio/4.mp3'));
          audio.play();
          await wait(1000)
          this.stat = "up"
        }

        this.acc = prediction[2].probability.toFixed(2) * 100;

        if(prediction[2].probability.toFixed(1) == 1.0 && this.stat == "up"){
          this.speak = "📢 지금 상태를 3초간 유지해 주세요."
          this.step++;

          setTimeout(() => {
            this.dialog = false;
            this.stepb = true;
          }, 3000);

        }else if(prediction[3].probability.toFixed(2) == 1.0){
          this.speak = "📢 허리를 펴고, 팔을 상체와 가깝게 붙이고 들어올려주세요."
        }

        }

        // step4 basic 자세 
        if(this.step == 4 && this.stepb == true ){
          if(this.dialog == false){
            this.$emit("sendStep",this.step);
            this.dialog = true;
            var audio = new Audio(require('@/assets/3audio/3.mp3'));
            audio.play();
            await wait(1000)
            this.speak = "step3 클리어!⭐️"
            this.stat = "basic"
          }

          this.acc = prediction[1].probability.toFixed(2) * 100;

          if(prediction[1].probability.toFixed(2) == 1.0 && this.clear == false){
            this.speak = "📢 지금 상태를 3초간 유지해 주세요."
            setTimeout(() => {
              this.speak = "덤벨 로우 클리어!⭐️ 완료를 눌러주세요";
              this.clear = true;
            }, 3000);
          }else if(prediction[4].probability.toFixed(2) == 1.0){
            this.speak = "📢 고개를 들고 허리를 펴주세요."
          }else if(prediction[5].probability.toFixed(2) == 1.0){
            this.speak = "📢 팔을 상체와 가깝게 붙이고 내려주세요."
          }else if(this.clear == true){
            this.acc = 100;
            if(this.clear_sound == false){
              await wait(1000)
              var audio = new Audio(require('@/assets/3audio/5.mp3'));
              audio.play();
              this.clear_sound = true;
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
