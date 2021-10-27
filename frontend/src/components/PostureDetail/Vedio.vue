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
  width: 35%;
  height: 70%;  
  top: 15%;
  left: 5%;
  position: absolute;
  border: solid black;
  margin: 10px;
}
</style>
