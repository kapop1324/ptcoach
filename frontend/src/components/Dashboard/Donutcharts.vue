<template>
  <div>
    <apexchart width="500" height="350" type="donut" :options="chartOptions" :series="series"></apexchart>

  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: "Donutcharts",
  components:{
    apexchart: VueApexCharts,
  },
  data: function() {
    return {
      chartOptions: {
        chart: { id: "basic-bar"},
        xaxis: { categories: ["이번달 평균", "가장 최근(언제인지 적어야할거같은데..)",] }
      },
      series: [30, 12, 39, 20,]
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>
