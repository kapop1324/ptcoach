<template>
    <div id="StepRoot">
      <div class="step-box">
        <div class="step">step {{num}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: "Step",
  props: {
    num: Array,
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.step-box {
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
.step {
  padding-top: 6px;
}

</style>
