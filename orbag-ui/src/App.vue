<template>
  <div id="app">
    <top-bar/>    
    <b-card overlay>
      <b-card-body overlay>
        <router-view :key="$route.path"/>
        <action-submitter/>
      </b-card-body>
    </b-card>
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

export default {
  components: {
    TopBar,
    ActionSubmitter
  },
  mounted() {
    const router = this.$router as VueRouter;
    subscribeSmartEditConfigurationItem( (ci:ConfigurationItemReference) => {
        router.push("/edit/"+ci.configurationItemType+"/"+ci.identifier);
      });
  }
}
</script>
