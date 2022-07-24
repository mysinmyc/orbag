<template>
  <b-card header-tag="header">
    <template #header>
        Properties
    </template>
    <b-card-body>
        <b-alert :variant="messageType"  :show="showMessage " dismissible>{{message}}</b-alert>
        <b-form inline v-if=" updateRequest != undefined" @submit="onSubmit">
            <b-container fluid>
                <b-row class="my-1" v-for="property in updateRequest.properties.fields " :key="property.name">
                    <b-col sm="3">
                    <label for="input-none">{{property.displayLabel}}</label>
                    </b-col>
                    <b-col sm="9">
                    <b-form-input v-model="property.value" :readonly="property.readOnly" @change="update(property)"></b-form-input>
                    </b-col>
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                    </b-col>
                    <b-col sm="9">
                    <b-button type="submit" variant="primary" v-show="changed">Update</b-button>
                    </b-col>
                </b-row>
            </b-container>
        </b-form>
    </b-card-body>
  </b-card>     
</template>

<script lang="ts">

import { ConfigurationItemReference } from "@/framework/reference"
import { UpdateRequest, getUpdateRequestTemplate, updateConfigurationItem } from "@/framework/update"
import { InputFieldBase } from '@/framework/input'

export default {
    props: {
        value: {
            type: Object as () => ConfigurationItemReference
        } 
    },
    data()  {
        return {
            updateRequest: undefined as UpdateRequest | undefined,
            message: "",
            showMessage:false,
            messageType: "success",
            changed: false
        }
    },
    mounted() {
        getUpdateRequestTemplate(this.value ).then( (r)=> {
            this.updateRequest = r;
            this.changed =false;
        })
    },
    methods: {
        onSubmit(event:Event) {
            event.preventDefault;
            this.changed =false;
            updateConfigurationItem(this.updateRequest!).then( (r)=>
                getUpdateRequestTemplate(r).then( (r1)=> {
                    this.updateRequest = r1;
                    this.changed =false;
                })                
            )
        },
        update(property: InputFieldBase) {
            property.changed =true;
            this.changed = true;
        }
    }
}
</script>

