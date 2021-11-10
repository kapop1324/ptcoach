<template>
    <div id="StepRoot">
      <div class="step-con">
        <div v-if="num!=newstep" class="step-box">{{name}}</div>
        <div v-else-if="num==newstep" class="new-step-box">{{name}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: "CourStep",
  props: {
    num: Number,
    name: String,    
    newstep: Number,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.step-box {
  top: 16%;
  height: 35px;
  width: 140px;
  right: 6%;
  border-radius: 30px;
  background-color: white;
  font-size: 18px;
  text-align: center;
  margin: 20px;
  padding-top: 10px;
}
.new-step-box {
  top: 16%;
  height: 35px;
  width: 130px;
  right: 6%;
  border-radius: 30px;
  background-color: $light-color;
  font-size: 18px;
  text-align: center;
  margin: 20px;
  padding-top: 10px;
  transform:scale(1.045);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}
</style>
