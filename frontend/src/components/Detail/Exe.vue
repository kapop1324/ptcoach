<template>
    <div id="ExeRoot"> 
      <div class="exe-box">
        <div class="exe"> 운동 {{num}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: "Exe",
  props: {
    num: Number,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";


.exe-box {
  top: 15%;
  height: 40px;
  width: 120px;
  right: 5%;
  border-radius: 30px;
  background-color: $yellow-color;
  font-size: 22px;
  text-align: center;
  margin: 20px;
}
.exe {
  padding-top: 5px;
}

</style>
