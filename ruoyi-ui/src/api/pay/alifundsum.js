import request from '@/utils/request'

// 查询支付宝资金汇总列表
export function listAlifundsum(query) {
  return request({
    url: '/pay/alifundsum/list',
    method: 'get',
    params: query
  })
}

// 查询支付宝资金汇总详细
export function getAlifundsum(columnId) {
  return request({
    url: '/pay/alifundsum/' + columnId,
    method: 'get'
  })
}

// 新增支付宝资金汇总
export function addAlifundsum(data) {
  return request({
    url: '/pay/alifundsum',
    method: 'post',
    data: data
  })
}

// 修改支付宝资金汇总
export function updateAlifundsum(data) {
  return request({
    url: '/pay/alifundsum',
    method: 'put',
    data: data
  })
}

// 删除支付宝资金汇总
export function delAlifundsum(columnId) {
  return request({
    url: '/pay/alifundsum/' + columnId,
    method: 'delete'
  })
}
