// 운동별 정확도 변경 추이 (한달 내 일자별) (c)
<template>
  <div>
    <apexchart type="line" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
  name: "Linecharts",
  components:{
    apexchart: VueApexCharts,
  },
  data: function() {
    return {
      series: [
        {
          name: "스쿼트(운동이름)",
          data: [28, 29, 33, 36, 32, 32, 33, 89, 100, 98, 68, 70, 88]
        },
      ],
      chartOptions: {
        chart: {
          dropShadow: {
            enabled: true,
            color: '#000',
            top: 18,
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
            colors: ['#f1f5ff', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          },
        },
        markers: {
          size: 1
        },
        xaxis: {
          categories: ['8/19', '8/20', '8/25', '8/28', '9/1', '9/3', '9/4', '9/5', '9/8', '9/13', '9/14', '9/17', '9/18'],
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
        // legend: {
        //   position: 'top',
        //   horizontalAlign: 'right',
        //   floating: true,
        //   offsetY: -25,
        //   offsetX: -5
        // }
      },



    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";



</style>