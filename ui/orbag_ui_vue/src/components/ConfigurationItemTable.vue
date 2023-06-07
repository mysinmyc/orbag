<template>
    <div>


        <b-alert :variant="messageType"  :show="showMessage " dismissible>{{message}}</b-alert>
        <br/>

        <div v-if="value?.rows !=undefined && value.rows.length > 0">
            
            Select cis from list to show the available actions
        
            <br/>
            <b-button class="m-2" v-for=" action in availablableActions" :key="action.identifier" @click="onClickAction(action)">{{action.displayLabel}}</b-button>
        </div>
        
        <b-table responsive sticky-header="true" v-if="value != undefined"  head-variant="light" :items="value.rows" select-mode="multi" selectable  @row-selected="onRowSelected" :fields="fields">
            <template #cell()="data">
                <span v-if="data.field.nestedColumn.type=='Reference' && data.item.fields[data.field.nestedColumn.name] != ''">
                    <b-link v-if="value!=undefined" @click="onReferenceClick(data.field.nestedColumn,data.item.fields[data.field.nestedColumn.name])">
                        <b-icon icon="link"/>
                            {{data.item.fields[data.field.nestedColumn.name].displayLabel}}
                        </b-link>
                </span>
                <span v-else>{{data.item.fields[data.field.nestedColumn.name]}}</span>
            </template>
        </b-table>
    </div>
</template>

<script lang="ts">

import { smartSubmitAction } from '@/framework/smartDispatcher'
import { ConfigurationItemReference, GetAvailableActionsRequest, SerializableAction, SerializableColumn, SerializableRow, SerializableTable, SubmitActionResponse } from '@/generated/client';
import { myHttpClient } from '@/framework/client';

export default {
  components: { },
    data()  {
        return {
            selectedCis: Array<ConfigurationItemReference>(),
            availablableActions: Array<SerializableAction>(),
            message: "",
            showMessage:false,
            messageType: "success"
        }
    },
    props: {
        value: {
            type: Object as () => SerializableTable | undefined
        }, 
        sourceci: {
            type: Object as () => ConfigurationItemReference | undefined
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
                if (a.key?.startsWith("__")) {
                    return -1
                }
                if (b.key?.startsWith("__")) {
                    return 1
                }
                return a.label!.localeCompare(b.label!);
            });
            return fields;
        }
    },
    methods: {
        onRowSelected(items:Array<SerializableRow>) {
            this.availablableActions=[];
            this.selectedCis =  new Array<ConfigurationItemReference>();
            for ( let ci  of items ) {
                this.selectedCis.push (ci.fields!.__reference);
            }

            myHttpClient().actionApi.getAvailable({sourceCi: this.sourceci,targetCis: this.selectedCis} as GetAvailableActionsRequest).then( r=>{
                this.availablableActions = r.data!.availableActions!;
            });
        },
        onReferenceClick(column:SerializableColumn,ci:ConfigurationItemReference) {
            if (column.name=='__reference') {
                this.$emit("selectedci",ci);
            } else {
                this.$emit("selectedotherci",ci);
            }
        },
        onClickAction(action:SerializableAction) {
            this.showMessage=false;
            smartSubmitAction( { action:action,cis:this.selectedCis, sourceCi: this.sourceci, callback: (_:SubmitActionResponse)=>{
                this.$emit("change");
            } });
        }
    }
}
</script>
