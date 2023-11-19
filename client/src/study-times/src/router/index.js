import * as VueRouter from 'vue-router'
import * as Names from './names'


const routes = [
  {
    path: '/',
    name: Names.Home,
    displayName: '홈',
    icon: 'mdi-home',
    component: () => import('../views/index.vue')
  },
  {
    path: '/mypage',
    name: Names.MyPage,
    displayName: '나의 정보',
    icon: 'mdi-account',
    component: () => import('../views/mypage/index.vue')
  },
  {
    path: '/mypage/join',
    name: Names.Join,
    displayName: '회원가입',
    icon: 'mdi-account',
    hide: true,
    component: () => import('../views/mypage/Join.vue')
  },
  {
    path: '/mypage/login',
    name: Names.Login,
    displayName: '로그인',
    icon: 'mdi-account',
    component: () => import('../views/mypage/Login.vue')
  }
] 

// https://router.vuejs.org/guide/advanced/meta.html
// lazy loading routes
const router = VueRouter.createRouter({
  // https://router.vuejs.org/guide/advanced/meta.html
  history: VueRouter.createWebHistory(),
  routes: routes,
})

const tables = routes.reduce((a,c) => ({
  ...a,
  [c.name] : c
}), {})

const getPath = (symbol) => tables[symbol].path

export { Names, routes, getPath }
export default router