// 코스 선택 아이템 1개
<template>
  <div class="cor-div">
    <router-link class="r-link" :to="{name: 'CourseDetail', params: {id: courseInfo.id}}">
      <div class="cor-title">{{ courseInfo.name }}</div>
      <div class="all-ex">
        <div class="one-ex" v-for="(course, idx) in courseInfo.course" :key="`c-${idx}`">
          <div class="ex-img">
            <!-- <img :src="course.path" alt="운동 샘플이미지"> -->
            <img src="@/assets/squat_sample.png" alt="운동 샘플이미지">
            <!-- <img src="@/assets/squat_sample_remove.png" alt="운동 샘플이미지"> -->
          </div>
          <div class="ex-title">{{ course.exercise }}</div>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'CourseListItem',
  props: {
    courseInfo: Object,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

* {
  text-decoration: none;
  color: $black-color;
}
.cor-div {
  background-color: #ffffff;
  border-radius: 20px;
  width: 80%;
  padding: 25px 40px 25px;
  min-width: 600px;
}
.cor-title {
  font-size: 1.4rem;
}
.all-ex {
  display: flex;
  justify-content: space-between;
  width: 500px;
  margin: 0 50px 0 auto;
}
.one-ex {
  // display: flex;
  // flex-direction: column;
  // align-items: center;
  text-align: center;
}
.ex-title {
  font-size: 1.2rem;
}
.ex-img {
  width: 80px;
  margin-bottom: 10px;
}
.ex-img > img {
  width: 100%;
  height: 100%;
}
.cor-div:active, .cor-div:hover {
  transform:scale(1.01);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}
</style>