// 자세교정 선택 페이지
<template>
  <div id="posture">
    <div class="title">
      부족한 운동을 선택하고<br>집중적으로 교정해보세요
    </div>

    <div class="card">
      <div class="pos-div" v-for="(exercise, idx) in exerciseList" :key="idx">
        <PostureItem :exInfo="exercise" />
      </div>
    </div>
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
    Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo inventore in dolor doloremque, soluta libero officia. Nesciunt quidem maxime, modi tenetur corrupti numquam et. Modi eum excepturi atque placeat eligendi.
  </div>
</template>

<script>
import PostureItem from "@/components/PostureItem.vue"

export default {
  name: 'PostureLists',
  components: {
    PostureItem,
  },
  data (){
    return {
      exerciseList: [
        {id:1, name: '스쿼트', part: ['하체', '근력']},
        {id:2, name: '숄더 프레스', part: ['상체', '근력']},
        {id:3, name: '스탠딩 킥백', part: ['하체', '근력', '대퇴근']},
        {id:4, name: '스탠딩 사이드 크런치', part: ['전신', '유산소']},
        {id:5, name: '런지', part: ['하체', '근육']},
        {id:6, name: '스탠딩 토터치', part: ['전신', '근력', '점프']},
      ],
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

#posture {
  // background-color: $yellow-color;
}

ul {
	padding: 0;
	margin: 0;
}
li {
	list-style: none;
}
.title {
  margin-top: 30px;
  margin-bottom: 70px;
  font-size: 1.7rem;
  // font-weight: bold;
  line-height: 40px;
}
.card {
  display: flex;
}
.pos-div {
  display: inline-block;
  flex-direction: row;
  // padding: 20px;
  flex-wrap: wrap;
  justify-content: space-between;
}

</style>