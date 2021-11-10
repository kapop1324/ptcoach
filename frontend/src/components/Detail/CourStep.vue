<template>
    <div id="StepRoot">
      <div class="step-con">
        <div v-if="num!=newstep" class="step-box">운동{{num}}</div>
        <div v-else-if="num==newstep" class="new-step-box">운동{{num}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: "CourStep",
  props: {
    num: Number,
    newstep: Number,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.step-box {
  top: 16%;
  height: 30px;
  width: 120px;
  right: 6%;
  border-radius: 30px;
  background-color: #968989;
  background-color: white;
  font-size: 20px;
  text-align: center;
  margin: 20px;
  padding-top: 8px;
}
.new-step-box {
  top: 16%;
  height: 30px;
  width: 120px;
  right: 6%;
  border-radius: 30px;
  background-color: $light-color;
  font-size: 18px;
  text-align: center;
  margin: 20px;
  padding-top: 8px;
  transform:scale(1.045);
  box-shadow: 0 0 10px #99a6ff21, 0 0 15px #99a6ff21, 0 0 25px #99a6ff21;
}
</style>
