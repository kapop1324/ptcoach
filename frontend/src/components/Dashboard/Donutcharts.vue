//  상체,하체,전신 비중 (d)
<template>
  <div>
    <apexchart width="350" type="donut" :options="chartOptions" :series="series"></apexchart>
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
        colors: ['#00E396', '#FFB019', '#008FFB'],
        labels: ['상체', '하체', '전신'],
      },
      series: [30, 12, 33],
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>
