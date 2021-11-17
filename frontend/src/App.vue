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
  data: () => {
    return {
      check: false,
    };
  },  
  components:{
    Nav,
    Footer,
  },
  watch:{
    $route(to,from){
        if (to.path != from.path) {
          location.reload();
      }
    },
  }
}
</script>

<style lang="scss">
@import "./styles/common.scss";

@font-face {
  font-family: 'SBAggroL';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroL.woff') format('woff');
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
  background-color: #F7F8FC;
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
  background: #d8deffe0;
}

</style>