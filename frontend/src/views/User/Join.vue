<template>
  <div>
    <div>
      <div>
        <label>EMAIL</label>
      </div>
      <div>
        <input v-model="email" placeholder="이메일 입력하세요" type="text">
      </div>
    </div>
    <div>
      <div>
        <label>PASSWORD</label>
      </div>
      <div>
        <input v-model="password" placeholder="비밀번호를 입력하세요" type="text">
      </div>
    </div>
    <div>
      <div>
        <label>이름</label>
      </div>
      <div>
        <input v-model="name" placeholder="이름을 입력하세요" type="text">
      </div>
    </div>
    <div>
      <div>
        <label>나이</label>
      </div>
      <div>
        <input v-model="age" placeholder="나이를 입력하세요" type="number">
      </div>
    </div>
    <div>
      <div>
        <label>성별</label>
      </div>
      <div>
        <input v-model="gender" placeholder="성별을 입력하세요" type="text">
      </div>
    </div>
    <div>
      <div>
        <label>키</label>
      </div>
      <div>
        <input v-model="height" placeholder="키를 입력하세요" type="number">
      </div>
    </div>
    <div>
      <div>
        <label>몸무게</label>
      </div>
      <div>
        <input v-model="weight" placeholder="몸무게를 입력하세요" type="number">
      </div>
    </div>
    <div>
      <button type="submit" @click="join">회원가입</button>
    </div>
  </div>
</template>

<script>

import UserApi from "../../api/UserApi"

export default {
  data() {
    return {
        age: 0,
        email: "",
        gender: "",
        height: 0,
        name: "",
        password: "",
        time: "",
        weight: 0
    }
  },
  methods: {
    join(){
      
      let data = {
        email : this.email,
        password : this.password,
        age: this.age,
        gender: this.gender,
        height: this.height,
        name: this.name,
        time: "",
        weight: this.weight
      };

      UserApi.Join(
          data,
          res => {
            if(res.data.message == "success"){

              alert("가입 성공");
              this.$router.push({name:'Login'});
            }else if(res.data.message == "fail"){
              alert("가입 실패");
          
            }else{
              alert("가입 실패");

            }
          },
          error => {
            alert("에러발생");
            console.log(error);
          }
        );

    }
  },

}

</script>

<style scoped>

</style>