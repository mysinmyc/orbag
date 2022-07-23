<template>
    <b-link :to="link">
        <b-icon icon="link"/>
        {{ value.displayLabel}}
    </b-link>
</template>

<script lang="ts">

import {ConfigurationItemReference} from "@/framework/reference"

export default {
    props: {
        value: {
            type: Object as () => ConfigurationItemReference
        } 
    },
    computed: {
        link(): string {
            return "/edit/"+this.value.configurationItemType+"/"+this.value.identifier;
        }
    }
}
</script>
