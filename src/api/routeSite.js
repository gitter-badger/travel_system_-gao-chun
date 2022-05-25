import request from '@/utils/request'

export default {

  // 添加站点信息
  addSite(data) {
    return request({
      url: '/travel_system/route_detail/addSite',
      method: 'post',
      data
    })
  },

  // 修改站点信息
  updateSite(data) {
    return request({
      url: '/travel_system/route_detail/updateSite',
      method: 'put',
      data
    })
  },

  // 根据id删除站点
  removeSite(id) {
    return request({
      url: `/travel_system/route_detail/${id}`,
      method: 'delete'
    })
  }
}
