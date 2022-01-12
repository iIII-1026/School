import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [//{
    //         path: '/login',
    //         component: () =>
    //             import ('@/views/login/index'),
    //         hidden: true
    //     },
    // 404模块
    // {
    //     path: '/404',
    //     component: () =>
    //         import ('@/views/404'),
    //     hidden: true
    // },
    // 数据统计模块
    {
        path: '/',
        component: Layout,
        redirect: '/dashboard',
        children: [{
            path: 'dashboard',
            name: 'Dashboard',
            component: () =>
                import('@/views/dashboard/index'),
            meta: { title: '数据统计', icon: 'dashboard' }
        }]
    },
    // 人员管理模块
    {
        path: '/example',
        component: Layout,
        redirect: '/example/perinfo',
        name: 'Example',
        meta: { title: '人员管理', icon: 'el-icon-user-solid' },
        children: [{
            path: 'perinfo',
            name: 'perinfo',
            component: () =>
                import('@/views/perinfo/index'),
            meta: { title: '人员信息', icon: 'table' }
        },
        {
            path: 'pertypemanage',
            name: 'pertypemanage',
            component: () =>
                import('@/views/pertypemanage/index'),
            meta: { title: '人员类型管理', icon: 'tree' }
        }
        ]
    },
    // 出入管理模块
    {
        path: '/accessmanage',
        component: Layout,
        redirect: '/accessmanage/accessrecord',
        name: 'Accessmanage',
        meta: {
            title: '出入管理',
            icon: 'el-icon-s-help'
        },
        children: [{
            path: 'accessrecord',
            component: () =>
                import('@/views/accessmanage/accessrecord/index'),
            name: '出入记录',
            meta: { title: '出入记录', icon: 'el-icon-document' },
        },
        {
            path: 'accessconfig',
            component: () =>
                import('@/views/accessmanage/accessconfig/index'),
            name: '出入配置',
            meta: { title: '出入配置', icon: 'el-icon-edit-outline' }
        },

        ]
    },
    // 系统设置模块
    {
        path: '/systemset',
        component: Layout,
        redirect: '/systemset/rolemanage',
        name: 'systemset',
        meta: {
            title: '系统设置',
            icon: 'el-icon-setting'
        },
        children: [{
            path: 'rolemanage',
            component: () =>
                import('@/views/systemset/rolemanage/index'),
            name: '角色管理',
            meta: { title: '角色管理' },
        },
        {
            path: 'powermanage',
            component: () =>
                import('@/views/systemset/powermanage/index'),
            name: '权限管理',
            meta: { title: '权限管理' }
        },
        {
            path: 'accountmanage',
            component: () =>
                import('@/views/systemset/accountmanage/index'),
            name: '账号管理',
            meta: { title: '账号管理' }
        },

        ]
    },

    // 404 page must be placed at the end !!!
    { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
    mode: '', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router
