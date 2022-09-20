import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
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
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
