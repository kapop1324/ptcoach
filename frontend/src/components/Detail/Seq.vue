<template>
    <div @click="changeStep" id="seqRoot"> 
      <!--seq-box쓰면 에러남-->
      <div :class="{seqbox:true, sel:num==selected}">
        <div class="seq"> 운동 {{num}}</div>
      </div>
    </div>
</template>

<script>
export default {
  name: "Seq",
  props: {
    num: Number,
    selected: Number,
  },
  methods: {
    changeStep() {
      let i = this.num
      console.log(i)
      this.$emit('change-step', i)
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";


.seqbox {
  top: 15%;
  height: 40px;
  width: 120px;
  right: 5%;
  border-radius: 30px;
  background-color: $light-color;
  font-size: 22px;
  text-align: center;
  margin: 20px;
  cursor: pointer;
}
.seq {
  padding-top: 5px;
}
.sel {
  background-color: $logo-color;
  color: white;
}

</style>
