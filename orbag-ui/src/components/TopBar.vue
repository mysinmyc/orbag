<template>
    <b-navbar toggleable="lg" type="dark" variant="info">
        <b-navbar-brand to="/">orbaG</b-navbar-brand>
        <b-navbar-nav>
            <b-nav-item-dropdown :text="element[0]" v-for="element in cisMenu.entries()" :key="element[0]" >
                <b-dropdown-item v-for=" ciMenu in element[1]" :to="ciMenu.link" :key="ciMenu.name"  >{{ciMenu.displayLabel}}</b-dropdown-item>
            </b-nav-item-dropdown>
        </b-navbar-nav>
    </b-navbar>
</template>

<script lang="ts">

import {ClassModel,getClassModel} from "@/framework/metadata"

export default {
    data() {
        return {
            cisMenu: new Map<string,Array<unknown>>()
        }
    },
    mounted() {
        getClassModel().then( (classModel:ClassModel) =>{
            let newCisMenu= new Map<string,Array<unknown>>();
            
            for (let descriptor of classModel.configurationItemDescriptors) {
                let currentCategory = newCisMenu.get(descriptor.category);
                if (currentCategory==null )  {
                    currentCategory = new Array<unknown>();
                    newCisMenu.set(descriptor.category,currentCategory);
                }
                currentCategory.push( {link: "/search/"+descriptor.name, displayLabel: descriptor.displayLabel });
            }

            this.cisMenu = newCisMenu;
        });
    }
}
</script>
