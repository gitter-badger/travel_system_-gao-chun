package com.carolin_violet.travel_system.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.Feedback;
import com.carolin_violet.travel_system.bean.Photos;
import com.carolin_violet.travel_system.bean.vo.FeedbackVo;
import com.carolin_violet.travel_system.mapper.FeedbackMapper;
import com.carolin_violet.travel_system.service.FeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carolin_violet.travel_system.service.PhotosService;
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
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private PhotosService photosService;

    // 添加反馈
    @Override
    public void addFeedback(FeedbackVo feedbackVo) {
        // 1. 添加反馈获取id
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackVo, feedback);
        baseMapper.insert(feedback);

        String id = feedback.getId();

        // 2.判断添加图片
        List<String> photoList = feedbackVo.getPhotoUrlList();
        if (photoList.size() > 0) {
            for (int i = 0; i < photoList.size(); i++) {
                Photos photo = new Photos();
                photo.setMarkId(id);
                photo.setPicture(photoList.get(i));
                photosService.save(photo);
            }
        }

    }

    // 删除反馈
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
}
