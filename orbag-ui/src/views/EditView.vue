<template>
  <configuration-item-editor v-if=" ci != undefined" :value="ci"/>
</template>
<script lang="ts">

import {ConfigurationItemReference, loadReference} from "@/framework/reference"
import ConfigurationItemEditor from '@/components/ConfigurationItemEditor.vue';

export default {
  components: { ConfigurationItemEditor },
    data() {
        return {
          ci: undefined as ConfigurationItemReference | undefined
        }
    },  
    computed: {
      configurationItemType():string {
          return this.$route.params.configurationItemType;
      },
      configurationItemId():string {
          return this.$route.params.configurationItemId;
      }
      
    },
    mounted() {
      loadReference(this.configurationItemId,this.configurationItemType).then(
        (ci) => this.ci = ci
      )
    },
}
</script>

