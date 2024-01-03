import request from '@/utils/request'

// 查询业务分类列表
export function listTradetype(query) {
  return request({
    url: '/pay/tradetype/list',
    method: 'get',
    params: query
  })
}

// 查询业务分类详细
export function getTradetype(id) {
  return request({
    url: '/pay/tradetype/' + id,
    method: 'get'
  })
}

// 新增业务分类
export function addTradetype(data) {
  return request({
    url: '/pay/tradetype',
    method: 'post',
    data: data
  })
}

// 修改业务分类
export function updateTradetype(data) {
  return request({
    url: '/pay/tradetype',
    method: 'put',
    data: data
  })
}

// 删除业务分类
export function delTradetype(id) {
  return request({
    url: '/pay/tradetype/' + id,
    method: 'delete'
  })
}
