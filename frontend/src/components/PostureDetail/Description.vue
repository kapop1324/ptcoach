<template>
    <div id="DescriptionRoot">
        <!-- <img src="@/assets/images/ex.PNG"> -->
        <img>
        <div class="Desc">
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
    top: 15%;
    width: 35%;
    height: 45%;  
    left: 45%;
    position: absolute;
    border: solid black;
    margin: 10px;
}
.Desc {
    top: 65%;
    width: 35%;
    height: 20%;  
    left: 45%;
    position: absolute;
    border: solid black;
    margin: 10px;    
}
</style>
