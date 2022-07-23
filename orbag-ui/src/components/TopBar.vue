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
            cisMenu: new Map<string,Array<any>>()
        }
    },
    mounted() {
        getClassModel().then( (v:ClassModel) =>{
            let newCisMenu= new Map<string,Array<any>>();
            
            v.configurationItemDescriptors.forEach( (v,i,a)=>{
                let currentCategory = newCisMenu.get(v.category);
                if (currentCategory==null )  {
                    currentCategory = new Array<any>();
                    newCisMenu.set(v.category,currentCategory);
                }
                currentCategory.push( {link: "/search/"+v.name, displayLabel: v.name });
            })

            this.cisMenu = newCisMenu;
        });
    }
}
</script>
