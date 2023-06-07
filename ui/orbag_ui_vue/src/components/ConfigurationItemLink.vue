<template>
    <b-link :to="link" v-if="value!=undefined">
        <b-icon icon="link"/>
        {{ value.displayLabel}}
    </b-link>
</template>

<script lang="ts">
import { ConfigurationItemReference } from '@/generated/client';


export default {
    props: {
        value: {
            type: Object as () => ConfigurationItemReference | undefined
        } 
    },
    computed: {
        link(): string {
            return "/edit/"+this.value!.configurationItemType+"/"+this.value!.identifier;
        }
    }
}
</script>
