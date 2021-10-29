// 자세교정 선택 페이지
<template>
  <div id="posturelist">
    <div class="title">
      부족한 운동을 선택하고<br>집중적으로 교정해보세요
    </div>

    <ul>
      <li class="pos-div" v-for="(exercise, idx) in exerciseList" :key="idx">
        <PostureItem :exInfo="exercise" />
      </li>
    </ul>
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
        {id:1, name: '스쿼트', part: ['하체', '근력'], path:"k5c201.p.ssafy.io/images/thumbnail/sidestep.png"},
        {id:2, name: '숄더 프레스', part: ['상체', '근력']},
        {id:3, name: '스탠딩 킥백', part: ['하체', '근력', '대퇴근']},
        {id:4, name: '스탠딩 숄더 프레스', part: ['전신', '유산소']},
        {id:5, name: '런지', part: ['하체', '근육']},
        {id:6, name: '팔벌려 높이뛰기', part: ['전신', '근력', '점프']},
      ],
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";

#posturelist {
  padding-bottom: 10%;
}
ul {
	padding: 0;
	margin: 0;
}
.title {
  margin-top: 30px;
  margin-bottom: 70px;
  font-size: 1.7rem;
  line-height: 40px;
}
.pos-div {
  display: inline-block;
  margin: 0 4% 20px 0;
}

</style>