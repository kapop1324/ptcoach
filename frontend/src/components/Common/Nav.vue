// Navbar
<template>
  <div id="NavRoot">
    <div class="logo"><router-link to="/">우리집PT코치</router-link></div>
          <div class="menu-item1">
            <router-link to="/course">코스 운동</router-link>
          </div>          
          <div class="menu-item2">          
            <router-link to="/posture">자세 교정</router-link>
          </div>             
          <div class="menu-item3">           
            <router-link to="/dashboard">대시 보드</router-link>
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
  display: flex;
  align-items: center;
  justify-content: flex-end;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10000;
  //box-shadow: 1px 1px 7px 3px rgba(129, 127, 127, 0.849);
}
#NavRoot .logo a {
  font-family: $kor-font-family;
  font-size: $title-font-size;
  font-weight: bold;
  margin-left: 2vw;
  font-size: 2vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  left: 5vw;
  color: $logo-color;
  display: inline-block;
}

#NavRoot .menu-item1 a {
  font-family: $kor-font-family;  
  margin: 10px;
  font-size: 1.2vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  left: 30vw;
  color: black;
}
#NavRoot .menu-item2 a {
  font-family: $kor-font-family;
  margin: 10px;
  display: inline-block;  
  font-size: 1.2vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  left: 40vw;
  color: black;
}
#NavRoot .menu-item3 a {  
  font-family: $kor-font-family;
  margin: 10px;
  display: inline-block;  
  font-size: 1.2vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  left: 50vw;
  color: black;
}
#NavRoot .menu-item4 a {
  font-family: $kor-font-family;
  margin: 10px;
  display: inline-block;  
  font-size: 1.2vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  right: 15vw;
  color: black;
}
#NavRoot .login-btn a {
  font-family: $kor-font-family;
  border-radius: 15px;
  border: 2px solid $logo-color;
  border-width: 2px; 
  margin: 10px;
  padding: 5px;
  display: inline-block;  
  font-size: 1vw;
  text-decoration: none;
  position: absolute;
  top: 8vh;
  right: 25vw;
  color: black;   
}
</style>