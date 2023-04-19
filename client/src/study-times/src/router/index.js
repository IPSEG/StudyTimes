import * as VueRouter from 'vue-router'
import * as Names from './names'

// https://router.vuejs.org/guide/advanced/meta.html
// lazy loading routes
const router = VueRouter.createRouter({
  // https://router.vuejs.org/guide/advanced/meta.html
  history: VueRouter.createWebHistory(),
  routes: [
    {
      path: '/mypage',
      name: Names.MyPage,
      component: () => import('../views/mypage/index.vue')
    }
  ],
})

export { Names }
export default router