package com.carolin_violet.travel_system.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.Photos;
import com.carolin_violet.travel_system.bean.TravelNote;
import com.carolin_violet.travel_system.bean.vo.TravelNoteVo;
import com.carolin_violet.travel_system.handler.MyException;
import com.carolin_violet.travel_system.mapper.TravelNoteMapper;
import com.carolin_violet.travel_system.service.PhotosService;
import com.carolin_violet.travel_system.service.TravelNoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carolin_violet.travel_system.utils.OssPropertiesUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author carolin-violet
 * @since 2022-05-22
 */
@Service
public class TravelNoteServiceImpl extends ServiceImpl<TravelNoteMapper, TravelNote> implements TravelNoteService {

    @Autowired
    private PhotosService photosService;

    // 在删除游记前先删除图片
    @Override
    public void removePhotos(String id) {

        String endpoint = OssPropertiesUtils.END_POINT;
        String accessKeyId = OssPropertiesUtils.KEY_ID;
        String accessKeySecret = OssPropertiesUtils.KEY_SECRET;
        String bucketName = OssPropertiesUtils.BUCKET_NAME;

        //.获取图片url列表
        QueryWrapper<Photos> wrapper = new QueryWrapper<>();
        wrapper.eq("mark_id", id);
        List<Photos> photoObjectList = photosService.list(wrapper);

        // 遍历图片列表，切割字符串，将所有图片路径存入keys
        List<String> keys = new ArrayList<>();
        for (Photos photo : photoObjectList) {
            String[] arr = photo.getPicture().split("/");
            String url = arr[arr.length-2] + "/" + arr[arr.length-1];
            keys.add(url);
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除请求
        DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName);
        // 赋值需要删除的文件
        deleteRequest.setKeys(keys);
        //deleteRequest.setEncodingType(URL_ENCODING);
        // 调用删除
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteRequest);
        // 获取删除的文件
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭OSSClient。
        ossClient.shutdown();

        // 2. 删除表中图片信息
        photosService.remove(wrapper);
    }


    // 添加游记
    @Override
    public void addNote(TravelNoteVo travelNoteVo) {

        // 获取图片列表
        List<String> photoList = travelNoteVo.getPhotoUrlList();

        // 添加游记获取id
        TravelNote travelNote = new TravelNote();
        BeanUtils.copyProperties(travelNoteVo, travelNote);
        int insert = baseMapper.insert(travelNote);
        if (insert == 0) {
            throw new MyException(20001, "上传游记失败");
        }

        String id = travelNote.getId();

        // 添加图片
        for (int i = 0; i < photoList.size(); i++) {
            Photos photo = new Photos();
            photo.setMarkId(id);
            photo.setPicture(photoList.get(i));
            boolean save = photosService.save(photo);
            if (!save) {
                throw new MyException(20001, "添加图片失败");
            }
        }
    }

}
