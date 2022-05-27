<template>
  <div class="dashboard-container">
    <h1>欢迎使用高淳旅游服务管理系统</h1>
    <video width="1280" height="720" controls="controls" autoplay="autoplay">
      <source
        src="https://outin-ccdd316fd0ec11ec8e0700163e06123c.oss-cn-shanghai.aliyuncs.com/656fb783b911432a85f99c4a20b3b51c/d5a4b5627d0a8eeb508d2e7a929b7ef3-sd.mp4?Expires=1653631106&OSSAccessKeyId=LTAIrkwb21KyGjJl&Signature=hc3jJV3vFzavIQRpvNJxOOa2LLE%3D"
        type="video/mp4"
      />
    </video>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'


export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard-container{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  h1{
    text-align: center;
  }
  video{
    align-self: center;
  }
}

</style>
