<template>
  <div class="about">
    <div class="vedio"><canvas id="canvas"></canvas></div>
    <div>
      <p class="speak">{{speak}}</p>
    <div class="result">{{acc}}% 일치</div> 
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

const URL = "https://teachablemachine.withgoogle.com/models/mZu-ppxDG/";
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
      acc:0,
      step:0,
    };
  },

  methods: {
    
    async init() {
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

    async predict() {
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);
      if(this.step==0 && !this.dialog){
        this.$emit("sendStep",this.step);
        this.speak = "정자세로 서주시기 바랍니다" 
        this.step++;
      }
      if (this.step==1 && prediction[0].probability.toFixed(2) == 1.0 && !this.dialog) {
        this.stat = "stand";
        this.dialog = true; // 서있는 자세를 정확하게 했을 경우
        this.speak = "좋습니다 시작합니다 시작해주세요"
      }
      //step1 시작
      if (this.step==1 && this.dialog) {
        //상위newstep으로 1 넘기기
        this.$emit("sendStep",this.step);
        if (prediction[3].probability.toFixed(2) == 1.0 && this.dialog) {
        this.stat = "up_false";
        this.dialog = true;
        this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
        } else if (prediction[4].probability.toFixed(2) == 1.0 && this.dialog) {
        this.stat = "down_false";
        this.dialog = true;
        this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
        } else if (prediction[5].probability.toFixed(2) == 1.0 && this.dialog) {
        this.stat = "basic_false";
        this.dialog = true;
        this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
        } 

        this.acc = prediction[1].probability.toFixed(2) * 100;
        
        //step1성공하면!
        if(this.step==1 && prediction[1].probability.toFixed(2) == 1.0 && this.dialog){
            this.step++;
            this.stat = "basic";
            this.dialog = true;
            this.speak = "step1 성공!! step2를 진행해 주세요."

            if (prediction[3].probability.toFixed(2) == 1.0 && this.dialog) {
            this.stat = "up_false";
            this.dialog = true;
            this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
            }
            else if (prediction[4].probability.toFixed(2) == 1.0 && this.dialog) {
            this.stat = "down_false";
            this.dialog = true;
            this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
            } else if (prediction[5].probability.toFixed(2) == 1.0 && this.dialog) {
            this.stat = "basic_false";
            this.dialog = true;
            this.speak ="팔 넓이를 좁혀 주세요/ 팔을 어깨 선과 맞춰 주세요."
            } 

            this.acc = prediction[1].probability.toFixed(2) * 100;
            console.log("stat:"+this.stat);
            console.log("acc:"+this.acc);
        }
      }
      //step2 시작
      if(this.step==2 && prediction[2].probability.toFixed(2) == 1.0 && this.dialog){
          this.$emit("sendStep",this.step);
          this.stat = "up_true";
          this.dialog = true;
          this.speak = "step2 성공!! '완료' 버튼을 눌러주세요."
          this.acc = prediction[2].probability.toFixed(2) * 100;
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
