<template>
    <b-card header-tag="header" >
    
        <template #header>
            Search {{ classDescriptor == undefined ? configurationItemType : classDescriptor.displayLabel}}
        </template>

        
        <b-card-body>
            
        <b-form inline v-if=" searchRequest != undefined " @submit="onSubmit">
    
        <b-form-input class="mr-2" v-for=" field in searchRequest.parameters.stringFields " :key="field.name" :placeholder="field.displayLabel + ' filter'"  v-model="field.value"/>

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

    <configuration-item-table v-model="result" v-if="result != undefined" @change="doSearch()"/>

    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import {SearchRequest, getSearchTemplate, executeSearch} from "@/framework/search"
import ConfigurationItemTable from '@/components/ConfigurationItemTable.vue'
import { ConfigurationItemDescriptor, getClassMetadata } from '@/framework/metadata'
import { SerializableTable } from '@/framework/data'

export default {
    components: { ConfigurationItemTable },
    data() {
        return {
            searchRequest: undefined as SearchRequest|undefined,
            result: undefined as SerializableTable|undefined,
            classDescriptor: undefined as ConfigurationItemDescriptor | undefined,
            pending: false

        }
    },
    computed: {
        configurationItemType():string {
            return this.$route.params.configurationItemType;
        }
    },
    mounted() {
        this.reloadFromTemplate();
        getClassMetadata(this.configurationItemType,false).then( (r)=>
            this.classDescriptor = r
        );
    }, 
    methods: {
        reloadFromTemplate() {
            getSearchTemplate(this.configurationItemType).then( (r) => this.searchRequest = r);
        },
        onSubmit(event:Event) {
            event.preventDefault();
            this.doSearch();
        },
        doSearch() {
            this.pending=true
            executeSearch(this.searchRequest as SearchRequest).then( (r) => this.result = r).finally(
                ()=> this.pending =false
            );
        },
        doCreate() {
            this.$router.push( "/create/"+this.configurationItemType);
        }
    }
}
</script>
