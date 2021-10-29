// 자세교정 선택 아이템 1개
<template>
  <div class="cor-div">
    <router-link class="r-link" :to="{name: 'CourseExp', params: {id: courseInfo.id}}">
      <!-- <div class="cor-title">{{ courseInfo.name }}</div> -->
      {{ courseInfo }}
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
  // border: 2px solid #b6b6b6;
  border: 2px solid black;
  border-radius: 20px;
}

</style>