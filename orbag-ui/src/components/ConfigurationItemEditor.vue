<template>
  <b-card header-tag="header">
    <template #header>
        {{value.configurationItemTypeDisplayLabel}}
    </template>
    <b-card-body>
        <b-card-title>{{value.displayLabel}}</b-card-title>
        <b-dropdown class="mx-1" right text="actions">
            <b-dropdown-item class="m-2" v-for="action in availablableActions" :key="action.name" @click="onClickAction(action)">{{action.displayLabel}}</b-dropdown-item>
        </b-dropdown>
        <br/>
        <br/>
        <b-alert :variant="messageType"  :show="showMessage " dismissible>{{messsage}}</b-alert>
    </b-card-body>
  </b-card>     
</template>

<script lang="ts">

import { ConfigurationItemReference } from "@/framework/reference"
import {getAvailableActions, SerializableAction,submitAction} from "@/framework/action"

export default {
    props: {
        value: {
            type: Object as () => ConfigurationItemReference
        } 
    },
    data()  {
        return {
            availablableActions: Array<SerializableAction>(),
            messsage: "",
            showMessage:false,
            messageType: "success"
        }
    },
    methods: {
          onClickAction(action:SerializableAction) {
            this.showMessage=false;
            submitAction(action, [this.value]).then(r=>{
                this.messsage = r.message;
                this.showMessage=true;
                this.messageType="success";
            }).catch ( reason=> {
                this.messsage = action.displayLabel + " failed: "+reason;
                this.messageType="warning";
                this.showMessage=true;
            });
        }
    },
    mounted() {
      getAvailableActions([this.value]).then( r=>{
                this.availablableActions = r;
      });
    }
}
</script>

