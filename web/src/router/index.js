import { createRouter, createWebHistory } from 'vue-router'
import store from "@/store";
import {notification} from "ant-design-vue";

const routes = [
  {
    path: '/',
    component: () => import('../views/main.vue'),
    meta:{
      //自定义变量logInRequire  下面路由登录拦截会用到  变量为true就拦截处理  没有变量或者变量为false就跳过
      logInRequire:true
    },
    children:[{
      path:"welcome",
      component:()=>import('../views/main/welcome.vue')
    },{
      path: "passenger",
      component:()=>import("../views/main/passenger.vue")
    },
      {
        path: '/trainByTicket',
        component: () => import('../views/main/trainByTicket.vue')
      }]

  },
    //访问根域名 =  访问/welcome
  {
    path: '',
    redirect:'/welcome'
  },
  {
    path: '/login',
    component: () => import('../views/login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由登录拦截  to-跳到哪里 from-从哪里跳
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    console.log(item, "是否需要登录校验：", item.meta.loginRequire || false);
    return item.meta.loginRequire
  })) {
    const _member = store.state.member;
    console.log("页面登录校验开始：", _member);
    if (!_member.token) {
      console.log("用户未登录或登录超时！");
      notification.error({ description: "未登录或登录超时" });
      next('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});


export default router
