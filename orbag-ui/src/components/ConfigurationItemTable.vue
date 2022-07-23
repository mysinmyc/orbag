<template>
    <div>
        <div v-if="value.rows!=undefined && value.rows.length > 0">
            
            Select cis from list to show the available actions
        
            <b-alert :variant="messageType"  :show="showMessage " dismissible>{{messsage}}</b-alert>
            <br/>
            <b-button class="m-2" v-for=" action in availablableActions" :key="action.name" @click="onClickAction(action)">{{action.displayLabel}}</b-button>
        </div>
        
        <b-table responsive sticky-header="true" v-if="value != undefined"  head-variant="light" :items="value.rows" select-mode="multi" selectable  @row-selected="onRowSelected" :fields="fields">
            <template #cell()="data">
                <configuration-item-link v-if="data.field.nestedColumn.type=='Reference'" v-model="data.value"/>
                <span v-else>{{data.value}}</span>
            </template>
        </b-table>
    </div>
</template>

<script lang="ts">

import {SerializableTable,SerializableRow} from "@/framework/data"
import ConfigurationItemLink from './ConfigurationItemLink.vue'
import { ConfigurationItemReference } from '@/framework/reference'
import {getAvailableActions, SerializableAction,submitAction} from "@/framework/action"

export default {
  components: {ConfigurationItemLink },
    data()  {
        return {
            selectedCis: Array<ConfigurationItemReference>(),
            availablableActions: Array<SerializableAction>(),
            messsage: "",
            showMessage:false,
            messageType: "success"
        }
    },
    props: {
        value: {
            type: Object as () => SerializableTable | undefined
        } 
    },
    computed: {
        fields() {
            const fields = [];
            if (this.value?.columns != null) {
                for (let column of this.value?.columns) {
                    fields.push( {
                        key: column.name,
                        label: column.displayLabel,
                        nestedColumn: column,
                        sortable: true
                    })
                }
            }
            fields.sort( (a,b) => {
                if (a.key.startsWith("__")) {
                    return -1
                }
                if (b.key.startsWith("__")) {
                    return -1
                }
                return a.label.localeCompare(b.label);
            });
            return fields;
        }
    },
    methods: {
        onRowSelected(items:Array<SerializableRow>) {
            this.availablableActions=[];
            this.selectedCis =  new Array<ConfigurationItemReference>();
            for ( let ci  of items ) {
                this.selectedCis.push (ci.__reference);
            }
            getAvailableActions(this.selectedCis).then( r=>{
                this.availablableActions = r;
            });
        },
        onClickAction(action:SerializableAction) {
            this.showMessage=false;
            submitAction(action, this.selectedCis).then(r=>{
                this.messsage = r.message;
                this.showMessage=true;
                this.messageType="success";
            }).catch ( reason=> {
                this.messsage = action.displayLabel + " failed: "+reason;
                this.messageType="warning";
                this.showMessage=true;
            });
        }
    }
}
</script>
