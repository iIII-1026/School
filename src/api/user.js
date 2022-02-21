import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/api/school/admin/login',
        method: 'post',
        data
    })
}

export function getfindByPage(query) {
    console.log(query)
    return request({
        url: '/api/school/user/findByPage',
        method: 'get',
        params: {
            pageCurrent: query.pageCurrent,
            pageSize: query.pageSize,
            name: query.name,
            card: query.card,
            typeId: query.typeId,
            state: query.state,
            startTime: query.startTime
        }
    })
}
export function getAdd(query) {
    console.log(query)
    return request({
        headers: {
            'Content-type': 'application/json'
        },
        url: '/api/school/user/addUser',
        method: 'post',
        params: {},
        data: {
            name: query.name,
            typeId: query.type,
            state: query.state,
            card: query.card,
            phone: query.phone,
            expiry: query.expiry,
            remark: query.remark,
            createTime: query.createTime,
        }
    })
}
export function getEdit(query) {
    console.log(query)
    return request({
        headers: {
            'Content-type': 'application/json'
        },
        url: '/api/school/user/updateUser',
        method: 'put',
        params: {},
        data: {
            id: query.id,
            name: query.name,
            typeId: query.type,
            state: query.state,
            card: query.card,
            phone: query.phone,
            expiry: query.expiry,
            remark: query.remark,
            createTime: query.createTime,
        }
    })
}
export function getdelete(query) {
    console.log(query)
    return request({
        url: '/api/school/user/deleteUserIds',
        method: 'delete',
        params: { ids: query }
    })
}
export function logout() {
    return request({
        url: '/vue-admin-template/user/logout',
        method: 'post'
    })
}