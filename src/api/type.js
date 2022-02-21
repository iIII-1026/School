import request from '@/utils/request'
export function getfindByPage(query) {
    console.log(query)
    return request({
        url: '/api/type/findByPage',
        method: 'get',
        params: {
            pageCurrent: query.pageCurrent,
            pageSize: query.pageSize,
            name: query.name,
            startTime: query.startTime,
        }
    })
}