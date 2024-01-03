import request from '@/utils/request'

// 查询每日汇总列表
export function listSum(query) {
  return request({
    url: '/pay/sum/list',
    method: 'get',
    params: query
  })
}

// 查询每日汇总详细
export function getSum(id) {
  return request({
    url: '/pay/sum/' + id,
    method: 'get'
  })
}

// 新增每日汇总
export function addSum(data) {
  return request({
    url: '/pay/sum',
    method: 'post',
    data: data
  })
}

// 修改每日汇总
export function updateSum(data) {
  return request({
    url: '/pay/sum',
    method: 'put',
    data: data
  })
}

// 删除每日汇总
export function delSum(id) {
  return request({
    url: '/pay/sum/' + id,
    method: 'delete'
  })
}
