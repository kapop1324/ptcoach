// 메인페이지
<template>
  <div class="home">

    <!-- 1. AI 어쩌구 서비스 전체적 소개글 -->
    <div class="m-title">
      <span>AI 코치</span>와 즐기는 <span>나만의 홈트레이닝</span>
    </div>

    <div class="m-div scale">
      <div class="m-img">
        <img src="@/assets/main1.jpg" alt="메인이미지">
      </div>
      <div class="m-desc">AI 코칭으로 집에서도 똑똑하게</div>
    </div>
    <div class="m-div scale">
      <div class="m-img">
        <img src="@/assets/main2.jpg" alt="메인이미지">
      </div>
      <div class="m-desc">건강한 몸을 향한 투자</div>
    </div>
    <div class="m-div scale">
      <div class="m-img">
        <img src="@/assets/main3.jpg" alt="메인이미지">
      </div>
      <div class="m-desc">우리집 PT코치와 함께 해요</div>
    </div>
      

    <!-- 2-1. 코스운동 사용방법 설명 -->
    <div class="s-container" style="padding-top:250px;">
      <div data-aos="fade-right">
        <div class="s-numbering">01</div>
        <div class="s-title">
          상체, 하체, 전신 운동을<br>코스로 즐겨보세요
        </div>
        <div class="s-desc">
          클릭 한번으로 나만의 PT을 시작할 수 있습니다<br>
        </div>
      </div>
      <div data-aos="fade-left">
        <div class="scren-img">
          <img src="@/assets/main/course.jpg" alt="코스운동 예시 이미지">
        </div>
      </div>
    </div>

    <!-- 2-2. 자세교정 사용방법 설명 -->
    <div class="s-container">
      <div data-aos="fade-right">
        <div class="scren-img">
          <img src="@/assets/main/posture.png" alt="코스운동 예시 이미지">
        </div>
      </div>
      <div data-aos="fade-left">
        <div class="s-numbering">02</div>
        <div class="s-title">
          운동 자세를 바로 잡아보세요
        </div>
        <div class="s-desc">
          트레이너가 없어 자세가 정확한지 걱정된다구요?<br>
          걱정할 필요 없어요!<br>
          AI 기술로 정확한 운동 자세를 만들어갈 수 있습니다
        </div>
      </div>
    </div>

    <!-- 2-3. 대시보드 -->
    <div class="s-container">
      <div data-aos="fade-right">
        <div class="s-numbering">03</div>
        <div class="s-title">
          운동 통계 결과를<br>한 눈에 확인해보세요
        </div>
        <div class="s-desc">
          누적 운동시간과 운동별 운동량, 정확도,<br>변경 추이를 대시보드로 확인할 수 있습니다
        </div>
      </div>
      <div data-aos="fade-left">
        <div class="scren-img">
          <img src="@/assets/main/dashboard.jpg" alt="코스운동 예시 이미지">
        </div>
      </div>
    </div>

    <!-- 2-4. 캘린더 소개 -->
    <div class="s-container">
      <div data-aos="fade-right">
        <div class="scren-img">
          <img src="@/assets/main/mypage.jpg" alt="코스운동 예시 이미지">
        </div>
      </div>
      <div data-aos="fade-left">
        <div class="s-numbering">04</div>
        <div class="s-title">
          달력에서 운동한 날짜와<br>운동 기록을 한 눈에 확인해보세요
        </div>
        <div class="s-desc">
          1일 1운동으로 달력의 색을 채워봐요
        </div>
      </div>
    </div>

    <!-- 2-5. 캐릭터 소개 -->
    <!-- <div class="s-container" style="padding-bottom:350px;">
      <div data-aos="fade-right">
        <div class="s-numbering">05</div>
        <div class="s-title">
          운동량에 따라 변하는<br>캐릭터를 확인할 수 있어요
        </div>
        <div class="s-desc">
          헛둘헛둘 근육짱이 되어봐요
        </div>
      </div>
      <div data-aos="fade-left">
        <div class="scren-img">
          <img src="@/assets/eximg.jpg" alt="코스운동 예시 이미지">
        </div>
      </div>
    </div> -->

    <GoTop />
  </div>
</template>

<script>
import GoTop from "@/components/Common/GoTop.vue"

export default {
  name: 'Home',
  components: {
    GoTop,
  },

}
</script>

<style lang="scss">
@import "@/styles/common.scss";

.m-title {
  font-size: 2.8rem;
  text-align: center;
  margin-top: 70px;
  margin-bottom: 100px;
  font-family: 'GmarketSansLight';
}
.m-title span {
  font-family: 'GmarketSansMedium';
}
.m-div  {
  width: 30%;
  height: 30%;
  display: inline-block;
  margin: 1%;
}
.m-desc {
  font-size: 1.4rem;
  text-align: center;
}
.m-img > img {
  width: 100%;
  height: 100%;
}
.scale {
  transform: scale(1);
  -webkit-transform: scale(1);
  -moz-transform: scale(1);
  -ms-transform: scale(1);
  -o-transform: scale(1);
  transition: all 0.3s ease-in-out;
}
.scale:hover {
  transform: scale(1.06);
  -webkit-transform: scale(1.06);
  -moz-transform: scale(1.06);
  -ms-transform: scale(1.06);
  -o-transform: scale(1.06);
  color: #1A73E8;
}

.s-numbering {
  font-size: 8rem;
  color: #83C6FE;
  // color: #B094FF;
  // color: #9ccfff;
  display: inline-block;
  margin-left: -10px;
  margin-bottom: -15px;
}
.s-title {
  font-size: 2.5rem;
  line-height: 53px;
}
.s-desc {
  font-family: 'GmarketSansLight';
  font-size: 1.4rem;
}
.scren-img {
  width: 480px;
  border-radius: 20px;
  overflow: hidden;
}
.scren-img > img {
  width: 100%;
  height: 100%;
}
.s-container {
  display: flex;
  padding-top: 150px;
  padding-bottom: 150px;
  align-items: center;
  justify-content: space-around;
}

</style>