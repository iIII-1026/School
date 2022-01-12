import request from '@/utils/request'
export function getfindByPage(query) {
  console.log(query)
  return request({
    url: 'access/findByPage',
    method: 'get',
    params: {
      pageCurrent: query.pageCurrent, pageSize: query.pageSize, name: query.name,
      startTime: query.startTime, endTime: query.endTime, typeId: query.typeId,
      pip: query.pip, typeId: query.typeId, card: query.card, ctype: query.ctype
    }
  })
}