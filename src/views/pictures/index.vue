<template>
  <div class="app-container">
    隐藏的，可以查看更多图片并添加更多图片
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      markeId: null
    }
  },

  created() {
    console.log(this.$route.query.mark_id)
    this.markeId = this.$route.query.mark_id
  }
}
</script>

<style scoped>

</style>
