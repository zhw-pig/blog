import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/Home/IndexPage.vue'
import { environment } from '@/evn.config'

const router = createRouter({
  history: createWebHistory(environment.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
  ],
})

export default router
