<template>
  <div id="app">

    <Nav />
    <div class="content-box">
      <router-view/>
    </div>
    <Footer/>

  </div>
</template>

<script>
import Nav from "@/components/Common/Nav.vue"
import Footer from "@/components/Common/Footer.vue"

export default {
  name: "App",
  components:{
    Nav,
    Footer,
  },
}

</script>

<style lang="scss">
@import "./styles/common.scss";

@font-face {
  font-family: 'SBAggroM';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroM.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'SBAggroL';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroL.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
    font-family: 'S-CoreDream-3Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
  font-family: 'NanumSquareRound';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'NEXON Lv1 Gothic OTF';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'KoPubDotumMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'GmarketSansLight';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: 'TmoneyRoundWindRegular';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

#app {
  font-family: 'GmarketSansMedium';
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: $black-color;
  min-width: 375px;
  // background-color: $light-color;
  background-color: #f6f6f6;
  min-height: 100vh;
}

::-webkit-scrollbar {
  width: 8px;
  height: 8px;
} 
::-webkit-scrollbar-thumb {
  background-color: #ced1df;
  border: 1px solid transparent;
  border-radius: 10px;
} 
::-webkit-scrollbar-track { 
  background-color: #f4f6f6;
}

::selection {
  background: #d8deffcc;
  // background: #ffe8d3;
}

</style>