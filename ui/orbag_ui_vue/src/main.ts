import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { initAuthenticationFilter } from './framework/authentication'
import { loadConfig } from './framework/client'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.config.productionTip = false

loadConfig(()=> {
initAuthenticationFilter(router);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
});
