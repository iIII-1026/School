import request from '@/utils/request'
//首页
export function finData(data) {
    return request({
        url: '/api/school/mainPage/findData',
        method: 'get',
        params: data
    })
}
//首页-不同时段统计
export function findHours(data) {
    return request({
        url: '/api/school/mainPage/findHour',
        method: 'get',
        params: data
    })
}
//首页-年度统计
export function findMonths(data) {
    return request({
        url: '/api/school/mainPage/findMonths',
        method: 'get',
        params: data
    })
}
//各出入口不同角色人次对比
export function findRole(data) {
    return request({
        url: '/api/school/mainPage/findRole',
        method: 'get',
        params: data
    })
}