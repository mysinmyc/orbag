<template>
  <div class="home">
   
   <h1>{{configurationItemType}}</h1>

    Select cis from list, then click on one of the available actions
    
    <b-alert variant="success"  :show=" showMessage " dismissible>{{infoMesssage}}</b-alert>
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
import {ConfigurationItemReference} from "@/framework/common"
import {listConfigurationItems} from "@/framework/list"
import {getAvailableActions, SerializableAction,submitAction} from "@/framework/action"
export default {
    data() {
        return {
            cis: Array<ConfigurationItemReference>(),
            selectedCis: Array<ConfigurationItemReference>(),
            availablableActions: Array<SerializableAction>(),
            infoMesssage: "",
            showMessage:false
        }
    },
    computed: {
        configurationItemType():string {
            return this.$route.params.configurationItemType;
        }
    },
    mounted() {
        listConfigurationItems(this.configurationItemType).then( r => {
            this.cis = r.cis;
        });
    },
    methods: {
        onRowSelected(items:Array<ConfigurationItemReference>) {
            this.availablableActions=[];
            this.selectedCis = items;
            getAvailableActions(this.selectedCis).then( r=>{
                this.availablableActions = r;
            });
        },
        onClickAction(action:SerializableAction) {
            this.showMessage=false;
            submitAction(action, this.selectedCis).then(r=>{
                this.infoMesssage = r.message;
                this.showMessage=true;
            });
        }
    }
}
</script>
