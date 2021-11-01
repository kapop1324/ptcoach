<template>
  <div>
    <apexchart width="500" height="350" type="bar" :options="chartOptions" :series="series"></apexchart>

  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: "Barcharts",
  components:{
    apexchart: VueApexCharts,
  },
  data: function() {
    return {
      // chart: {
      //   chartOptions: {
      //     chart: { id: "basic-bar"},
      //     xaxis: { categories: ["이번달 평균", "가장 최근(언제인지 적어야할거같은데..)",] }
      //   },
      //   series: [{ name: "value", data: [73, 88] } ]
      // },
      chartOptions: {
        chart: { id: "basic-bar"},
        xaxis: { categories: ["이번달 평균", "가장 최근(언제인지 적어야할거같은데..)",] },
        yaxis: { title: { text: "운동 정확도 (%)" }},
      },
      series: [{ name: "운동 정확도(%)", data: [73, 88] } ]
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>
