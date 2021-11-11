<template>
  <div class='contents-box'>
    <vc-calendar id="calendar" :attributes="attrs" @dayclick='dayClicked'/>
    <div class='accordion-box'>
      <div v-for="(course, i) in dailyRecords" :key="i">
        <div class='course-box'>{{course.course_name}}</div>  
        <div class='exercise-box' v-for="(exercise, idx) in course.exercises" :key="idx">
            운동명: {{exercise.exercisename}}
            운동시간: {{exercise.time}}
            운동정확도: {{exercise.accuracy}}
            운동세트: {{exercise.set}}
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserApi from '../../api/UserApi';
// import {BadgerAccordion, BadgerAccordionItem} from 'vue-badger-accordion'

export default {
  // components: {
  //   BadgerAccordion,
  //   BadgerAccordionItem,
  // },
  data() {
    return {
      myRecords: [],
      dailyRecords: [],
      selectedDay: null,
      attrs: [
        {
          key: '',
          highlight: {
            color: '',
            fillMode: '',
            contentClass: '',
          },
          dates:'',
        },
      ],
    };
  },
  created() {
    UserApi.MyRecord(
      this.email,
      res => {
        if(res.data.message == "success"){
          
          this.myRecords = res.data.myRecord;
          this.getDates();
          // this.$store.dispatch("fetch_my_records", res.data.myRecord);
        }else if(res.data.message == "fail"){
          alert("아직 한 번도 운동을 하지 않았어요!");
        }else{
          alert("운동 기록 로드 실패");
        }
      },
      error => {
        alert("에러발생");
        console.log(error);
      }
    )
  },
  computed: {
    email() {
      return this.$store.getters.getLoginEmail;
    },
  },
  methods: {
    getDates() {
      for (let i=0; i<this.myRecords.length; i++){
        const record = this.myRecords[i].split('-');
        var date = (new Date(Number(record[0]),Number(record[1])-1,Number(record[2])));
        this.attrs.push({
            key: this.myRecords[i],
            highlight: {
              color: 'purple',
              fillMode: 'solid',
              contentClass: 'italic',
            },
          dates:date,});
      }
    },
    dayClicked(day) {
      const selectedDate = day.date;
      console.log(this.myRecords)
      for (let i=0; i<this.attrs.length; i++){
        var attr = this.attrs[i];
        if(attr.dates.valueOf() === selectedDate.valueOf()){
          this.selectedDay = attr.key;
          this.getDailyRecord();
          break;
        }
      }
      this.selectedDay = selectedDate;
      this.dailyRecords = [];
      // console.log(this.selectedDay)
    },
    getDailyRecord(){
      let data = {
                email : this.email,
                date: this.selectedDay,
            }
            // console.log(data)
      UserApi.DailyRecord(
      data,
      res => {
        if(res.data.message == "success"){
          // console.log(res.data.dailyRecord);
          this.dailyRecords = res.data.dailyRecord;
          //
        }else if(res.data.message == "fail"){
          alert("운동 기록 로드 실패");
        }else{
          alert("운동 기록 로드 실패");
        }
      },
      error => {
        alert("에러발생");
        console.log(error);
      }
    )
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/styles/common.scss";
.contents-box{
  width: 80%;
}

#calendar {
  width: 100%;
  height: 100%;
  margin: 0px 0px 30px 0px;
}

// .accordion-box {
//   border-radius: 15px;
// }

.accordion-box div{
  height: 30px;
}

.course-box {
  background: #b3b3ff;
  font-size: 20px;
  padding: 10px;
  border-radius: 15px 15px 0px 0px;
}

.exercise-box{
  background: white;
  padding: 10px;
  border-bottom: 1px solid gray;
}
</style>