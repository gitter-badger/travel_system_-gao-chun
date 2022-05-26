<template>
  <div class="app-container">
    线路站点
  </div>
</template>

<script>
export default {
  name: "routeSites",
  data () {
    return {
      routeId: null,
    }
  },

  created() {
    console.log(this.$route.query.route_id)
    this.routeId = this.$route.query.route_id
  }
}
</script>

<style scoped>

</style>
