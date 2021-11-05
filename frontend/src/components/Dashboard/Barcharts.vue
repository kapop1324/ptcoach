// 운동별 자세 정확도 평균(한달)+최근정확도 퍼센트 (b)
<template>
  <div>
    <apexchart width="300" height="350" type="bar" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: "Barcharts",
  components:{
    apexchart: VueApexCharts,
  },
  props: {
    bardata: Object,
  },
  data: function() {
    return {
      chartOptions: {
        chart: { id: "basic-bar"},
        xaxis: { categories: ["최근 한달 평균", "가장 최근",] },
        yaxis: { title: { text: "운동 정확도 (%)" }},
        // colors: ['#b293ff'],
      },
      series: [{ name: "운동 정확도(%)", data: [this.bardata.wholeaccuracy, this.bardata.recentaccuracy] }],
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>
