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
import http from '@/util/http-common';

export default {
  name: 'PostureLists',
  components: {
    PostureItem,
  },
  data (){
    return {
      exerciseList: [],
    }
  },
  created() {
   this.getPostureList()
  },
  methods: {
    getPostureList() {
      http.get('/exercise/list')
      .then(res => {
        console.log(res.data)
        this.exerciseList = res.data.exercise_list
      })
      .catch(err => {
        console.log(err)
        console.log('자세교정 에러')
      })
    }
  },
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