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


    <div class="photo-container">
      <div class="item"  v-for="src in srcList">
        <el-image :src="src" :preview-src-list="srcList" lazy="lazy">
          <div slot="placeholder" class="image-slot">
            <img src="/loading.gif" alt="">
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
      srcList: [],
      dialogImageUrl: '',
      dialogVisible: false,
      BASE_API: process.env.VUE_APP_BASE_API
    }
  },

  created() {
    this.markeId = this.$route.query.mark_id
    this.findAllPictureByMarkId()
  },

  methods: {
    async findAllPictureByMarkId() {
      let res = await photos.findAllPictureByMarkId(this.markeId)
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
  margin-top: 50px;
  width: 100%;
  height: 100%;
  column-count: 5;
  column-gap: 20px;
  .item{
    margin-bottom: 10px;
    .el-image{
      width: 100%;
      height: auto;
    }
  }
}
</style>
