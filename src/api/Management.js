
import request from '@/utils/request'
// 导出操作
function formidableUtil(res, fileName) {
  const blob = new Blob([res], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' })
  if ('download' in document.createElement('a')) {
   // 非IE下载
   const elink = document.createElement('a')
   elink.download = fileName
   elink.style.display = 'none'
   elink.href = URL.createObjectURL(blob)
   document.body.appendChild(elink)
   elink.click()
   URL.revokeObjectURL(elink.href) // 释放URL 对象
   document.body.removeChild(elink)
  } else {
   // IE10+下载
   navigator.msSaveBlob(blob, fileName)
  }
}
//分页获取人员信息列表
export function getFindByPage(data) {
  return request({
    url: '/school/user/findByPage',
    method: 'get',
    params: data
  })
}
//人员信息-新增
export function addUser(data) {
  return request({
    url: '/school/user/addUser',
    method: 'post',
    data
  })
}
//根据id删除角色信息
export function deleteUser(data) {
  return request({
    url: `/school/user/deleteUser`,
    method: 'delete',
    params: data
  })
}
//人员信息-编辑
export function updateUser(data) {
  return request({
    url: '/school/user/updateUser',
    method: 'put',
    data
  })
}
//修改角色状态
export function updateState(data) {
  return request({
    url: '/spring/role/updateState',
    method: 'put',
    params: data
  })
}
//人员类型-分页查询
export function getfindType(data) {
  return request({
    url: '/school/type/findByPage',
    method: 'get',
    params: data
  })
}
//人员类型-移除
export function deleteById(data) {
  return request({
    url: `/school/type/deleteById`,
    method: 'delete',
    params: data
  })
}
//人员类型-新增
export function addType(data) {
  return request({
    url: '/school/type/addType',
    method: 'post',
    data
  })
}
//人员类型-编辑
export function updateType(data) {
  return request({
    url: '/school/type/updateType',
    method: 'put',
    data
  })
}
//出入记录-分页查询
export function userfindByPage(data) {
  return request({
    url: '/school/access/findByPage',
    method: 'get',
    params: data
  })
}
//出入管理-分页查询
export function qrFindByPage(data) {
  return request({
    url: '/school/qr/findByPage',
    method: 'get',
    params: data
  })
}
//出入管理-新增
export function addQr(data) {
  return request({
    url: '/school/qr/addQr',
    method: 'post',
    data
  })
}
//出入管理-移除
export function qrDeleteById(data) {
  return request({
    url: `/school/qr/deleteById`,
    method: 'delete',
    params: data
  })
}
//出入管理-编辑
export function updateQr(data) {
  return request({
    url: '/school/qr/updateQr',
    method: 'put',
    data
  })
}
//出入管理-出入记录-导出
export function exportInfo(data) {
  return request({
    url: '/school/access/exportInfo',
    method: 'get',
    params: data,
    responseType: 'blob'
  }).then(res => {
    formidableUtil(res,'出入信息.xlsx')
  });
}
//出入管理-下载打卡二维码
export function exportSaveImg(data) {
  return request({
    url: '/school/qr/saveImg',
    method: 'get',
    params: data,
    responseType: 'blob'
  }).then(res => {
    formidableUtil(res,'二维码.jpg')
  });
}
//人员类型-查询所有
export function findAllType(data) {
  return request({
    url: '/school/type/findAllType',
    method: 'get',
    params: data
  })
}
//人员信息-批量导入
export function batchImport(data) {
  return request({
    url: '/school/user/batchImport',
    method: 'post',
    data
  })
}
//人员信息-批量移除
export function deleteUserIds(data) {
  return request({
    url: `/school/user/deleteUserIds`,
    method: 'delete',
    data
  })
}

