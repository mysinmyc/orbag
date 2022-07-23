import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/list/:configurationItemType',
    name: 'list',
    component: () => import(/* webpackChunkName: "about" */ '../views/ListView.vue')
  },
  {
    path: '/search/:configurationItemType',
    name: 'search',
    component: () => import(/* webpackChunkName: "about" */ '../views/SearchView.vue')
  },
  {
    path: '/edit/:configurationItemType/:configurationItemId',
    name: 'edit',
    component: () => import(/* webpackChunkName: "about" */ '../views/EditView.vue')
  }  
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
