<template>
  <div class="result">
    <div id="course-img"><img src="@/assets/squat.png"/></div>
    <div id="course-comment">comment</div>
  </div>
</template>

<script>
export default {

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
    height: 100px;
    margin: 30px;
    padding: 30px;
    background: #d4d3d3;
}

#course-comment {
    width: 100px;
    height: 50px;
    padding: 30px;
    margin: 30px;
    // font-weight: bold;
    background: $light-color;
    border-radius: 20px;
}
</style>