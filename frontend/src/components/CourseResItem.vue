<template>
  <div class="result">
    <div id="course-img"><img src="@/assets/squat.png"/></div>
    <div id="course-comment">{{course.accuracy}}% 정확성! :)</div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      // accuracy: '',
      // src: '@/assets/squat.png',
    }
  },
  props: {
    course: {
      type: Object
    },
  },
  computed: {
    // src() {
    //   return '@/assets/squat.png'; //`http://i5c102.p.ssafy.io/api/feed/${this.feed.images[0].newname}`
    // },
  }
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