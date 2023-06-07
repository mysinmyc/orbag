<template>
        <b-form inline v-if=" updateRequest != undefined" @submit="onSubmit">

            <b-container fluid>
                <b-row class="my-1">
                  <input-property-editor :value="updateRequest.properties" @change=" changed=true" />
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
</template>

<script lang="ts">
import { ConfigurationItemReference, UpdateRequest } from '@/generated/client'

import InputPropertyEditor from './InputPropertyEditor.vue'
import { myHttpClient } from '@/framework/client'

export type InputFieldBase<T> = {
    changed?: boolean
}
export default {
  components: { InputPropertyEditor },
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

        myHttpClient().updateApi.buildUpdateTemplate(this.value! ).then( (r)=> {
            this.updateRequest = r.data!;
            this.changed =false;
        })
    },
    methods: {
        onSubmit(event:Event) {
            event.preventDefault;
            this.changed =false;
            myHttpClient().updateApi.update(this.updateRequest!).then( (r)=>
            myHttpClient().updateApi.buildUpdateTemplate(r.data!).then( (r1)=> {
                    this.updateRequest = r1.data!;
                    this.changed =false;
                })                
            )
        },
        update(property: InputFieldBase<any>) {
            property.changed =true;
            this.changed = true;
        }
    }
}
</script>

