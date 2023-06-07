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
import { CreateRequest, SerializableConfigurationItemDescriptor } from '@/generated/client';

import InputPropertyEditor from '../components/InputPropertyEditor.vue';
import { myHttpClient } from '@/framework/client';

export default {
  components: {InputPropertyEditor  },
    data() {
        return {
            createRequest: undefined as CreateRequest | undefined,
            classDescriptor: undefined as SerializableConfigurationItemDescriptor | undefined,
            changed: false
        }
    },  
    computed: {
      configurationItemType():string {
          return this.$route.params.configurationItemType;
      },
     
    },
    mounted() {
      myHttpClient().createApi.getCreateTemplate(this.configurationItemType).then( (r)=>
        this.createRequest = r.data!
      );
      myHttpClient().metadataApi.getClassMetadata(this.configurationItemType,false).then( (r)=>
            this.classDescriptor = r.data!
      );
    },
    methods: {
      onSubmit(event:Event) {
        event.preventDefault;
        myHttpClient().createApi.create(this.createRequest!).then((r)=>{
            this.$router.push("/edit/"+r.data!.configurationItemType+"/"+r.data!.identifier!);
        });
      }
    }
}
</script>

