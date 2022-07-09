<template>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand to="/">orbaG</b-navbar-brand>
         <b-navbar-nav>
      <b-nav-item-dropdown text="Search">
          <b-dropdown-item v-for=" ciMenu in cisMenu" :to="ciMenu.link" :key="ciMenu.name"  >{{ciMenu.displayLabel}}</b-dropdown-item>
      </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-navbar>
</template>

<script lang="ts">

import {ClassModel,getClassModel} from "@/framework/metadata"

export default {
    data() {
        return {
            cisMenu: Array<any>([])
        }
    },
    mounted() {
        getClassModel().then( (v:ClassModel) =>{
            let newCisMenu:Array<any> = []
            
            v.configurationItemDescriptors.forEach( (v,i,a)=>{
                newCisMenu.push( {link: "/list/"+v.name, displayLabel: v.name });
            })

            this.cisMenu = newCisMenu;
        });
    }
}
</script>
