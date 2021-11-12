import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import axios from 'axios'
import AxiosPlugin from 'vue-axios-cors';
import VueApexCharts from 'vue-apexcharts'
import VCalendar from 'v-calendar'

Vue.use(AxiosPlugin);
axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

Vue.config.productionTip = false
Vue.use(VueApexCharts)
Vue.use(VCalendar, {
  componentPrefix: 'vc',
} )

Vue.component('apexchart', VueApexCharts)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
