<template>
    <div id="VedioRoot">
      <div class="VedioBox">
      </div>
    </div>
</template>

<script>
export default {
  name: "Vedio",
  props: {
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";


.VedioBox {
  width: 32%;
  height: 64%;  
  top: 22%;
  left: 12%;
  position: absolute;
  border: solid black;
  margin: 10px;
}
</style>
