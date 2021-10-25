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

#app {
  font-family: $font-family;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: $black-color;
  min-width: 375px;
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
  // background: #eaedff;
  background: $light-color;
}

</style>
