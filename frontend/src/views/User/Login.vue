// 로그인 페이지
<template>
  <div>
    <div class="loginForm">
      <h2>로그인</h2>
      <div class="idForm">
        <input class="id" v-model="email" placeholder="이메일" type="text">
      </div>
      <div class="passForm">
        <input class="pw" v-model="password" placeholder="비밀번호" type="text">
      </div>
      <button type="submit" class="btn" @click="login">
        LOG IN
      </button>
      <div class="bottomText">
        <router-link class="r-link" :to="{name: 'Join'}">회원가입</router-link>
      </div>
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
              this.$store.dispatch("login",data.email);
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

<style lang="scss" scoped>
@import '@/styles/common.scss';

*{
  margin: 0px;
  padding: 0px;
  text-decoration: none;
}

.loginForm {
  position:absolute;
  width:300px;
  height:400px;
  // padding: 30px 20px;
  background-color:#FFFFFF;
  text-align:center;
  top:50%;
  left:50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
}

.loginForm h2{
  text-align: center;
  margin: 30px;
}

.idForm{
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}

.passForm{
  border-bottom: 2px solid #adadad;
  margin: 30px;
  padding: 10px 10px;
}

.id {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.pw {
  width: 100%;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#81ecec,#6c5ce7,#81ecec);
  background-position: left;
  background-size: 200%;
  color:white;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
  font-family: 'SBAggroL';
}

.btn:hover {
  background-position: right;
}

.bottomText {
  text-align: center;
}

</style>