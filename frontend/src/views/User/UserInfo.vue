<template>
    <div>
        <div>
            <div>
                <label>EMAIL</label>
            </div>
        <div>
            {{email}}
        </div>
        </div>
        <div>
            <div>
                <label>PASSWORD</label>
            </div>
            <div>
                {{password}}
            </div>
        </div>
        <div>
            <div>
                <label>이름</label>
            </div>
            <div>
                {{name}}
            </div>
        </div>
        <div>
            <div>
                <label>나이</label>
            </div>
            <div>
                {{age}}
            </div>
        </div>
        <div>
            <div>
                <label>성별</label>
            </div>
            <div>
                {{gender}}
            </div>
            <div>
                <label>키</label>
            </div>
            <div>
                {{height}}
            </div>
        </div>
        <div>
            <div>
                <label>몸무게</label>
            </div>
            <div>
                {{weight}}
            </div>
        </div>
        <div>
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

        console.log(this.userEmail )

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

<style scoped>

</style>