<template>
    <configuration-item-editor v-if=" ci != undefined" :value="ci"/>
</template>
<script lang="ts">

import ConfigurationItemEditor from '@/components/ConfigurationItemEditor.vue';
import { ConfigurationItemReference } from '@/generated/client';
import { myHttpClient } from '@/framework/client';

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
      myHttpClient().referenceApi.getConfigurationItem(this.configurationItemType,this.configurationItemId).then(
        (r) => this.ci = r.data!
      )
    },
}
</script>

