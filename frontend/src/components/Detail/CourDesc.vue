<template>
    <div id="DescriptionRoot">
    <!-- //exList[0].exercise_image[0].path
    //exList[0].exercise_image[1].path
    //exList[0].exercise_image[2].path
    //exList[0].exercise_image[3].path         -->
    <carousel class="carousel" :items="1">
        <div v-for="(exercise,i) in exList[newstep].exercise_image" v-bind:key="i">
            <img :src="exercise.path" >
        </div>
    </carousel>        

    </div>
</template>

<script>
import carousel from 'vue-owl-carousel'
export default {
    name: "Description",
    props: {
        newstep:Number,
    },
    components: { carousel },    
    computed: {
        exList() {
            return this.$store.getters.getExerciseList;
            },
        },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.carousel {
    position: absolute;
    top:50%;
    width: 230px;
    height:60px;
    right:34%;
}

</style>
