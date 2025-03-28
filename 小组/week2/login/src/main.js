import Vue from 'vue'
//import App from './App.vue'
import login from './views/login.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(login)
}).$mount('#app')
