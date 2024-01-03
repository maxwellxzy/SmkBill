import request from '@/utils/request'

// 查询商户号管理列表
export function listPaymchid(query) {
  return request({
    url: '/pay/paymchid/list',
    method: 'get',
    params: query
  })
}

// 查询商户号管理详细
export function getPaymchid(id) {
  return request({
    url: '/pay/paymchid/' + id,
    method: 'get'
  })
}

// 新增商户号管理
export function addPaymchid(data) {
  return request({
    url: '/pay/paymchid',
    method: 'post',
    data: data
  })
}

// 修改商户号管理
export function updatePaymchid(data) {
  return request({
    url: '/pay/paymchid',
    method: 'put',
    data: data
  })
}

// 删除商户号管理
export function delPaymchid(id) {
  return request({
    url: '/pay/paymchid/' + id,
    method: 'delete'
  })
}
