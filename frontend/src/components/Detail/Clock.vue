//시작 버튼 누르면 시작
<template>
    <div id="ClockRoot">
        <div class="clock">
            
        </div>

    </div>
</template>

<script>
export default {
    name: "Clock",
    
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.clock {
    width: 120px;
    height: 130px;  
    border: solid black;
}

</style>
