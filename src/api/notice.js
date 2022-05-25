import request from '@/utils/request'

export default {
  // 获取所有公告信息
  getAllNotice() {
    return request({
      url: '/travel_system/notice/findAll',
      method: 'get'
    })
  },

  // 添加公告信息
  addNotice(data) {
    return request({
      url: '/travel_system/notice/addNotice',
      method: 'post',
      data
    })
  },

  // 修改公告信息
  updateNotice(data) {
    return request({
      url: '/travel_system/notice/updateNotice',
      method: 'put',
      data
    })
  },

  // 根据id删除公告
  removeNotice(id) {
    return request({
      url: `/travel_system/notice/${id}`,
      method: 'delete'
    })
  }
}
