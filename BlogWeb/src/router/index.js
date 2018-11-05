import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import MainPage from '@/pages/MainPage'
import ArticlePage from '@/pages/ArticlePage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'main-page',
      component: MainPage
    },
    {
      path: '/login',
      name: 'login-page',
      component: LoginPage
    },
    {
      path: '/article/:code',
      name: 'article',
      component:ArticlePage
    },
  ],
  proxyTable:{
    '/user/*' : {
      target: 'http://localhost:80'
    }
  }
})
