// 대시보드
<template>
  <div>
    <!-- 총 운동시간 - 한달이랑 전체 누적 숫자로 (a) -->
    <div>총 운동시간(누적): 8시간</div>
    <div>총 운동시간(이번달{month}): 2시간 14분</div>
    <br>

    <div class="bg-box">
      운동별 자세 정확도 평균(한달)+최근정확도 퍼센트:<Barcharts />
    </div>
    <div class="bg-box">
      부위별 운동량, 운동시간 통계:<Donutcharts />
    </div>
    <div class="bg-box">
      운동별 정확도 변경 추이 (한달 내 일자별):<Linecharts />
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

.bg-box {
  box-shadow: 0px 1px 15px 1px #99a6ff21;
  background-color: #ffffff;
  // background-color: #343E59;
  // color: #CCCCCC;
  border-radius: 20px;
  // width: 30%;
  padding: 30px;
  // padding: 25px 50px 5px 30px;
  // display: inline-block;
  // min-width: 280px;
}


</style>