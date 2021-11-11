<template>
    <div class='userinfo-box'>
        <div>
            {{name}} 님의 정보
        </div>
        <div class='userinfo-img-box'> </div>
        <div class='midForm'>
            <div class='left'>
                <label>이메일</label>
            </div>
            <div class='right'>
                {{email}}
            </div>
        </div>
        <div class='midForm'>
            <div class='left'>
                <label>비밀번호</label>
            </div>
            <div class='right'>
                {{password}}
            </div>
        </div>
        <div class='midForm'>
            <div class='left'>
                <label>이름</label>
            </div>
            <div class='right'>
                {{name}}
            </div>
        </div>
        <div class='midForm'>
            <div class='left'>
                <label>나이</label>
            </div>
            <div class='right'>
                {{age}}
            </div>
        </div>
        <div class='midForm'>
            <div class='left'>
                <label>성별</label>
            </div>
            <div class='right'>
                {{gender}}
            </div>
        </div>
        <div div class='midForm'>
            <div class='left'>
                <label>키</label>
            </div>
            <div class='right'>
                {{height}}
            </div>
        </div>
        <div class='midForm'>
            <div class='left'>
                <label>몸무게</label>
            </div>
            <div class='right'>
                {{weight}}
            </div>
        </div>
        <div>
            <!-- <button type="button" @click="modify">수정</button> -->
            <button type="button" @click="leave">탈퇴</button>
        </div>
    </div>
</template>

<script>

import UserApi from "../../api/UserApi"
import { mapState } from 'vuex'
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
    created() {
        // console.log(this.userEmail )
        let data = {
            email : this.$store.state.userEmail,
        };
        UserApi.UserInfo(
            data,
            res => {
            if(res.data.message == "success"){
                this.email = res.data.userinfo.email;
                this.age = res.data.userinfo.age;
                this.name = res.data.userinfo.name;
                this.height = res.data.userinfo.height;
                this.weight = res.data.userinfo.weight;
                this.gender = res.data.userinfo.gender;
                this.time = res.data.userinfo.time;
            }else if(res.data.message == "fail"){
                alert("정보불러오기 실패");
            }else{
                alert("정보불러오기 실패");
            }
            },
            error => {
                alert("에러발생");
                console.log(error);
            }
        );
    },
    methods: {
        leave(){
            let data = {
                email : this.email,
            }
            UserApi.Leave(
                data,
                res => {
                if(res.data.message == "success"){
                    alert("정상적으로 탈퇴되었습니다.");
                    this.$router.push({name:'Home'});
                }else if(res.data.message == "fail"){
                    alert("탈퇴 실패");
                }else{
                    alert("탈퇴 실패");
                }
                },
                error => {
                    alert("에러발생");
                    console.log(error);
                }
            );
        },       
    },
    computed: {
    ...mapState([
        'userEmail',
    ]),
    }
}
</script>

<style lang="scss" scoped>
@import '@/styles/common.scss';

*{
  text-decoration: none;
}

.userinfo-box {
  width: 100%;
  position:absolute;
  display: block;
  width:30%;
  background-color:#FFFFFF;
  text-align:center;
  border-radius: 15px;
  padding: 10px 20px;
}

.midForm{
  display: flex;
  width: 90%;
  padding: 15px 10px;
  margin: 5px;
  text-align: left;
}

.left{
  width: 130px;
}

.right {
  width: 100%;
  border-bottom: 2px solid #adadad;
}
</style>