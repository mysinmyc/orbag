<template>

  
  <div id="app">
    <span v-if="logged">
    <top-bar/>    
    <b-card overlay>
      <b-card-body overlay>
        <router-view :key="$route.path"/>
        <action-submitter/>
      </b-card-body>
    </b-card>
    </span>
    <span v-else>
      <b-card-body overlay>
      <login-form/>
    </b-card-body>
    </span>
  </div>


</template>

<style>

</style>
<script lang="ts">

import TopBar from "@/components/TopBar.vue"
import { smartEditConfigurationItem, subscribeSmartEditConfigurationItem } from "./framework/smartDispatcher";
import { ConfigurationItemReference } from './framework/reference';
import VueRouter from 'vue-router';
import ActionSubmitter from './components/ActionSubmitter.vue';
import { initAuthenticationFilter, LoginEvent, subscribeLoginEvent } from './framework/authentication';
import LoginForm from './components/LoginForm.vue';

export default {
  components: {
    TopBar,
    ActionSubmitter,
    LoginForm
  },
  data() {
    return {
      logged: false
    }
  },
  mounted() {
    const router = this.$router as VueRouter;
    subscribeSmartEditConfigurationItem( (ci:ConfigurationItemReference) => {
        router.push("/edit/"+ci.configurationItemType+"/"+ci.identifier);
      });
    subscribeLoginEvent( (loginEvent:LoginEvent) => {
      if (loginEvent.logged!= this.logged) {
        this.logged = loginEvent.logged;
      }
    } );
  }
}
</script>
