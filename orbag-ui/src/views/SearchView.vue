<template>
    <b-card header-tag="header" >
    
        <template #header>
            Search {{configurationItemType}}
        </template>

        
        <b-card-body>
            
        <b-form inline v-if=" searchRequest.parameters != null " @submit="onSubmit">
    
        <b-form-input class="m-2" v-for=" field in searchRequest.parameters.fields " :key="field.name" :placeholder="field.displayLabel + ' filter'"  v-model="field.value"/>

        <b-select  v-model="searchRequest.resultType">
            <b-select-option value="ROW_REFERENCE">Don't show any field</b-select-option>
            <b-select-option value="HIGHLIGHTED_FIELDS">Show highlighted fields</b-select-option>
            <b-select-option value="ALL_FIELDS">Show all fields</b-select-option>
        </b-select>
        <b-button class="m-2" type="submit" variant="primary" v-show="!pending">Search</b-button>
    </b-form>
    <b-button v-else type="submit" variant="primary" v-show="!pending">List</b-button>
    <b-progress :value="100" :max="100" animated v-show="pending"></b-progress>

    <br/>

    <configuration-item-table v-model="result"/>

    </b-card-body>
  </b-card>
</template>

<script lang="ts">
import {SearchRequest, getSearchTemplate, executeSearch} from "@/framework/search"
import ConfigurationItemTable from '@/components/ConfigurationItemTable.vue'

export default {
    components: { ConfigurationItemTable },
    data() {
        return {
            searchRequest: {},
            result: {},
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
        }
    }
}
</script>
