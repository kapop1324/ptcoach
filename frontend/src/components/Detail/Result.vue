//result값이 80이 넘으면 성공 메시지와 함께 다음 step으로 넘어가기
<template>
    <div id="Result">
        <div class="result">{{result}}% 일치합니다</div>   
    </div>
</template>

<script>
export default {
    name: "Result",
    props: {
        result: Number,
    },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.result {
    position:absolute;
    top: 94%;
    left: 45%;
    font-size: 20px;
    color: $logo-color;
    font-weight: bold;
}
</style>
