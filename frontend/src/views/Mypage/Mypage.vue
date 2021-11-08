// 마이페이지
<template>
  <div class='mypage-contents'>
    <!-- 왼쪽에 userInfo 컴포넌트-->
    <div class='left'>
      <user-info></user-info>
    </div>
    <!-- 오른쪽에 Calendar 컴포넌트 -->
    <div class='right'>
      <calendar id='calendar'></calendar>
    </div>

  </div>
</template>

<script>
import UserInfo from '@/components/Mypage/UserInfo.vue'
import Calendar from '@/components/Mypage/Calendar.vue'

export default {
  name: 'Mypage',
  components:{
    UserInfo,
    Calendar,
  },
  
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

.mypage-contents{
  display: flex;
  min-width: 1200px;
  min-height: 800px;
}

.left{
  width: 30%;
  padding: 10px;
}

.right{
  width: 60%;
  height: 50%;
  padding: 10px;
}

#calendar {
  width: 600px;
  height: 600px;
}

// .vc-weeks {
//   min-width: 400px;
// }
</style>