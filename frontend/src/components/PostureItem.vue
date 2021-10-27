// 자세교정 선택 아이템 1개
<template>
  <div>
    <div class="pos-div">
      <div class="pos-title">{{ exInfo.name }}</div>
      <div class="post-img">
        <!-- <img src="@/assets/squat_sample.png" alt="스쿼트 샘플이미지"> -->
        <img src="@/assets/squat_sample_remove.png" alt="스쿼트 샘플이미지">
      </div>
      <!-- <span class="pos-tag" v-for="(tag, idx) in exInfo.part" :key="idx">
        {{ tag }}
      </span> -->
    </div>
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

.pos-div {
  background-color: #ffffff;

  border-radius: 20px;
  width: 220px;
  height: 290px;
}
.pos-title {
  // position: absolute;
  padding-top: 20px;
  font-size: 1.5rem;
  // font-weight: bold;
}
.post-img {
  width: 120px;
  // height: 150px;
}
.post-img > img {
  width: 100%;
  height: 100%;
}
.pos-tag {
  // background-color: $footer-color;
  background-color: $black-color;
  color: $white-color;

  border-radius: 3px;
  margin-right: 5px;
  font-size: 0.9rem;
  padding: 3px 12px;
  // line-height: 41px;
  // font-weight: bold;
  font-family: 'SBAggroL';
}
</style>