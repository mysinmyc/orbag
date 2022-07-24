(function(){"use strict";var n={9099:function(){},1934:function(n,t,e){var r=e(6369),o=function(){var n=this,t=n._self._c;return t("div",{attrs:{id:"app"}},[t("top-bar"),t("b-card",{attrs:{overlay:""}},[t("b-card-body",{attrs:{overlay:""}},[t("router-view",{key:n.$route.path})],1)],1)],1)},a=[],i=function(){var n=this,t=n._self._c;return t("b-navbar",{attrs:{toggleable:"lg",type:"dark",variant:"info"}},[t("b-navbar-brand",{attrs:{to:"/"}},[n._v("orbaG")]),t("b-navbar-nav",n._l(n.cisMenu.entries(),(function(e){return t("b-nav-item-dropdown",{key:e[0],attrs:{text:e[0]}},n._l(e[1],(function(e){return t("b-dropdown-item",{key:e.name,attrs:{to:e.link}},[n._v(n._s(e.displayLabel))])})),1)})),1)],1)},u=[],c=e(6265),f=e.n(c);function l(){return new Promise(((n,t)=>{f().get("/api/metadata").then((t=>n(t.data))).catch((n=>t(n)))}))}var s={data(){return{cisMenu:new Map}},mounted(){l().then((n=>{let t=new Map;n.configurationItemDescriptors.forEach(((n,e,r)=>{let o=t.get(n.category);null==o&&(o=new Array,t.set(n.category,o)),o.push({link:"/search/"+n.name,displayLabel:n.name})})),this.cisMenu=t}))}},d=s,p=e(1001),v=(0,p.Z)(d,i,u,!1,null,null,null),b=v.exports,h={components:{TopBar:b}},m=h,g=(0,p.Z)(m,o,a,!1,null,null,null),y=g.exports,w=e(2631),_=function(){var n=this,t=n._self._c;n._self._setupProxy;return t("div",{staticClass:"home"},[n._v(" To start select an entity from the top menu ")])},k=[],O=e(9099),T=e.n(O),j=T(),x=(0,p.Z)(j,_,k,!1,null,null,null),M=x.exports;r["default"].use(w.Z);const P=[{path:"/",name:"home",component:M},{path:"/list/:configurationItemType",name:"list",component:()=>e.e(443).then(e.bind(e,8124))},{path:"/search/:configurationItemType",name:"search",component:()=>e.e(443).then(e.bind(e,4324))},{path:"/edit/:configurationItemType/:configurationItemId",name:"edit",component:()=>e.e(443).then(e.bind(e,3902))}],C=new w.Z({mode:"hash",base:"/",routes:P});var E=C,A=e(5996),I=e(9425);e(7024);r["default"].use(A.XG7),r["default"].use(I.A7),r["default"].config.productionTip=!1,new r["default"]({router:E,render:n=>n(y)}).$mount("#app")}},t={};function e(r){var o=t[r];if(void 0!==o)return o.exports;var a=t[r]={exports:{}};return n[r](a,a.exports,e),a.exports}e.m=n,function(){var n=[];e.O=function(t,r,o,a){if(!r){var i=1/0;for(l=0;l<n.length;l++){r=n[l][0],o=n[l][1],a=n[l][2];for(var u=!0,c=0;c<r.length;c++)(!1&a||i>=a)&&Object.keys(e.O).every((function(n){return e.O[n](r[c])}))?r.splice(c--,1):(u=!1,a<i&&(i=a));if(u){n.splice(l--,1);var f=o();void 0!==f&&(t=f)}}return t}a=a||0;for(var l=n.length;l>0&&n[l-1][2]>a;l--)n[l]=n[l-1];n[l]=[r,o,a]}}(),function(){e.n=function(n){var t=n&&n.__esModule?function(){return n["default"]}:function(){return n};return e.d(t,{a:t}),t}}(),function(){e.d=function(n,t){for(var r in t)e.o(t,r)&&!e.o(n,r)&&Object.defineProperty(n,r,{enumerable:!0,get:t[r]})}}(),function(){e.f={},e.e=function(n){return Promise.all(Object.keys(e.f).reduce((function(t,r){return e.f[r](n,t),t}),[]))}}(),function(){e.u=function(n){return"js/about.c85bce76.js"}}(),function(){e.miniCssF=function(n){}}(),function(){e.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(n){if("object"===typeof window)return window}}()}(),function(){e.o=function(n,t){return Object.prototype.hasOwnProperty.call(n,t)}}(),function(){var n={},t="orbag-ui:";e.l=function(r,o,a,i){if(n[r])n[r].push(o);else{var u,c;if(void 0!==a)for(var f=document.getElementsByTagName("script"),l=0;l<f.length;l++){var s=f[l];if(s.getAttribute("src")==r||s.getAttribute("data-webpack")==t+a){u=s;break}}u||(c=!0,u=document.createElement("script"),u.charset="utf-8",u.timeout=120,e.nc&&u.setAttribute("nonce",e.nc),u.setAttribute("data-webpack",t+a),u.src=r),n[r]=[o];var d=function(t,e){u.onerror=u.onload=null,clearTimeout(p);var o=n[r];if(delete n[r],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(n){return n(e)})),t)return t(e)},p=setTimeout(d.bind(null,void 0,{type:"timeout",target:u}),12e4);u.onerror=d.bind(null,u.onerror),u.onload=d.bind(null,u.onload),c&&document.head.appendChild(u)}}}(),function(){e.r=function(n){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(n,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(n,"__esModule",{value:!0})}}(),function(){e.p="/"}(),function(){var n={143:0};e.f.j=function(t,r){var o=e.o(n,t)?n[t]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(e,r){o=n[t]=[e,r]}));r.push(o[2]=a);var i=e.p+e.u(t),u=new Error,c=function(r){if(e.o(n,t)&&(o=n[t],0!==o&&(n[t]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;u.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",u.name="ChunkLoadError",u.type=a,u.request=i,o[1](u)}};e.l(i,c,"chunk-"+t,t)}},e.O.j=function(t){return 0===n[t]};var t=function(t,r){var o,a,i=r[0],u=r[1],c=r[2],f=0;if(i.some((function(t){return 0!==n[t]}))){for(o in u)e.o(u,o)&&(e.m[o]=u[o]);if(c)var l=c(e)}for(t&&t(r);f<i.length;f++)a=i[f],e.o(n,a)&&n[a]&&n[a][0](),n[a]=0;return e.O(l)},r=self["webpackChunkorbag_ui"]=self["webpackChunkorbag_ui"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=e.O(void 0,[998],(function(){return e(1934)}));r=e.O(r)})();
//# sourceMappingURL=app.e99590fe.js.map