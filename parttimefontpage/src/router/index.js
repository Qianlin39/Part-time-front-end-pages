import { createRouter, createWebHistory } from 'vue-router'
import {useCounterStore} from '@/stores/counter.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path:'/',
      name:'login',
      component:()=>import('@/views/UserLogin.vue')
    },
    {
      path:'/login',
      name:'login',
      component:()=>import('@/views/UserLogin.vue'),

    },
    {
      path:'/home',
      name:'home',
        component:()=>import('@/views/Home.vue'),
      meta:{requiresAuth:true},
     children:[
       {
         path:'user',
            name:'user',
            component:()=>import('@/components/User/Personalcenter.vue'),
       },
       {
         path:'Message',
         name:'Message',
            component:()=>import('@/components/Message/Message.vue'),
       },
       {
         path:'Post',
            name:'Post',
                component:()=>import('@/components/post/Post.vue'),
       },
       {
         path:'EditPost',
            name:'EditPost',
                component:()=>import('@/components/post/EditPost.vue'),
       },
       {
         path:'Help',
            name:'Help',
                component:()=>import('@/components/forum/Help.vue'),
       },
       {
         path:'Share',
            name:'Share',
                component:()=>import('@/components/forum/Share.vue'),
       },
         {

             path: 'post/:id',  // 动态路由
             name: 'PostDetail',
             component: () => import('@/components/forum/PostDetail.vue'),

         },
         {
             path: '/edit-post/:userid',
             name: 'Edit-Post',
             component: () => import('@/components/post/EditPostPage.vue'),
         }

     ]

    }
  ],
})
router.beforeEach((to, from, next) => {
  const CounterStore = useCounterStore();
  if(to.meta.requiresAuth && !CounterStore.email) {
    next('/login')
  }else{
    next()
  }
})

export default router
