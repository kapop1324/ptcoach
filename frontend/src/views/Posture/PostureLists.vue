// 자세교정 선택 페이지
<template>
  <div>
    <h1>
      부족한 운동을 선택하고<br>집중적으로 교정해보세요
    </h1>

    <PostureItem />
  
  </div>
</template>

<script>
import PostureItem from "@/components/PostureItem.vue"

export default {
  name: 'PostureLists',
  components: {
    PostureItem,
  },

}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

h1 {
  margin-top: 30px;
  margin-bottom: 70px;
}

</style>