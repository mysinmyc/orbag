<template>
    <configuration-item-table :value="table" v-if="table.rows != undefined" :sourceCi="ci"  />
</template>

<script lang="ts">

import {SerializableTable,SerializableRow} from "@/framework/data"
import { ConfigurationItemReference } from '@/framework/reference'
import { SerializableView, bindView } from "@/framework/view"
import ConfigurationItemTable from './ConfigurationItemTable.vue'

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
        bindView(this.view!, this.ci! ).then( (r)=> {
            this.table = r;
        })
    },
}
</script>
