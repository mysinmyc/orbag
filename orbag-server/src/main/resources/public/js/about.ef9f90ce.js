"use strict";(self["webpackChunkorbag_ui"]=self["webpackChunkorbag_ui"]||[]).push([[443],{373:function(e,t,s){s.r(t),s.d(t,{default:function(){return f}});var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"home"},[t("h1",[e._v(e._s(e.configurationItemType))]),e._v(" Select cis from list, then click on one of the available actions "),t("b-alert",{attrs:{variant:"success",show:e.showMessage,dismissible:""}},[e._v(e._s(e.infoMesssage))]),t("br"),t("br"),t("b-button-group",e._l(e.availablableActions,(function(s){return t("b-button",{key:s.name,on:{click:function(t){return e.onClickAction(s)}}},[e._v(e._s(s.displayLabel))])})),1),t("br"),t("br"),t("b-table",{attrs:{items:e.cis,fields:["displayLabel"],selectable:"","select-mode":"multi"},on:{"row-selected":e.onRowSelected}})],1)},a=[],n=s(6265),o=s.n(n);function c(e){return new Promise(((t,s)=>{o().get("/api/list/"+e).then((e=>t(e.data))).catch((e=>s(e)))}))}function l(e){return new Promise(((t,s)=>{const i={targetCis:e};o().post("/api/action/getAvailable",i).then((e=>t(e.data.availableActions))).catch((e=>s(e)))}))}function r(e,t){return new Promise(((s,i)=>{const a={action:e,targetCis:t};o().post("/api/action/submit",a).then((e=>s(e.data))).catch((e=>i(e)))}))}var u={data(){return{cis:Array(),selectedCis:Array(),availablableActions:Array(),infoMesssage:"",showMessage:!1}},computed:{configurationItemType(){return this.$route.params.configurationItemType}},mounted(){c(this.configurationItemType).then((e=>{this.cis=e.cis}))},methods:{onRowSelected(e){this.availablableActions=[],this.selectedCis=e,l(this.selectedCis).then((e=>{this.availablableActions=e}))},onClickAction(e){this.showMessage=!1,r(e,this.selectedCis).then((e=>{this.infoMesssage=e.message,this.showMessage=!0}))}}},h=u,b=s(1001),d=(0,b.Z)(h,i,a,!1,null,null,null),f=d.exports}}]);
//# sourceMappingURL=about.ef9f90ce.js.map