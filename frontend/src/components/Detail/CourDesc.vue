<template>
    <div id="DescriptionRoot">
        <img :src="path">
        <div class="Desc">
            <p>{{desc}}</p>
        </div>          
    </div>
</template>

<script>
export default {
    name: "Description",
    props: {
        path:String,
        desc:String,
    },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
p { margin: 0px 0px 2px 0px; line-height: 120%;}
img {
    top: 27%;
    width: 360px;
    height: 360px;  
    left: 49%;
    position: absolute;
    margin: 10px;
    border-collapse: black;
}
.Desc {
    top: 79%;
    width: 25%;
    height: 12%;  
    left: 49%;
    position: absolute;
    color: gray;
    margin: 10px;    
}

</style>
