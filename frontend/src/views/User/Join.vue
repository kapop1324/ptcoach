<template>
  <div>
    <div class="joinForm">
      <h2>회원가입</h2>
      <div class="midForm">
        <div class="left">
          <label>이메일</label>
        </div>
        <div class="idForm">
          <input v-model="email" placeholder="이메일을 입력하세요" type="text">
          <button class="check-btn" type="button" @click="idcheck">중복확인</button>
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>비밀번호</label>
        </div>
        <div class="inputForm">
          <input v-model="password" placeholder="비밀번호를 입력하세요" type="password">
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>이름</label>
        </div>
        <div class="inputForm">
          <input v-model="name" placeholder="이름을 입력하세요" type="text">
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>나이</label>
        </div>
        <div class="inputForm">
          <input v-model="age" placeholder="나이를 입력하세요" type="number" min="1">
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>성별</label>
        </div>
        <div class="inputForm">
          <select v-model="gender">
            <option value="" disabled>성별을 선택하세요.</option>
            <option value="남자">남자</option>
            <option value="여자">여자</option>
          </select>
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>키</label>
        </div>
        <div class="inputForm">
          <input v-model="height" placeholder="키를 입력하세요" type="number" min="1">
        </div>
      </div>
      <div class="midForm">
        <div class="left">
          <label>몸무게</label>
        </div>
        <div class="inputForm">
          <input v-model="weight" placeholder="몸무게를 입력하세요" type="number" min="1">
        </div>
      </div>
      <div>
        <button class="btn" type="submit" @click="join">가입 하기</button>
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
        weight: 0,
        idpossible : false,
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
      if(this.idpossible == false){
        alert("이메일 중복확인을 해주세요")
      }else{
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
    idcheck(){
      let data = {
        email : this.email
      }
      UserApi.IdCheck(
          data,
          res => {
            if(res.data.message == "success"){
              alert("사용 가능");
              this.idpossible = true;
            }else if(res.data.message == "fail"){
              alert("사용 불가");
            }else{
              alert("사용 불가");
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

.joinForm {
  position:absolute;
  display: block;
  width:400px;
  height:600px;
  // padding: 30px 20px;
  // margin-bottom: 20px;
  background-color:#FFFFFF;
  text-align:center;
  top:60%;
  left:50%;
  transform: translate(-50%,-50%);
  border-radius: 15px;
  padding: 0px 20px;
}

.joinForm h2{
  text-align: center;
  margin: 30px;
}

.midForm{
  display: flex;
  // height: 62px;
  // justify-content: space-between;
  padding: 15px 10px;
  margin: 5px;
  text-align: left;
}

.left{
  width: 130px;
}

.inputForm {
  width: 100%;
  border-bottom: 2px solid #adadad;
}

.inputForm input{
  width: 100%;
  height: 100%;
  border:none;
  outline:none;
}

.idForm {
  width: 100%;
}

.idForm input{
  width: 70%;
  height: 100%;
  border:none;
  outline:none;
  border-bottom: 2px solid #adadad;
}

.check-btn {
  background: $light-color;
  border: none;
  border-radius: 4px;
  padding: 2px 10px;
  margin:2px 4px;
  cursor: pointer;
  font-family: 'SBAggroL';
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
</style>