import request from '@/utils/request'

export default {
  // 获取所有旅游线路信息
  getAllRoute() {
    return request({
      url: '/travel_system/tourist-route/findAll',
      method: 'get'
    })
  },

  // 添加旅游线路信息
  addRoute(data) {
    return request({
      url: '/travel_system/tourist-route/addRoute',
      method: 'post',
      data
    })
  },

  // 修改旅游线路信息
  updateRoute(data) {
    return request({
      url: '/travel_system/tourist-route/updateRoute',
      method: 'put',
      data
    })
  },

  // 根据id删除旅游线路
  removeHotel(id) {
    return request({
      url: `/travel_system/tourist-route/${id}`,
      method: 'delete'
    })
  },

  // 根据线路id查询该线路所有站点信息
  getAllSitesByRouteId(id) {
    return request({
      url: `/travel_system/tourist-route/${id}/findAll`,
      method: 'get'
    })
  }
}
