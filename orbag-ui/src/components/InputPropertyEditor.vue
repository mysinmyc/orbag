<template>
    <b-container fluid>
        <b-row class="my-1" v-for="property in value?.stringFields " :key="property.name">
            <b-col sm="3">
            <label for="input-none">{{property.displayLabel}}</label>
            </b-col>
            <b-col sm="9">
            <b-form-input v-model="property.value" :readonly="property.readOnly" @change="update(property)"></b-form-input>
            </b-col>
        </b-row>
        <b-row class="my-1" v-for="property in value?.booleanFields " :key="property.name">
            <b-col sm="3">
            <label for="input-none">{{property.displayLabel}}</label>
            </b-col>
            <b-col sm="9">
            <b-form-checkbox v-model="property.value" :readonly="property.readOnly" @change="update(property)"></b-form-checkbox>
            </b-col>
        </b-row>
        <b-row class="my-1" v-for="property in value?.enumFields " :key="property.name">
            <b-col sm="3">
            <label for="input-none">{{property.displayLabel}}</label>
            </b-col>
            <b-col sm="9">
            <b-form-select  v-model="property.value" :readonly="property.readOnly" @change="update(property)" :options="property.allowedValues"/>
            </b-col>
        </b-row>        
        <b-row class="my-1" v-for="property in value?.numericFields " :key="property.name">
            <b-col sm="3">
            <label for="input-none">{{property.displayLabel}}</label>
            </b-col>
            <b-col sm="9">
            <b-form-input v-model="property.value" type="number" :readonly="property.readOnly" @change="update(property)"></b-form-input>
            </b-col>
        </b-row>
        <b-row class="my-1" v-for="property in value?.configurationItemReferenceFields " :key="property.name">
            <b-col sm="3">
            <label for="input-none">{{property.displayLabel}}</label>
            </b-col>
            <b-col sm="9">
            <configuration-item-link  v-model="property.value" :readonly="property.readOnly" @change="update(property)"></configuration-item-link>
            <b-button v-if="! property.readOnly">change</b-button>
            </b-col>
        </b-row>                                                  
    </b-container>
  </template>
  
  <script lang="ts">
  
  import { InputFieldBase,SerializableFieldGroup } from '@/framework/input'
  import ConfigurationItemLink from './ConfigurationItemLink.vue'
  
  export default {
    components: { ConfigurationItemLink },
      props: {
          value: {
              type: Object as () => SerializableFieldGroup
          } 
      },
      data()  {
          return {
              message: "",
              showMessage:false,
              messageType: "success",
              changed: false
          }
      },
      methods: {
          onSubmit(event:Event) {
              event.preventDefault;
              this.changed =false;
          },
          update(property: InputFieldBase<any>) {
            property.changed =true;
            this.changed = true;
            this.$emit('change',this.value);
        }
      }
  }
  </script>
  
  