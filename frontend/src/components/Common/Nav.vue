// Navbar
<template>
  <div id="NavRoot">
    <div class="logo"><router-link to="/"><img src="@/assets/logo2.png" style="height:47px" alt=""></router-link></div>
    <div class="menus">
      <div class="menu-item" @click="reload()">
        <router-link to="/course">코스 운동</router-link>
      </div>          
      <div class="menu-item"  @click="reload()">          
        <router-link to="/posture">자세 교정</router-link>
      </div>             
      <div class="menu-item"  @click="reload()">           
        <router-link to="/dashboard">대시 보드</router-link>
      </div>    
    </div>
    <div v-if="!isLogin" class="login-btn">   
      <router-link to="/user/login">로그인</router-link>
    </div>
    <div v-if="isLogin" class="logout-btn" @click="logout">   
      로그아웃
    </div>
    <div v-if="isLogin" class="mypage-btn">   
      <router-link to="/mypage"><img src="@/assets/userinfo.png" style="height:32px" alt=""></router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Nav',
  methods: {
    logout() {
      this.$store.dispatch("logout");
      this.$router.push({name:'Login'});
    },
    reload() {
      location.reload();
    },    
  },
  computed: {
    isLogin() {
      return this.$store.getters.getIsLogin;
    },
  },
}
</script>

<style lang="scss" scoped>
@import "../../styles/common.scss";

#NavRoot {
  background: linear-gradient(to right, #b293ff, #32cafe);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10000;
  height: 70px;
  // box-shadow: 1px 1px 7px 3px rgba(207, 207, 207, 0.479);
}
#NavRoot .logo a {
  margin-left: 8vw;
  text-decoration: none;
  position: absolute;
  top: 8px;
  left: 8px;
  color: rgb(80, 0, 184);
  display: inline-block;
}
#NavRoot .menus {
  padding-top: 32px;
  padding-bottom: 20px;
  position: relative;
  display: flex;
  left: 340px;
  width: 300px;
  justify-content: space-between;
  font-family: 'SBAggroL';
}
#NavRoot .menu-item a {
  text-decoration: none;
  font-size: 1rem;
  color: $black-color;
  color: #fff;
  border-bottom: 4px solid transparent;
}

#NavRoot .menu-item a:focus,
#NavRoot .menu-item a:active,
#NavRoot .menu-item a:hover {
  border-bottom-color: #dde0ff;
  // font-weight: bold;
}

#NavRoot .login-btn a {
  border-radius: 30px;
  border: .7px solid #F7F8FC;
  padding: 6px 12px;
  display: inline-block; 
  position: absolute;
  text-decoration: none;
  top: 22px;
  right: 8vw;
  font-size: 0.85rem;
  color: $black-color;
  color: #fff;
}

#NavRoot .logout-btn{
  border-radius: 30px;
  border: .7px solid #F7F8FC;
  padding: 6px 12px;
  display: inline-block; 
  position: absolute;
  text-decoration: none;
  top: 22px;
  right: 8vw;
  font-size: 0.85rem;
  color: $black-color;
  color: #fff;
  cursor: pointer;
}

#NavRoot .mypage-btn{
  display: inline-block;
  position: absolute;
  top: 22px;
  right: 14vw;
}
</style>