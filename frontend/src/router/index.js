import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "about" */ '../views/ProductsPage.vue')
  },
  {
    path: '/types',
    name: 'types',
    component: () => import(/* webpackChunkName: "about" */ '../views/ProductTypesPage.vue')
  },
  {
    path: '/movements',
    name: 'movements',
    component: () => import(/* webpackChunkName: "about" */ '../views/MovementsPage.vue')
  },
  {
    path: '/sales',
    name: 'sales',
    component: () => import(/* webpackChunkName: "about" */ '../views/SalesPage.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
