<template>
    <b-card header-tag="header" >
    
        <template #header>
            Search {{ classDescriptor == undefined ? configurationItemType : classDescriptor.displayLabel}}
        </template>

        
        <b-card-body>
            
        <b-form inline v-if=" searchRequest != undefined " @submit="onSubmit">
    
        <b-form-input class="mr-2" v-for=" field in searchRequest?.parameters?.stringFields " :key="field.name" :placeholder="field.displayLabel + ' filter'"  v-model="field.value"/>

        <b-select class="mr-2" v-model="searchRequest.resultType">
            <b-select-option value="ROW_REFERENCE">Don't show any field</b-select-option>
            <b-select-option value="HIGHLIGHTED_FIELDS">Show highlighted fields</b-select-option>
            <b-select-option value="ALL_FIELDS">Show all fields</b-select-option>
        </b-select>
        <b-button class="mr-2" type="submit" variant="primary" v-show="!pending">Search</b-button>
        <b-button class="mr-2" v-show="classDescriptor != undefined && classDescriptor.allowCreation" @click="doCreate">Create new</b-button>
    </b-form>
    <b-button v-else type="submit" variant="primary" v-show="!pending">List</b-button>
    <b-progress :value="100" :max="100" animated v-show="pending"></b-progress>
    
    <br/>

    <configuration-item-table v-model="result" v-if="result != undefined" @change="doSearch()" @selectedci="(ci)=>onSelectedCi(ci)" @selectedotherci="(ci)=> onSelectedOtherCi(ci)"/>

    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import ConfigurationItemTable from '@/components/ConfigurationItemTable.vue'
import { myHttpClient } from '@/framework/client'
import { ConfigurationItemReference, SearchRequest, SerializableConfigurationItemDescriptor, SerializableTable } from '@/generated/client'

export default {
    components: { ConfigurationItemTable },
    props: {
        configurationItemType: {
            type: String
        } 
        
    },
    data() {
        return {
            searchRequest: undefined as SearchRequest|undefined,
            result: undefined as SerializableTable|undefined,
            classDescriptor: undefined as SerializableConfigurationItemDescriptor | undefined,
            pending: false

        }
    },
    mounted() {
        this.reloadFromTemplate();
        myHttpClient().metadataApi.getClassMetadata(this.configurationItemType!,false).then( (r)=>
            this.classDescriptor = r.data!
        );
    }, 
    methods: {
        reloadFromTemplate() {
            myHttpClient().searchApi.getSearchTemplate(this.configurationItemType!).then( (r) => this.searchRequest = r.data!);
        },
        onSubmit(event:Event) {
            event.preventDefault();
            this.doSearch();
        },
        doSearch() {
            this.pending=true
            myHttpClient().searchApi.execute(this.searchRequest as SearchRequest).then( (r) => this.result = r.data!).finally(
                ()=> this.pending =false
            );
        },
        doCreate() {
            this.$router.push( "/create/"+this.configurationItemType);
        },
        onSelectedCi(ci:ConfigurationItemReference) {
            this.$emit("selectedci",ci);
        },
        onSelectedOtherCi(ci:ConfigurationItemReference) {
            this.$emit("selectedotherci",ci);
        }
    }
}
</script>
