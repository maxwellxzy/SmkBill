import request from '@/utils/request'

// 查询微信支付资金账单列表
export function listWefund(query) {
  return request({
    url: '/pay/wefund/list',
    method: 'get',
    params: query
  })
}

// 查询微信支付资金账单详细
export function getWefund(columnId) {
  return request({
    url: '/pay/wefund/' + columnId,
    method: 'get'
  })
}

// 新增微信支付资金账单
export function addWefund(data) {
  return request({
    url: '/pay/wefund',
    method: 'post',
    data: data
  })
}

// 修改微信支付资金账单
export function updateWefund(data) {
  return request({
    url: '/pay/wefund',
    method: 'put',
    data: data
  })
}

// 删除微信支付资金账单
export function delWefund(columnId) {
  return request({
    url: '/pay/wefund/' + columnId,
    method: 'delete'
  })
}
