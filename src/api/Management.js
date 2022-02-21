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
//分页查询人员信息列表
export function getFindByPage(data) {
    return request({
        url: '/api/school/user/findByPage',
        method: 'get',
        params: data
    })
}
//人员信息-新增
export function addUser(data) {
    return request({
        url: '/api/school/user/addUser',
        method: 'post',
        data
    })
}
//人员信息-移除-根据id删除角色信息
export function deleteUser(data) {
    return request({
        url: '/api/school/user/deleteUser',
        method: 'delete',
        params: data
    })
}
//人员信息-编辑
export function updateUser(data) {
    return request({
        url: '/api/school/user/updateUser',
        method: 'put',
        data
    })
}
//修改角色状态
export function updateState(data) {
    return request({
        url: '/api/spring/role/updateState',
        method: 'put',
        params: data
    })
}
//人员类型-分页查询
export function getfindType(data) {
    return request({
        url: '/api/school/type/findByPage',
        method: 'get',
        params: data
    })
}
//人员类型-移除
export function deleteById(data) {
    return request({
        url: '/api/school/type/deleteById',
        method: 'delete',
        params: data
    })
}
//人员类型-新增
export function addType(data) {
    return request({
        url: '/api/school/type/addType',
        method: 'post',
        data
    })
}
//角色-新增
export function insertRole(data) {
    return request({
        url: '/api/school/role/insertRole',
        method: 'post',
        data
    })
}
//角色-删除
export function deleteRole(data) {
    return request({
        url: '/api/school/role/deleteRole',
        method: 'delete',
        params: data

    })
}
//角色-编辑
export function updateRole(data) {
    return request({
        url: '/api/school/role/updateRole',
        method: 'put',
        data
    })
}
//账号-新增
export function insertAdmin(data) {
    return request({
        url: '/api/school/admin/insertAdmin',
        method: 'post',
        data
    })
}
// 账号-编辑
export function updateAdmin(data) {
    return request({
        url: '/api/school/admin/updateAdmin ',
        method: 'put',
        data
    })
}
// 账号-删除
export function deleteAdmin(data) {
    return request({
        url: '/api/school/admin/deleteAdmin',
        method: 'delete',
        params: data

    })
}
// 权限-新增
export function insertPerm(data) {
    return request({
        url: '/api/school/perm/insertPerm',
        method: 'post',
        data
    })
}
// 权限-编辑
export function updatePerm(data) {
    return request({
        url: '/api/school/perm/updatePerm ',
        method: 'put',
        data
    })
}
// 权限-删除
export function deletePerm(data) {
    return request({
        url: '/api/school/perm/deletePerm',
        method: 'delete',
        params: data

    })
}
//人员类型-编辑
export function updateType(data) {
    return request({
        url: '/api/school/type/updateType',
        method: 'put',
        data
    })
}
//出入记录-分页查询
export function userfindByPage(data) {
    return request({
        url: '/api/school/access/findByPage',
        method: 'get',
        params: data
    })
}
//出入管理-分页查询
export function qrFindByPage(data) {
    return request({
        url: '/api/school/qr/findByPage',
        method: 'get',
        params: data
    })
}
//出入管理-新增
export function addQr(data) {
    return request({
        url: '/api/school/qr/addQr',
        method: 'post',
        data
    })
}
//出入管理-移除
export function qrDeleteById(data) {
    return request({
        url: '/api/school/qr/deleteById',
        method: 'delete',
        params: data
    })
}
//出入管理-编辑
export function updateQr(data) {
    return request({
        url: '/api/school/qr/updateQr',
        method: 'put',
        data
    })
}
//出入管理-出入记录-导出
// export function exportInfo(data) {
//     return request({
//         url: '/api/school/access/exportInfo',
//         method: 'get',
//         params: data,
//         responseType: 'blob'
//     }).then(res => {
//         formidableUtil(res, '出入信息.xlsx')
//     });
// }

// 导出2.0
export function exportInfo(params) {
    return request({
        url: '/api/school/access/exportInfo',
        method: 'get',
        responseType: 'blob',
        params,
        paramsSerializer: params => {
            return qs.stringify(params, { indices: false })
        },
    })
}
//出入管理-显示打卡二维码
export function exportSaveImg(data) {
    return request({
        url: '/api/school/qr/saveImg',
        method: 'get',
        params: data,
        responseType: 'blob'
    }).then(res => {
        formidableUtil(res, '二维码.jpg')
    });
}
//出入管理-下载打卡二维码
export function downloadSaveImg(data) {
    return request({
        url: '/api/school/qr/downloadQrImg',
        method: 'get',
        params: data,
        responseType: 'blob'
    }).then(res => {
        formidableUtil(res, '二维码.jpg')
    });
}
//人员类型-查询所有
export function findAllType(data) {
    return request({
        url: '/api/school/type/findType',
        method: 'get',
        params: data
    })
}
//人员信息-批量导入
export function batchImport(data) {
    return request({
        url: '/api/school/user/batchImport',
        method: 'post',
        data
    })
}
//人员信息-批量移除
export function deleteUserIds(data) {
    return request({
        headers: {
            'Content-type': 'application/json'
        },
        url: '/api/school/user/deleteUserIds',
        method: 'delete',
        params: data
    })
}