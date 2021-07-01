import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            component: Home
        },
        {
            path: '/booksPage',
            component: () => import('./views/Books.vue')
        }, {
            path: '/authorsPage',
            component: () => import('./views/Authors.vue')
        }
    ]

})