<template>
  <div class="home">
   
   <h1>{{configurationItemType}}</h1>

    Select cis from list, then click on one of the available actions
    
    <b-alert :variant="messageType"  :show="showMessage " dismissible>{{messsage}}</b-alert>
    <br/>
    <br/>

    <b-button-group>
        <b-button v-for=" action in availablableActions" :key="action.name" @click="onClickAction(action)">{{action.displayLabel}}</b-button>
    </b-button-group>

    <br/>
    <br/>

    <b-table :items="cis" :fields="['displayLabel']" selectable select-mode="multi"  @row-selected="onRowSelected">
    </b-table>    
  </div>
</template>

<script lang="ts">
import {ConfigurationItemReference} from "@/framework/reference"
import {listConfigurationItems} from "@/framework/list"

export default {
    data() {
        return {
            cis: Array<ConfigurationItemReference>(),
            selectedCis: Array<ConfigurationItemReference>(),
            messsage: "",
            showMessage:false,
            messageType: "success"
        }
    },
    computed: {
        configurationItemType():string {
            return this.$route.params.configurationItemType;
        }
    },
    mounted() {
        this.reloadList();
    },
    methods: {
        reloadList() {
            listConfigurationItems(this.configurationItemType).then( r => {
                this.cis = r.cis;
            });
        }
    }
}
</script>
