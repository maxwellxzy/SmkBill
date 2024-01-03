import request from '@/utils/request'

// 查询微信交易订单列表
export function listWetrade(query) {
  return request({
    url: '/pay/wetrade/list',
    method: 'get',
    params: query
  })
}

// 查询微信交易订单详细
export function getWetrade(columnId) {
  return request({
    url: '/pay/wetrade/' + columnId,
    method: 'get'
  })
}

// 新增微信交易订单
export function addWetrade(data) {
  return request({
    url: '/pay/wetrade',
    method: 'post',
    data: data
  })
}

// 修改微信交易订单
export function updateWetrade(data) {
  return request({
    url: '/pay/wetrade',
    method: 'put',
    data: data
  })
}

// 删除微信交易订单
export function delWetrade(columnId) {
  return request({
    url: '/pay/wetrade/' + columnId,
    method: 'delete'
  })
}
