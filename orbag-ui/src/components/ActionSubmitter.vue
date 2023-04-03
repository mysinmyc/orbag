<template>
  <span>
  <my-modal-container v-if="showActionInputProperties">
      <template #header>
        {{request?.action.displayLabel}}
      </template>
      <template #body>
        <input-property-editor :value="request?.parameters"/>
      </template>
      <template #footer>
        <b-button @click="doSubmit()">Execute</b-button><b-button @click="doCancelAction()">Cancel</b-button>
      </template>
  </my-modal-container>
    <b-alert :variant="messageType"  :show="showMessage" dismissible>{{message}}</b-alert>
  </span>        
</template>
  
<script lang="ts">
import { buildActionTemplateWithSource, submitAction, SubmitActionRequest } from '@/framework/action'
import InputPropertyEditor from './InputPropertyEditor.vue'
import { isFieldGroupEmpty } from '@/framework/input'
import { SmartActionEntry, subscribeSmartSubmitAction } from '@/framework/smartDispatcher'
import MyModalContainer from './MyModalContainer.vue'
  
  export default {
    components: { InputPropertyEditor, MyModalContainer},
      data()  {
          return {
              request: undefined as SubmitActionRequest | undefined,
              requestEntry: undefined as SmartActionEntry | undefined,
              message: "",
              showActionInputProperties: false,
              showMessage:false,
              messageType: "success"
          }
      },
      methods: {
        doCancelAction() {
          this.showActionInputProperties=false;
        },
        doSubmit() {
          submitAction(this.request!).then(r=>{
                this.message = r.message;
                this.showMessage=true;
                this.messageType="success";
                if (this.requestEntry?.callback != undefined) {
                  this.requestEntry.callback(r);
                }
            }).catch ( reason=> {
                this.message = this.request!.action.displayLabel + " failed: "+reason;
                this.messageType="warning";
                this.showMessage=true;
            }).finally(()=>{
              this.showActionInputProperties =false;
            });
        }
      },
      mounted() {
        subscribeSmartSubmitAction( (entry:SmartActionEntry)=> {
          this.showActionInputProperties=false;
          this.showMessage=false;
          this.requestEntry = entry;
          buildActionTemplateWithSource(entry.action,entry.sourceCi,entry.cis).then( (request)=>{
            this.request = request
            if (isFieldGroupEmpty(this.request!.parameters)) {
              this.doSubmit();
            } else {
              this.showActionInputProperties=true;
            }
          });
        });
      }
  }
  </script>
  
  