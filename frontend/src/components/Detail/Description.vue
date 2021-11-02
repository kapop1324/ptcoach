<template>
    <div id="DescriptionRoot">
        <!-- <img src="{{path}}" alt="스쿼트 샘플이미지">
        <div class="Desc">
            <p>{{desc}}</p>
        </div>     -->
        <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지">
        <div class="Desc">
            <p>1. 릴렉스한 채로 선다. 어깨 넓이로 발을 벌리고 양 팔은 몸에 가볍게 붙인다.</p>
            <p>2 앉아있기(정자세)</p>
            <p>3 앉아있기(잘못된자세) : 허리가 굽은경우 - 허리를 피세요</p>
        </div>          
    </div>
</template>

<script>
export default {
    name: "Description",
    props: {
        //path:"",
        //desc:"",
    },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
p { margin: 0px 0px 2px 0px; line-height: 120%;}
img {
    top: 22%;
    width: 20%;
    height: 45%;  
    left: 48%;
    position: absolute;
    margin: 10px;
}
.Desc {
    top: 72%;
    width: 25%;
    height: 12%;  
    left: 50%;
    position: absolute;
    color: gray;
    margin: 10px;    
}
</style>
