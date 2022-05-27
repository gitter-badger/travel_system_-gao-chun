import request from "@/utils/request";

export default {
  // 根据mark_id查询所有图片
  findAllPictureByMarkId(id) {
    return request({
      url: `/travel_system/oss/picture/${id}/findAll`,
      method: 'get',
    })
  }

}
