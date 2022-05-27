<template>
  <div class="dashboard-container">
    <video width="99%" height="90%" controls="controls" autoplay="autoplay" loop="loop">
      <source
        src="/石臼湖.mp4"
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
  flex-direction: column;
}

</style>
