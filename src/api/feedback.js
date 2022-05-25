import request from '@/utils/request'

export default {
  /*
  * 分页查询反馈
  * @current 当前页码
  * @limit 每页的记录数
  * */
  getPageFeedback(current, limit) {
    return request({
      url: `/travel_system/feedback/pageFeedback/${current}/${limit}`,
      method: 'get'
    })
  },

  // 根据id删除反馈
  removeFeedback(id) {
    return request({
      url: `/travel_system/feedback/${id}`,
      method: 'delete'
    })
  }
}
