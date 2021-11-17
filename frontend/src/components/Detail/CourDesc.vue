<template>
    <div id="DescriptionRoot">
    <div class="gif">
        <img :src="exList[newstep].exercise_image[exList[newstep].exercise_image.length-1].path" >
    </div>
    </div>
</template>

<script>
export default {
    name: "Description",
    props: {
        newstep:Number,
    },
    computed: {
        exList() {
            console.log(this.$store.getters.getExerciseList);
            return this.$store.getters.getExerciseList;
            },
        },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.gif img{
    position: absolute;
    top:44%;
    width: 220px;
    height:320px;
    right:34%;
}

</style>
