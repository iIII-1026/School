import request from '@/utils/request'

export function getList(params) {
    return request({
        url: '/api/vue-admin-template/table/list',
        method: 'get',
        params
    })
}