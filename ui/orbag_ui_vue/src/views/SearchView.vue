<template>
    <search-ci :configuration-item-type="configurationItemType" @selectedci="(ci)=>onSelectedCi(ci)" @selectedotherci="(ci)=>onSelectedCi(ci)"/>
</template>

<script lang="ts">
import SearchCi from '@/components/SearchCi.vue'
import { ConfigurationItemReference } from '@/framework/reference';
import { smartEditConfigurationItem } from '@/framework/smartDispatcher';

export default {
    components: {  SearchCi },
    data() {
        return {
        }
    },
    computed: {
        configurationItemType():string {
            return this.$route.params.configurationItemType;
        }
    },
    methods: {
        onSelectedCi(ci:ConfigurationItemReference) {
            smartEditConfigurationItem(ci);
        }
    }
}
</script>
