// 대시보드
<template>
  <div>
    <div>총 운동시간(누적): 8시간</div>
    <div>총 운동시간(이번달{month}): 2시간 14분</div>
    <br>
    <div>
      막대:<Barcharts />
      도넛(부위별 운동량, 운동시간 통계):<Donutcharts />
      라인:<Linecharts />
    </div>
  </div>
</template>

<script>
import Barcharts from "@/components/Dashboard/Barcharts.vue"
import Donutcharts from "@/components/Dashboard/Donutcharts.vue"
import Linecharts from "@/components/Dashboard/Linecharts.vue"

export default {
  name: 'Dashboard',
  components: {
    Barcharts,
    Donutcharts,
    Linecharts,
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

</style>