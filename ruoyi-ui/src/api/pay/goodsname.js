import request from '@/utils/request'

// 查询商品名称分类列表
export function listGoodsname(query) {
  return request({
    url: '/pay/goodsname/list',
    method: 'get',
    params: query
  })
}

// 查询商品名称分类详细
export function getGoodsname(id) {
  return request({
    url: '/pay/goodsname/' + id,
    method: 'get'
  })
}

// 新增商品名称分类
export function addGoodsname(data) {
  return request({
    url: '/pay/goodsname',
    method: 'post',
    data: data
  })
}

// 修改商品名称分类
export function updateGoodsname(data) {
  return request({
    url: '/pay/goodsname',
    method: 'put',
    data: data
  })
}

// 删除商品名称分类
export function delGoodsname(id) {
  return request({
    url: '/pay/goodsname/' + id,
    method: 'delete'
  })
}
