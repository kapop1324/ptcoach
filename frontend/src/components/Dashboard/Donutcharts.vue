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
  // props: {
  //   donutdata: Array,
  // },
  data: function() {
    return {
      chartOptions: {
        colors: ['#00E396', '#FFB019', '#008FFB'],
        labels: ['상체', '하체', '전신'],
      },
      // series: this.bardata.donutdata,
      series: [30, 40, 20],
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>
