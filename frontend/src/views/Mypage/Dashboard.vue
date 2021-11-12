// 대시보드
<template>
  <div>
    <div class="box-container flex-align">
      <div class="bg-box time-width">
          <div class="tiem-title">🕑 누적 운동시간 🏋️‍♀️</div>
          <div class="tiem-desc big-navy">{{ alltime }}</div>
          <div class="tiem-title">🕑 최근 한달 운동시간 🏃‍♀️</div>
          <div class="tiem-desc big-navy">{{ alltime }}</div>
      </div>

      <div class="bg-box box-width">
        <div class="box-title">부위별 운동량, 운동시간(초) 통계</div>
        <Donutcharts :donutdata="donutData" />
      </div>

    </div>

    <div class="box-container" v-for="(bardata, idx) in barData" :key="`b-${idx}`">
      <div class="bg-box">
        <div class="box-title"><span class="big-navy">{{ bardata.exercisename }}</span> 운동의 자세 정확도</div>
        <Barcharts :bardata="bardata" />
      </div>
      <div class="bg-box">
        <div class="box-title">최근 한달간 <span class="big-navy">{{ bardata.exercisename }}</span> 운동 정확도 변경 추이</div>
        <Linecharts :linedata="lineData[idx]" />
      </div>
    </div>


  </div>
</template>

<script>
import Barcharts from "@/components/Dashboard/Barcharts.vue"
import Donutcharts from "@/components/Dashboard/Donutcharts.vue"
import Linecharts from "@/components/Dashboard/Linecharts.vue"
import DashboardApi from "../../api/DashboardApi"

export default {
  name: 'Dashboard',
  components: {
    Barcharts,
    Donutcharts,
    Linecharts,
  },
  data: function() {
    return {
      barData: null,
      lineData: null,
      donutData: [],
      alltime: null,
      monthtime: null,
    }
  },
  created() {
   this.getDashboard()
  },
  methods: {
    getDashboard() {
      DashboardApi.GetDashboard(
        res => {
          const secAlltime = res.data.alltime
          const secMontime = res.data.monthtime

          this.alltime = this.secToTime(secAlltime)
          this.monthtime = this.secToTime(secMontime)
          this.barData = res.data.accuracylist
          this.lineData = res.data.accuracytransition
          this.donutData = res.data.partdis
        },
        error => {
          console.log(error)
          console.log('대시보드 에러')
        }
      )
    },
    secToTime(sec) {
      var h = parseInt(sec/3600)
      var m = parseInt((sec%3600)/60)
      var s = parseInt((sec%3600)%60)
      if (h===0 && m===0 && s===0) {
        return "운동 정보가 없어요 😥 운동을 시작해보세요!"
      } else if (h===0 && m===0) {
        return s+"초"
      } else if (h===0) {
        return m+"분 "+s+"초"
      } else {
        return h+"시간 "+m+"분 "+s+"초"
      }
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.bg-box {
  box-shadow: 0px 1px 15px 1px #99a6ff21;
  background-color: #ffffff;
  border-radius: 20px;
  padding: 25px 30px 15px 30px;
  display: inline-block;
  // min-width: 330px;
  margin: 20px;
}
.tiem-title {
  font-size: 1.2rem;
}
.big-navy {
  color: $logo-color;
  font-size: 1.4rem;
}
.tiem-desc {
  margin-bottom: 65px;
  // font-size: 1.5rem;
}
.box-title {
  font-size: 1.2rem;
  margin-bottom: 35px;
}
.box-container {
  min-width: 1200px;
}
.flex-align {
  display: flex;
  align-items: stretch;
}
.box-width {
  width: 550px;
}
.time-width {
  width: 445px;
}

</style>