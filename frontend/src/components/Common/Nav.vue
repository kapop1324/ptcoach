// Navbar
<template>
  <div id="NavRoot">
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
  // display: flex;
  // align-items: center;
  background-color: #ffffff;
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
}
#NavRoot .menu-item a {
  text-decoration: none;
  font-size: 1.1rem;
  color: $black-color;
  border-bottom: 6px solid transparent;
}

#NavRoot .menu-item a:focus,
#NavRoot .menu-item a:active,
#NavRoot .menu-item a:hover {
  border-bottom-color: $yellow-color;
}
#NavRoot .login-btn a {
  border-radius: 30px;
  border: .7px solid $dark-color;
  padding: 6px 12px;
  display: inline-block; 
  position: absolute;
  text-decoration: none;
  top: 22px;
  right: 40px;
  font-size: 0.85rem;
  color: $black-color;
}
</style>