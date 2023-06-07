<template>
    <search-ci :configuration-item-type="configurationItemType" @selectedci="(ci)=>onSelectedCi(ci)" @selectedotherci="(ci)=>onSelectedCi(ci)"/>
</template>

<script lang="ts">
import SearchCi from '@/components/SearchCi.vue'
import { smartEditConfigurationItem } from '@/framework/smartDispatcher';
import { ConfigurationItemReference } from '@/generated/client';

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
