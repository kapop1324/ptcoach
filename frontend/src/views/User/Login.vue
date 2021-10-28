// 로그인 페이지
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
      <button type="submit" @click="login">로그인</button>
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
    login(){
      
      let data = {
        email : this.email,
        password : this.password,
        age: 0,
        gender: "",
        height: 0,
        name: "",
        time: "",
        weight: 0
      };

      UserApi.Login(
          data,
          res => {
            if(res.data.message == "success"){

              this.$store.state.userEmail = data.email
              alert("로그인 성공");
              this.$router.push({name:'Home'});
            }else if(res.data.message == "fail"){
              alert("로그인 실패");
          
            }else{
              alert("로그인 실패");

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