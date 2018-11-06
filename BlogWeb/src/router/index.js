import Vue from 'vue'
import Router from 'vue-router'
import LoginPage from '@/pages/LoginPage'
import MainPage from '@/pages/MainPage'
import ArticlePage from '@/pages/ArticlePage'
import BlogShowPage from "@/pages/BlogShowPage"

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
      path: '/article/edit',
      name: 'article',
      component:ArticlePage
    },
    {
      path: '/blog/:code',
      name: 'blog-show-page',
      component:BlogShowPage
    }
  ],
  proxyTable:{
    '/user/*' : {
      target: 'http://localhost:80'
    }
  }
})
