// 자세교정 선택 아이템 1개
<template>
  <div class="pos-div">
    <router-link class="r-link" :to="{name: 'PostureDetail', params: {id: exInfo.id}}">
      <div class="pos-title">{{ exInfo.name }}</div>
      <div class="post-img">
        <!-- <img :src="exInfo.path" alt="스쿼트 샘플이미지"> -->
        <!-- <img src="@/assets/squat_sample.png" alt="스쿼트 샘플이미지"> -->
        <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지">
      </div>
      <span class="pos-tag" v-for="(tag, idx) in exInfo.part" :key="idx">
        {{ tag }}
      </span>
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'PostureItem',
  props: {
    exInfo: Object,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

* {
  text-decoration: none;
  color: $black-color;
}
.pos-div {
  background-color: #ffffff;
  border-radius: 20px;
  width: 190px;
  height: 250px;
  padding: 20px 10px 10px 18px;
}
.pos-title {
  font-size: 1.4rem;
}
.post-img {
  width: 100px;
  // height: 150px;
  margin: 20px 10px 27px auto;
  // margin: 10px auto 15px;
}
.post-img > img {
  width: 100%;
  height: 100%;
}
.pos-tag {
  background-color: $logo-color;
  color: $white-color;
  border-radius: 3px;
  margin-right: 5px;
  font-size: 0.9rem;
  padding: 3px 12px;
  font-family: 'SBAggroL';
}
.pos-div:active, .pos-div:hover {
  transform:scale(1.045);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}

</style>