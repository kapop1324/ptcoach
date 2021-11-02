<template>
  <div class="about">
    <!-- <div>Teachable Machine Pose Model</div> -->
    <button type="button" @click="init()">Start</button>
    <div><canvas id="canvas"></canvas></div>
    <div id="label-container"></div>
    <div>
      <p>{{speak}}</p>
      {{step}}
    </div>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>

<script >
import * as tmPose from "@teachablemachine/pose";
import AipreesApi from "../../api/AipressApi";
import { mapState } from 'vuex'

// More API functions here:
// https://github.com/googlecreativelab/teachablemachine-community/tree/master/libraries/pose

// the link to your model provided by Teachable Machine export panel
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
    };
  },
  props:{
    step: Number,
  },
  create(){
    console.log(this.stat)

    let data = {
      stat : this.$store.state.stat,
    };

    AipreesApi.Insert(
      data,
      res => {
        this.stat = res.data.insert.stat;
      }

    );


  },

  methods: {
    
    async init() {
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
      for (let i = 0; i < maxPredictions; i++) {
        // step번호에 해당하는 인덱스결과값만 label에 붙이기
        labelContainer.appendChild(document.createElement("div"));
      }
      
    },


    async loop(timestamp) {
      webcam.update(); // update the webcam frame
      await this.predict();
      window.requestAnimationFrame(this.loop);
    },

    async predict() {
      // var basic = "basic"
      // var up_true = "up_true"
      // var up_false = "up_false"
      // var down_false = "down_false"
      // var basic_fasle = "basic_fasle"

      // Prediction #1: run input through posenet
      // estimatePose can take in an image, video or canvas html element
      const { pose, posenetOutput } = await model.estimatePose(webcam.canvas);
      // Prediction 2: run input through teachable machine classification model
      const prediction = await model.predict(posenetOutput);

      if(!this.dialog){
        this.speak = "정자세로 서주시기 바랍니다" 
      }

      if (prediction[0].probability.toFixed(2) == 1.0 && !this.dialog) {
        this.stat = "stand";
        this.dialog = true; // 서있는 자세를 정확하게 했을 경우
        this.speak = "좋습니다 시작합니다 시작해주세요";
      }else if (prediction[1].probability.toFixed(2) == 1.0 && this.dialog ) {
        if (this.stat == "up_true" && this.is_wrong == false) {
          this.cnt++;
          this.success_cnt++;
          this.speak ="자세 좋습니다"
          console.log(this.cnt);
          this.rate = (this.success_cnt / this.cnt).toFixed(2) * 100;
          console.log(this.rate);

          //    const aaa = this.cnt%10 + '.mp3'

          //   var audio = document.getElementById(aaa);

          var audio = new Audio((this.cnt % 10) + ".mp3");

          audio.play();

          // var promise = document.querySelector("aaa").play();

          // if(promise !== undefined){
          //     promise.then(_ =>{

          //     }).catch(error =>{

          //     });
          // }
        } else if (this.stat != "up_true" && this.is_wrong == true && this.dialog) {
          this.cnt++;
          this.rate = (this.success_cnt / this.cnt).toFixed(2) * 100;
          
        }
        this.is_wrong = false;
        this.stat = "basic";
        
        } else if (prediction[2].probability.toFixed(2) == 1.0) {

        this.is_wrong = false;
        this.stat = "up_true";
        

        } else if (prediction[3].probability.toFixed(2) == 1.0 && this.dialog) {

        this.stat = "up_false";
        this.is_wrong = true;
        this.speak ="팔 넓이를 좁혀 주세요"

        } else if (prediction[4].probability.toFixed(2) == 1.0 && this.dialog) {

        this.stat = "down_false";
        this.is_wrong = true;
        this.speak ="팔을 어깨 선과 맞춰 주세요."

        } else if (prediction[5].probability.toFixed(2) == 1.0 && this.dialog) {

        if (this.stat == "up_true" || this.stat == "basic") {
          var audio = new Audio("basic_fasle.mp3");
          audio.play();

        }
        this.is_wrong = false;
        this.stat = "basic_false";
        } 
      // 만약 10개를 달성하면 일단 정지 
      // front store에 저장하여 가지고 있도록 구성하고 
      // 다음 코스 운동으로 넘어가기
        else if( this.cnt == 10){
        await this.webcam.stop();
        }

        for (let i = 0; i < maxPredictions; i++) {
          const classPrediction="";
          if(i+1==this.step){
            classPrediction =
            prediction[i].className + ": " + prediction[i].probability.toFixed(2);
          }
          labelContainer.childNodes[i].innerHTML = classPrediction;
        }

        // finally draw the poses
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
    // init(){
    //   // 상위 컴포넌트 이름: "accuracy"
    //   this.$emit("showAccuarcy",this.rate);
    // },
  },
};
</script>
