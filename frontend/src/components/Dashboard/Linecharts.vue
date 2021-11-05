// 운동별 정확도 변경 추이 (한달 내 일자별) (c)
<template>
  <div>
    <apexchart type="line" width="700" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: "Linecharts",
  components:{
    apexchart: VueApexCharts,
  },
  props: {
    linedata: Object,
  },
  data: function() {
    return {
      series: [
        {
          name: this.linedata.exercisename,
          data: this.linedata.accuracy,
        },
      ],
      chartOptions: {
        chart: {
          dropShadow: {
            enabled: true,
            color: '#000',
            top: 15,
            left: 7,
            blur: 10,
            opacity: 0.2
          },
          toolbar: {
            show: false
          }
        },
        colors: ['#77B6EA'],
        dataLabels: {
          enabled: true,
        },
        stroke: {
          curve: 'smooth'
        },
        grid: {
          borderColor: '#e7e7e7',
          row: {
            colors: ['#f1f5ff', 'transparent'],
            opacity: 0.5
          },
        },
        markers: {
          size: 1
        },
        xaxis: {
          categories: this.linedata.date,
          title: {
            text: '날짜'
          }
        },
        yaxis: {
          title: {
            text: '정확도'
          },
          min: 0,
          max: 100
        },
      },
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>