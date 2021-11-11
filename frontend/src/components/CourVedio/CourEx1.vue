<template>
<div class="about">
<div class="vedio"><canvas id="canvas"></canvas></div>
    <div>
        <p class="speak">{{speak}}</p>
    </div>
    <p class="timer">{{Timer}}</p>
    <div class="set-count">
        <div class="cont">{{this.set}}세트 {{this.cnt}}회</div>
        <div id="chart">
            <apexchart type="radialBar" height="150" :options="chart.chartOptions" v-bind:series="this.chart.series"></apexchart>
        </div>   
    </div> 

</div>
</template>
<script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs@1.3.1/dist/tf.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@teachablemachine/pose@0.8/dist/teachablemachine-pose.min.js"></script>
<script >
import * as tmPose from "@teachablemachine/pose";
import VueApexCharts from 'vue-apexcharts'
import wait from "waait"

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
        time:0,
        accuracy:0,
        apexchart:VueApexCharts,
        value:0,
        chart: {
            series: [0],
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
            labels: ['set'],
            },      
        },
        stopWatch: 0,
        timer: undefined  
    };
    },
    created(){
        this.init();
    },
    methods: {
        async init() {   
        for(var i = 3; i > 0; i--){
            this.speak = i+"초 후 시작 ⏳"
            await wait(1000);
        }
        this.speak = "카메라가 켜지고 있습니다."
        const modelURL = URL + "model.json";
        const metadataURL = URL + "metadata.json";
        model = await tmPose.load(modelURL, metadataURL);
        maxPredictions = model.getTotalClasses();
        const size = 430;
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
            this.speak = "좋습니다 시작해주세요 타이머 ON ⏰"
            this.start()  
        }else if (prediction[1].probability.toFixed(2) == 1.0 && this.dialog ) {
            if (this.stat == "up_true" && this.is_wrong == false) {
                this.cnt++;
                this.addChart();
                this.success_cnt++;
                this.speak ="자세 좋습니다"
                this.rate = (this.success_cnt / this.cnt).toFixed(2) * 100;
            } else if (this.stat != "up_true" && this.is_wrong == true && this.dialog) {
                this.cnt++;
                this.addChart();
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
        if( this.cnt == 5){
            this.set++;
            this.cnt=0;
            if( this.set == 2){
                this.stop();
                console.log("시간:"+this.stopWatch/1000);
                let record = {
                    exercise_idx:1,
                    time: this.stopWatch/1000,
                    accuracy: this.rate,
                };   
                this.$store.state.record = record;
                this.$emit("Index");
            } 
        }
            this.drawPose(pose);
        },
        drawPose(pose) {
        if (webcam.canvas) {
            ctx.drawImage(webcam.canvas, 0, 0);
            if (pose) {
            const minPartConfidence = 0.5;
            tmPose.drawKeypoints(pose.keypoints, minPartConfidence, ctx);
            tmPose.drawSkeleton(pose.keypoints, minPartConfidence, ctx);
            }
        }
        },
        start() {
            this.timer = setInterval(() => {
                this.stopWatch += 1000;
            }, 1000);
        },
        stop() {
            clearInterval(this.timer);
        },
        addChart() {
            this.value=this.value+20;
            this.chart.series.splice(0,1,this.value);
            if(this.value==100){
                this.value=0;
            }
        },
    },
    computed: {
        Timer() {
            const date = new Date(null);
            date.setSeconds(this.stopWatch / 1000);
            const utc = date.toUTCString();
            return utc.substr(20, 5);
        }
    },
};
</script>
<style lang="scss" scoped>
@import "@/styles/common.scss";
.set-count {
    top: 33%;
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
    top: 28%;
    position: absolute;
}
.speak {
    top: 3%;
    left: 24%;
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
.timer {
    top:20%;
    position: absolute;
    font-size: 54px;
    width: 180px;
    height: 90px;
    right:38%;
}
</style>
