<template>
  <div class="result">
    <div id="course-img"><img v-if="exerciseList[num]" :src="exerciseList[num].exercise_image[0].path"/></div>
    <div id="course-comment">{{exercise.accuracy}}% 정확성! :)</div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      exerciseList: [{
          idx:'', 
          exercise_image:[
            {
              coursename: "",
              path: "",
              image_step: 0,
              desc: "",
              exercise_idx: 0,
              exercise_name: "",
            },
          ],
        }],
    }
  },
  props: {
    exercise: {
      type: Object
    },
    num:{
      type: Number
    },
  },
  created() {
    this.exerciseList = this.$store.getters.getExerciseList;
    // console.log(exerciseList)
  },
}
</script>

<style lang="scss" scoped>
@import '@/styles/common.scss';

img {
    width: 80px;
    height: 80px;
    /* object-fit: fill; */
}

#course-img{
    width: 100px;
    height: 80px;
    margin: 10px 20px;
    padding: 20px;
    // background: #d4d3d3;
    // background: white;
}

#course-comment {
    width: 100px;
    height: 40px;
    padding: 20px;
    margin: 20px;
    font-size: 16px;
    // font-weight: bold;
    background: white;
    border-radius: 20px;
}
</style>