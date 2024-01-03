import request from '@/utils/request'

// 查询支付宝交易明细单列表
export function listAlitrade(query) {
  return request({
    url: '/pay/alitrade/list',
    method: 'get',
    params: query
  })
}

// 查询支付宝交易明细单详细
export function getAlitrade(columnId) {
  return request({
    url: '/pay/alitrade/' + columnId,
    method: 'get'
  })
}

// 新增支付宝交易明细单
export function addAlitrade(data) {
  return request({
    url: '/pay/alitrade',
    method: 'post',
    data: data
  })
}

// 修改支付宝交易明细单
export function updateAlitrade(data) {
  return request({
    url: '/pay/alitrade',
    method: 'put',
    data: data
  })
}

// 删除支付宝交易明细单
export function delAlitrade(columnId) {
  return request({
    url: '/pay/alitrade/' + columnId,
    method: 'delete'
  })
}
