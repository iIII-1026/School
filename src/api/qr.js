import request from '@/utils/request'
export function getfindByPage(query) {
    console.log(query)
    return request({
        url: '/api/qr/findByPage',
        method: 'get',
        params: {
            pageCurrent: query.pageCurrent,
            pageSize: query.pageSize,
            startTime: query.startTime,
            endTime: query.endTime,
            ctype: query.ctype,
            pip: query.pip
        }
    })
}