// Navbar
<template>
  <div id="NavRoot">
    <!-- <img src="@/assets/runner.png" style="width:40px;" alt=""> -->
    <div class="logo"><router-link to="/">우리집PT코치</router-link></div>
    <div class="menus">
      <div class="menu-item">
        <router-link to="/course">코스 운동</router-link>
      </div>          
      <div class="menu-item">          
        <router-link to="/posture">자세 교정</router-link>
      </div>             
      <div class="menu-item">           
        <router-link to="/dashboard">대시 보드</router-link>
      </div>    
    </div>
    <div class="login-btn">   
      <router-link to="/user/login">로그인</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Nav',

}
</script>

<style lang="scss" scoped>
@import "../../styles/common.scss";

#NavRoot {
  // background-color: #ffffff;
  background-color: #dadfd9;
  // background: linear-gradient(to right, #816AE2, #9181F2);
  background: linear-gradient(to right, #b293ff, #32cafe);
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10000;
  box-shadow: 1px 1px 7px 3px rgba(207, 207, 207, 0.479);
}
#NavRoot .logo a {
  font-size: 28px;
  font-weight: bold;
  margin-left: 40px;
  text-decoration: none;
  position: absolute;
  top: 15px;
  left: 8px;
  color: $logo-color;
  // color: #3c6135;y
  color: rgb(80, 0, 184);
  display: inline-block;
}
#NavRoot .menus {
  padding-top: 25px;
  padding-bottom: 20px;
  position: relative;
  display: flex;
  left: 300px;
  width: 300px;
  justify-content: space-between;
  font-family: 'SBAggroL';
}
#NavRoot .menu-item a {
  text-decoration: none;
  font-size: 1.1rem;
  color: $black-color;
  color: #fff;
  // color: #3c6135;
  // color: rgb(88, 0, 202);
  border-bottom: 6px solid transparent;
}

#NavRoot .menu-item a:focus,
#NavRoot .menu-item a:active,
#NavRoot .menu-item a:hover {
  border-bottom-color: $yellow-color;
}
#NavRoot .login-btn a {
  border-radius: 30px;
  // border: .7px solid $dark-color;
  border: .7px solid rgb(224, 224, 224);
  padding: 6px 12px;
  display: inline-block; 
  position: absolute;
  text-decoration: none;
  top: 22px;
  right: 40px;
  font-size: 0.85rem;
  color: $black-color;
  color: #fff;
  // font-family: 'SBAggroL';
}
</style>