<template>
  <b-card header-tag="header">
    <template #header>
        {{value?.displayLabel}}
    </template>

    <b-card-body>

        <b-dropdown class="mx-1" right text="actions">
            <b-dropdown-item class="m-2" v-for="action in availablableActions" :key="action.identifier" @click="onClickAction(action)">{{action.displayLabel}}</b-dropdown-item>
        </b-dropdown>
        <b-tabs>
    
        <b-tab title="Properties" active>
          <configuration-item-property-editor :value="value"/>
        </b-tab>
    <b-tab v-for="view in availablableViews" :key="view.identifier" :title="view.displayLabel">
      <configuration-item-view :ci="value" :view="view"/>
    </b-tab>


    </b-tabs>

    </b-card-body>
  </b-card>     
</template>

<script lang="ts">

import { ConfigurationItemReference } from "@/framework/reference"
import {getAvailableActions, SerializableAction,submitAction} from "@/framework/action"
import {getAvailableViews,SerializableView} from "@/framework/view";
import ConfigurationItemPropertyEditor from './ConfigurationItemPropertyEditor.vue'
import ConfigurationItemView from './ConfigurationItemView.vue';
import { smartSubmitAction } from '@/framework/smartDispatcher';

export default {
  components: { ConfigurationItemPropertyEditor, ConfigurationItemView },
    props: {
        value: {
            type: Object as () => ConfigurationItemReference
        } 
    },
    data()  {
        return {
            availablableActions: Array<SerializableAction>(),
            availablableViews: Array<SerializableView>(),
        }
    },
    methods: {
        onClickAction(action:SerializableAction) {
          smartSubmitAction( { action:action,cis:[this.value!] });
        }
    },
    mounted() {
      getAvailableActions([this.value!]).then( r=>{
        this.availablableActions = r;
      });
      getAvailableViews(this.value!).then( r=>{
        this.availablableViews = r;
      });
    }
}
</script>

