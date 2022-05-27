<template>
  <div class="app-container">

<!--  上传图片  -->
      <el-upload
        :action="BASE_API + `/travel_system/oss/picture/addPhoto/${markeId}`"
        list-type="picture-card"
        :on-preview="handlePictureCardPreview"
        :on-remove="handleRemove"
        :on-success="handleUploadSuccess"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>


    <div class="photo-container" v-for="src in srcList">
      <div class="item">
        <el-image :src="src" class="img" :preview-src-list="[src]">
          <div slot="placeholder" class="image-slot">
            <img src="/avatar.gif" alt="">
          </div>
        </el-image>
      </div>
    </div>


  </div>
</template>

<script>
import photos from "@/api/photos";

export default {
  name: "index",
  data() {
    return {
      markeId: null,
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      srcList: [],
      dialogImageUrl: '',
      dialogVisible: false,
      BASE_API: process.env.VUE_APP_BASE_API
    }
  },

  created() {
    console.log(this.$route.query.mark_id)
    this.markeId = this.$route.query.mark_id
    this.findAllPictureByMarkId()
  },

  methods: {
    async findAllPictureByMarkId() {
      let res = await photos.findAllPictureByMarkId(this.markeId)
      console.log(res)
      this.srcList = res.data.items
    },

    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleUploadSuccess(res, file, fileList) {
      if (res.code == 20000) {
        this.$message.success("上传成功")
        console.log("上传成功:",res.data.url)
        this.findAllPictureByMarkId()
      } else {
        this.$message.error("上传失败")
      }
    }
  }
}
</script>

<style scoped lang="scss">
.photo-container{
  display: flex;
  flex-flow: column wrap;
  height: 100vh;
  .item{
    margin: 10px;
    width: calc(100%/3 - 120px);
    .img{
      width: 100%;
      height: 100%;
    }
  }
}
</style>
