// 자세교정 선택 아이템 1개
<template>
  <div class="pos-div">
    <router-link class="r-link" :to="{name: 'PostureDetail', params: {id: exInfo.id}}">
      <div class="pos-title">{{ exInfo.name }}</div>
      <div class="post-img">
        <img src="@/assets/squat_sample.png" alt="스쿼트 샘플이미지">
        <!-- <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지"> -->
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
  border: 2px solid #b6b6b6;
  border-radius: 20px;
  width: 190px;
  height: 250px;
  padding: 20px 15px 10px;
}
.pos-title {
  font-size: 1.4rem;
}
.post-img {
  width: 120px;
  // height: 150px;
  margin: 10px 0 15px auto;
  // margin: 10px auto 15px;
}
.post-img > img {
  width: 100%;
  height: 100%;
}
.pos-tag {
  background-color: $footer-color;
  border-radius: 3px;
  margin-right: 5px;
  font-size: 0.9rem;
  padding: 3px 12px;
  font-family: 'SBAggroL';
}
.pos-div:active, .pos-div:hover {
  background-color: $yellow-color;
  transform:scale(1.06);
}

</style>