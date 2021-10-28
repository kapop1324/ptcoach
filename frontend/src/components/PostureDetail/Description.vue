<template>
    <div id="DescriptionRoot">
        <!-- <img src="@/assets/images/ex.PNG"> -->
        <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지">
        <div class="Desc">
            <span>1. 릴렉스한 채로 선다. 어깨 넓이로 발을 벌리고 양 팔은 몸에 가볍게 붙인다.
            2 앉아있기(정자세)
            3 앉아있기(잘못된자세) : 허리가 굽은경우 - 허리를 피세요</span>
        </div>    
    </div>
</template>

<script>
export default {
    name: "Description",
    props: {
    },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

img {
    top: 22%;
    width: 30%;
    height: 45%;  
    left: 48%;
    position: absolute;
    //border: solid black;
    margin: 10px;
}
.Desc {
    top: 72%;
    width: 30%;
    height: 14%;  
    left: 48%;
    position: absolute;
    //border: solid black;
    color: gray;
    margin: 10px;    
}
</style>
