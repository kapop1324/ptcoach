// 코스 선택 아이템 1개
<template>
  <div class="cor-div">
    <router-link class="r-link" :to="{name: 'CourseExp', params: {id: courseInfo.idx}}">
      <div class="cor-title">{{ courseInfo.name }}</div>
      <div class="all-ex">
        <div class="one-ex" v-for="(course, idx) in courseInfo.course" :key="`c-${idx}`">
          <div class="ex-img">
            <img :src="course.path" :alt="`${course.exercisename} 썸네일이미지`">
          </div>
          <div class="ex-title">{{ course.exercisename }}</div>
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
  box-shadow: 0px 1px 15px 1px #99a6ff21;
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
  width: 100px;
  height: 100px;
}
.cor-div:active, .cor-div:hover {
  transform:scale(1.013);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}
</style>