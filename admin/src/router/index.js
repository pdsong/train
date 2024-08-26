import { createRouter, createWebHistory } from 'vue-router'

const routes = [{
  path: '/',
  component: () => import('../views/main.vue'),
  children: [{
    path: 'welcome',
    component: () => import('../views/main/welcome.vue'),
  }, {
    path: 'about',
    component: () => import('../views/main/about.vue'),
  }, {
      path: 'station',
      component: () => import('../views/main/station.vue'),

  }, {
      path: 'train',
      component: () => import('../views/main/train.vue'),

  }, {
      path: 'train-station',
      component: () => import('../views/main/train-station.vue'),

  },
  //
  //   {
  //   path: 'business/',
  //   children: [{
  //     path: 'sk-token',
  //     component: () => import('../views/main/business/sk-token.vue'),
  //   }, {
  //     path: 'confirm-order',
  //     component: () => import('../views/main/business/confirm-order.vue'),
  //   }, {
  //     path: 'daily-train',
  //     component: () => import('../views/main/business/daily-train.vue'),
  //   }, {
  //     path: 'daily-train-station',
  //     component: () => import('../views/main/business/daily-train-station.vue'),
  //   }, {
  //     path: 'daily-train-carriage',
  //     component: () => import('../views/main/business/daily-train-carriage.vue'),
  //   }, {
  //     path: 'daily-train-seat',
  //     component: () => import('../views/main/business/daily-train-seat.vue'),
  //   }, {
  //     path: 'daily-train-ticket',
  //     component: () => import('../views/main/business/daily-train-ticket.vue'),
  //   }]
  // },
  //
  //   {
  //     path: 'batch/',
  //     children: [{
  //       path: 'job',
  //       component: () => import('../views/main/batch/job.vue')
  //     }]
  //   },
  //
  //   {
  //   path: 'member/',
  //   children: [{
  //     path: 'ticket',
  //     component: () => import('../views/main/member/ticket.vue')
  //   }]
  // }

  ]
}, {
  path: '',
  redirect: '/welcome'
}];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
