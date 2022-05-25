import request from '@/utils/request'

export default {
  // 获取所有管理员信息
  getAllManager() {
    return request({
      url: '/travel_system/manager/findAll',
      method: 'get'
    })
  },

  // 添加管理员
  addManager(data) {
    return request({
      url: '/travel_system/manager/addManager',
      method: 'post',
      data
    })
  },

  // 修改管理员信息
  updateManager(data) {
    return request({
      url: '/travel_system/manager/updateManager',
      method: 'put',
      data
    })
  },

  // 根据id删除用户
  removeManager(id) {
    return request({
      url: `/travel_system/manager/${id}`,
      method: 'delete'
    })
  },

  // 查询所有角色
  getAllRole() {
    return request({
      url: '/travel_system/role/findAll',
      method: 'get'
    })
  },

  // 根据用户id给用户添加权限
  addPermission(data) {
    return request({
      url: '/travel_system/permission/addPerssion',
      method: 'post',
      data
    })
  },

  // 根据权限id删除权限信息
  delPermission(id) {
    return request({
      url: `/travel_system/permission/${id}`,
      method: 'delete'
    })
  },

}
