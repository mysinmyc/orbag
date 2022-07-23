<template>
  <div id="app">
    <top-bar/>    
    <b-card overlay>

      <b-card-body overlay>
          <router-view :key="$route.path"/>
      </b-card-body>
    </b-card>
  </div>
</template>

<style>

</style>
<script lang="ts">

import TopBar from "@/components/TopBar.vue"

export default {
  components: {
    TopBar
  }
}
</script>
