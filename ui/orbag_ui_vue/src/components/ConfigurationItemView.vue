<template>
    <configuration-item-table :value="table" v-if="table.rows != undefined" :sourceci="ci"  @selectedci="(ci)=>onSelectedCi(ci)" @selectedotherci="(ci)=>onSelectedCi(ci)" />
</template>

<script lang="ts">

import ConfigurationItemTable from './ConfigurationItemTable.vue'
import { smartEditConfigurationItem } from '@/framework/smartDispatcher'
import { BindViewRequest, ConfigurationItemReference, SerializableTable, SerializableView } from '@/generated/client'
import { myHttpClient } from '@/framework/client'

export default {
  components: { ConfigurationItemTable },
    data()  {
        return {
            table: {} as SerializableTable
        }
    },
    props: {
        ci: {
            type: Object as () => ConfigurationItemReference | undefined
        },
        view: {
            type: Object as () => SerializableView | undefined
        }
    },
    mounted() {
        myHttpClient().viewApi.bind({view: this.view!, targetCi: this.ci!} as BindViewRequest ).then( (r)=> {
            this.table = r.data!.resultTable!;
        })
    },
    methods: {
        onSelectedCi(ci:ConfigurationItemReference) {
            smartEditConfigurationItem(ci);
        }
    }
}
</script>
