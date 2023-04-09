<template>
    <span>
    <my-modal-container v-if="showSearchCi">
      <template #header>
        Select
      </template>
      <template #body>
        <search-ci :configurationItemType="field.configurationItemType" @selectedci="(ci)=>onChangedCi(ci)"/>
      </template>
    </my-modal-container>        
    <b-link v-if="currentValue!=undefined" @click="onClickCi">
        <b-icon icon="link"/>
        {{ currentValue?.displayLabel}}
    </b-link>
    <b-button @click="doSearch">Change</b-button>
    </span>
</template>

<script lang="ts">
  
import { ConfigurationItemReferenceField} from '@/framework/input'
import SearchCi from './SearchCi.vue';
import MyModalContainer from './MyModalContainer.vue';
import { ConfigurationItemReference } from '@/framework/reference';
import { smartEditConfigurationItem } from '@/framework/smartDispatcher';

export default {
  components: { MyModalContainer,SearchCi },
    props: {
        field: {
            type: Object as () => ConfigurationItemReferenceField,
            required: true
        },
    },
    data()  {
        return {
            currentValue: this.field.value,
            showSearchCi: false
        }
    },
    methods: {
      doSearch() {
        this.showSearchCi=true;
      },
      onClickCi(){
        smartEditConfigurationItem(this.currentValue);
      },
      onChangedCi(ci:ConfigurationItemReference) {
        this.showSearchCi=false;
        this.currentValue = ci;
        this.$emit("selectedci", this.currentValue)
      }
    }
}
</script>

