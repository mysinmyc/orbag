<template>
  <b-card header-tag="header" >
    <template #header>
      Create {{ classDescriptor == undefined ? configurationItemType : classDescriptor.displayLabel}}
    </template>

    <b-card-body>        
      <b-form inline v-if=" createRequest != undefined " @submit="onSubmit">
        <b-container fluid>
                <b-row class="my-1">
                  <input-property-editor :value="createRequest.parameters" @change=" changed=true" />
                </b-row>
                <b-row class="my-1">
                    <b-col sm="3">
                    </b-col>
                    <b-col sm="9">
                    <b-button type="submit" variant="primary" v-show="changed">Create</b-button>
                    </b-col>
                </b-row>
        </b-container>
      </b-form>
    </b-card-body>
  </b-card>
</template>
<script lang="ts">

import { getCreateRequestTemplate, CreateRequest, createConfigurationItem } from "@/framework/create";
import { ConfigurationItemDescriptor, getClassMetadata } from '@/framework/metadata';
import InputPropertyEditor from '../components/InputPropertyEditor.vue';

export default {
  components: {InputPropertyEditor  },
    data() {
        return {
            createRequest: undefined as CreateRequest | undefined,
            classDescriptor: undefined as ConfigurationItemDescriptor | undefined,
            changed: false
        }
    },  
    computed: {
      configurationItemType():string {
          return this.$route.params.configurationItemType;
      },
     
    },
    mounted() {
      getCreateRequestTemplate(this.configurationItemType).then( (r)=>
        this.createRequest = r
      );
      getClassMetadata(this.configurationItemType,false).then( (r)=>
            this.classDescriptor = r
      );
    },
    methods: {
      onSubmit(event:Event) {
        event.preventDefault;
        createConfigurationItem(this.createRequest!).then((r)=>{
            this.$router.push("/edit/"+r.configurationItemType+"/"+r.identifier);
        });
      }
    }
}
</script>

