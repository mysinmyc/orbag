"use strict";(self["webpackChunkorbag_ui"]=self["webpackChunkorbag_ui"]||[]).push([[443],{3902:function(e,t,s){s.r(t),s.d(t,{default:function(){return y}});var a=function(){var e=this,t=e._self._c;return void 0!=e.ci?t("configuration-item-editor",{model:{value:e.ci,callback:function(t){e.ci=t},expression:"ci"}}):e._e()},n=[],i=s(6265),r=s.n(i);function l(e,t){return new Promise(((s,a)=>{r().get("/api/reference/"+t+"/"+e).then((e=>s(e.data))).catch((e=>a(e)))}))}var o=function(){var e=this,t=e._self._c;return t("b-card",{attrs:{"header-tag":"header"},scopedSlots:e._u([{key:"header",fn:function(){return[e._v(" "+e._s(e.value.configurationItemTypeDisplayLabel)+" ")]},proxy:!0}])},[t("b-card-body",[t("b-card-title",[e._v(e._s(e.value.displayLabel))]),t("b-dropdown",{staticClass:"mx-1",attrs:{right:"",text:"actions"}},e._l(e.availablableActions,(function(s){return t("b-dropdown-item",{key:s.name,staticClass:"m-2",on:{click:function(t){return e.onClickAction(s)}}},[e._v(e._s(s.displayLabel))])})),1),t("br"),t("br"),t("b-alert",{attrs:{variant:e.messageType,show:e.showMessage,dismissible:""}},[e._v(e._s(e.messsage))])],1)],1)},c=[],u=s(6844),h={props:{value:{type:Object}},data(){return{availablableActions:Array(),messsage:"",showMessage:!1,messageType:"success"}},methods:{onClickAction(e){this.showMessage=!1,(0,u.F)(e,[this.value]).then((e=>{this.messsage=e.message,this.showMessage=!0,this.messageType="success"})).catch((t=>{this.messsage=e.displayLabel+" failed: "+t,this.messageType="warning",this.showMessage=!0}))}},mounted(){(0,u.u)([this.value]).then((e=>{this.availablableActions=e}))}},d=h,m=s(1001),p=(0,m.Z)(d,o,c,!1,null,null,null),f=p.exports,v={components:{ConfigurationItemEditor:f},data(){return{ci:void 0}},computed:{configurationItemType(){return this.$route.params.configurationItemType},configurationItemId(){return this.$route.params.configurationItemId}},mounted(){l(this.configurationItemId,this.configurationItemType).then((e=>this.ci=e))}},b=v,g=(0,m.Z)(b,a,n,!1,null,null,null),y=g.exports},8124:function(e,t,s){s.r(t),s.d(t,{default:function(){return d}});var a=function(){var e=this,t=e._self._c;return t("div",{staticClass:"home"},[t("h1",[e._v(e._s(e.configurationItemType))]),e._v(" Select cis from list, then click on one of the available actions "),t("b-alert",{attrs:{variant:e.messageType,show:e.showMessage,dismissible:""}},[e._v(e._s(e.messsage))]),t("br"),t("br"),t("b-button-group",e._l(e.availablableActions,(function(s){return t("b-button",{key:s.name,on:{click:function(t){return e.onClickAction(s)}}},[e._v(e._s(s.displayLabel))])})),1),t("br"),t("br"),t("b-table",{attrs:{items:e.cis,fields:["displayLabel"],selectable:"","select-mode":"multi"},on:{"row-selected":e.onRowSelected}})],1)},n=[],i=s(6265),r=s.n(i);function l(e){return new Promise(((t,s)=>{r().get("/api/list/"+e).then((e=>t(e.data))).catch((e=>s(e)))}))}var o={data(){return{cis:Array(),selectedCis:Array(),messsage:"",showMessage:!1,messageType:"success"}},computed:{configurationItemType(){return this.$route.params.configurationItemType}},mounted(){this.reloadList()},methods:{reloadList(){l(this.configurationItemType).then((e=>{this.cis=e.cis}))}}},c=o,u=s(1001),h=(0,u.Z)(c,a,n,!1,null,null,null),d=h.exports},4324:function(e,t,s){s.r(t),s.d(t,{default:function(){return A}});var a=function(){var e=this,t=e._self._c;return t("b-card",{attrs:{"header-tag":"header"},scopedSlots:e._u([{key:"header",fn:function(){return[e._v(" Search "+e._s(e.configurationItemType)+" ")]},proxy:!0}])},[t("b-card-body",[null!=e.searchRequest.parameters?t("b-form",{attrs:{inline:""}},[e._l(e.searchRequest.parameters.fields,(function(s){return t("b-form-input",{key:s.name,staticClass:"m-2",attrs:{placeholder:s.displayLabel+" filter"},model:{value:s.value,callback:function(t){e.$set(s,"value",t)},expression:"field.value"}})})),t("b-select",{model:{value:e.searchRequest.resultType,callback:function(t){e.$set(e.searchRequest,"resultType",t)},expression:"searchRequest.resultType"}},[t("b-select-option",{attrs:{value:"ROW_REFERENCE"}},[e._v("Don't show any field")]),t("b-select-option",{attrs:{value:"HIGHLIGHTED_FIELDS"}},[e._v("Show highlighted fields")]),t("b-select-option",{attrs:{value:"ALL_FIELDS"}},[e._v("Show all fields")])],1),t("b-button",{directives:[{name:"show",rawName:"v-show",value:!e.pending,expression:"!pending"}],staticClass:"m-2",attrs:{variant:"primary"},on:{click:e.doSearch}},[e._v("Search")])],2):t("b-button",{directives:[{name:"show",rawName:"v-show",value:!e.pending,expression:"!pending"}],attrs:{variant:"primary"},on:{click:e.doSearch}},[e._v("List")]),t("b-progress",{directives:[{name:"show",rawName:"v-show",value:e.pending,expression:"pending"}],attrs:{value:100,max:100,animated:""}}),t("br"),t("configuration-item-table",{model:{value:e.result,callback:function(t){e.result=t},expression:"result"}})],1)],1)},n=[],i=s(6265),r=s.n(i);function l(e){return new Promise(((t,s)=>{r().get("/api/search/template/"+e).then((e=>t(e.data))).catch((e=>s(e)))}))}function o(e){return new Promise(((t,s)=>{r().post("/api/search/execute",e).then((e=>t(e.data))).catch((e=>s(e)))}))}var c=function(){var e=this,t=e._self._c;return t("div",[void 0!=e.value.rows&&e.value.rows.length>0?t("div",[e._v(" Select cis from list to show the available actions "),t("b-alert",{attrs:{variant:e.messageType,show:e.showMessage,dismissible:""}},[e._v(e._s(e.messsage))]),t("br"),e._l(e.availablableActions,(function(s){return t("b-button",{key:s.name,staticClass:"m-2",on:{click:function(t){return e.onClickAction(s)}}},[e._v(e._s(s.displayLabel))])}))],2):e._e(),void 0!=e.value?t("b-table",{attrs:{responsive:"","sticky-header":"true","head-variant":"light",items:e.value.rows,"select-mode":"multi",selectable:"",fields:e.fields},on:{"row-selected":e.onRowSelected},scopedSlots:e._u([{key:"cell()",fn:function(s){return["Reference"==s.field.nestedColumn.type?t("configuration-item-link",{model:{value:s.value,callback:function(t){e.$set(s,"value",t)},expression:"data.value"}}):t("span",[e._v(e._s(s.value))])]}}],null,!1,991663366)}):e._e()],1)},u=[],h=function(){var e=this,t=e._self._c;return t("b-link",{attrs:{to:e.link}},[t("b-icon",{attrs:{icon:"link"}}),e._v(" "+e._s(e.value.displayLabel)+" ")],1)},d=[],m={props:{value:{type:Object}},computed:{link(){return"/edit/"+this.value.configurationItemType+"/"+this.value.identifier}}},p=m,f=s(1001),v=(0,f.Z)(p,h,d,!1,null,null,null),b=v.exports,g=s(6844),y={components:{ConfigurationItemLink:b},data(){return{selectedCis:Array(),availablableActions:Array(),messsage:"",showMessage:!1,messageType:"success"}},props:{value:{type:Object}},computed:{fields(){const e=[];if(null!=this.value?.columns)for(let t of this.value?.columns)e.push({key:t.name,label:t.displayLabel,nestedColumn:t,sortable:!0});return e.sort(((e,t)=>e.key.startsWith("__")||t.key.startsWith("__")?-1:e.label.localeCompare(t.label))),e}},methods:{onRowSelected(e){this.availablableActions=[],this.selectedCis=new Array;for(let t of e)this.selectedCis.push(t.__reference);(0,g.u)(this.selectedCis).then((e=>{this.availablableActions=e}))},onClickAction(e){this.showMessage=!1,(0,g.F)(e,this.selectedCis).then((e=>{this.messsage=e.message,this.showMessage=!0,this.messageType="success"})).catch((t=>{this.messsage=e.displayLabel+" failed: "+t,this.messageType="warning",this.showMessage=!0}))}}},_=y,w=(0,f.Z)(_,c,u,!1,null,null,null),k=w.exports,T={components:{ConfigurationItemTable:k},data(){return{searchRequest:{},result:{},pending:!1}},computed:{configurationItemType(){return this.$route.params.configurationItemType}},mounted(){this.reloadFromTemplate()},methods:{reloadFromTemplate(){l(this.configurationItemType).then((e=>this.searchRequest=e))},doSearch(){this.pending=!0,o(this.searchRequest).then((e=>this.result=e)).finally((()=>this.pending=!1))}}},C=T,I=(0,f.Z)(C,a,n,!1,null,null,null),A=I.exports},6844:function(e,t,s){s.d(t,{F:function(){return r},u:function(){return i}});var a=s(6265),n=s.n(a);function i(e){return new Promise(((t,s)=>{const a={targetCis:e};n().post("/api/action/getAvailable",a).then((e=>t(e.data.availableActions))).catch((e=>s(e)))}))}function r(e,t){return new Promise(((s,a)=>{const i={action:e,targetCis:t};n().post("/api/action/submit",i).then((e=>s(e.data))).catch((e=>a(e)))}))}}}]);
//# sourceMappingURL=about.c85bce76.js.map