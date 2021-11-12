// 자세교정 선택 아이템 1개
<template>
  <div>
    <router-link class="r-link" :to="{name: 'PostureDetail', params: {id: exInfo.idx}}">
      <div class="pos-div">
        <div class="pos-title">{{ exInfo.exercisename }}</div>
        <div class="post-img">
          <img :src="exInfo.path" :alt="`${exInfo.exercisename} 썸네일이미지`">
        </div>
        <span class="pos-tag">{{ exInfo.part }}</span>
        <span class="pos-tag">{{ exInfo.category }}</span>
      </div>
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
  box-shadow: 0px 1px 15px 1px #99a6ff21;
}
.pos-title {
  font-size: 1.4rem;
}
.post-img {
  width: 190px;
  height: 130px;
  margin: 23px 10px 30px auto;
}
.post-img > img {
  width: 100%;
  height: 100%;
}
.pos-tag {
  background-color: $light-color;
  color: $black-color;
  border-radius: 3px;
  margin-right: 5px;
  font-size: 0.9rem;
  padding: 3px 12px;
}
.pos-div:active, .pos-div:hover {
  transform:scale(1.045);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}

</style>