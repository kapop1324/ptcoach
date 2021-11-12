//시작 버튼 누르면 시작
<template>
    <div id="ClockRoot">
        <div class="clock">
            {{minutes}}:{{seconds}}   
        </div>

    </div>
</template>

<script>
export default {
    name: "Clock",
    props:{
    },
    data(){
        return{
        timer: null,
        totalTime: 0,  
        }
    },    
    methods: {
        clockStart(){
            this.timer = setInterval(() => this.countdown(), 1000);
        },
        clockStop(){
            clearInterval(this.timer);
            this.timer=null;
        },        
        countdown() {
        if(this.totalTime >= 0) {
            this.totalTime++;
        }
        },
        padTime(time) {
        return (time < 10 ? '0' : '') + time;
        },
        
    },
    computed: {
        minutes() {
        const minutes = Math.floor(this.totalTime / 60);
        return this.padTime(minutes);
        },
        seconds() {
        const seconds = this.totalTime - (this.minutes * 60);
        return this.padTime(seconds);
        }
    }         
    
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.clock {
    font-size: 60px;
    width: 180px;
    height: 90px;  
}

</style>
