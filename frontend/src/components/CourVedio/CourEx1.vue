<template>
<div class="about">
<div class="vedio"><canvas id="canvas"></canvas></div>
    <div>
        <p class="speak">{{speak}}</p>
    </div>
    <div class="start-btn">
        <div class="start" @click="init()"> 시작 </div>
    </div> 

    <div class="set-count">
        <div class="cont">{{this.set}}세트 {{this.success_cnt}}회</div>
        <div id="chart">
        <apexchart type="radialBar" height="150" :options="chart.chartOptions" :series="chart.series"></apexchart>
        </div>   
    </div> 

</div>
</template>
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>
<script >
import * as tmPose from "@teachablemachine/pose";
import VueApexCharts from 'vue-apexcharts'
const URL = "https://teachablemachine.withgoogle.com/models/mZu-ppxDG/";
let model, webcam, ctx, labelContainer, maxPredictions;

export default {
    data: () => {
    return {
        stat: "",
        cnt: 0,
        set: 0,
        success_cnt: 0,
        is_wrong: false,
        rate: 0,
        dialog : false,
        speak :"",
        set:0,
        count:0,
        apexchart:VueApexCharts,
        chart: {
            series: [],
            chartOptions: {
            chart: {
            height: 150,
            type: 'radialBar',
            },
            plotOptions: {
            radialBar: {
                hollow: {
                size: '70%',
                }
            },
            },
            labels: ['count'],
            },      
        },        
    };
    },
    create(){
        console.log(this.stat);
        let data = {
        stat : this.$store.state.stat,
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
        const prediction = await model.predict(posenetOutput);

        if(!this.dialog){
            this.speak = "정자세로 서주시기 바랍니다" 
        }
        if (prediction[0].probability.toFixed(2) == 1.0 && !this.dialog) {
            this.stat = "stand";
            this.dialog = true; // 서있는 자세를 정확하게 했을 경우
            this.speak = "좋습니다 시작합니다 시작해주세요"
        }else if (prediction[1].probability.toFixed(2) == 1.0 && this.dialog ) {
            if (this.stat == "up_true" && this.is_wrong == false) {
                this.cnt++;
                this.success_cnt++;
                //this.$emit("Count",this.success_cnt);
                this.speak ="자세 좋습니다"
                //console.log(this.cnt);
                this.rate = (this.success_cnt / this.cnt).toFixed(2) * 100;
                console.log(this.rate);
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
            this.is_wrong = false;
            this.stat = "basic_false";
        } 
        // 만약 10개를 달성하면 일단 정지 
        // front store에 저장하여 가지고 있도록 구성하고 
        // 다음 코스 운동으로 넘어가기
        else if( this.cnt == 10){
                await this.webcam.stop();
        }
            //1세트 다 끝나면
        if( this.cnt == 3){
            this.set++;
            this.cnt=0;
            this.$emit("Set",this.set);
            this.$emit("Count",this.cnt);
            if( this.set == 1){
                this.$emit("Index");
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
    },
};
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
.set-count {
    top: 16%;
    right: 19%;
    width: 150px;
    position: absolute;  
    font-size: 24px;
    text-align: center;  
}
#chart {
    top: 100%;
    position: absolute;
}
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
    top: 96%;
    height: 40px;
    width: 100px;
    right: 18%;
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
    top: 110%;
    height: 40px;
    width: 100px;
    right: 18%;
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
