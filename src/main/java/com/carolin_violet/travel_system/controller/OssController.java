package com.carolin_violet.travel_system.controller;

import com.carolin_violet.travel_system.service.OssService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author zj
 * @Date 2022/5/23 10:38
 * @Version 1.0
 */

@RestController
@RequestMapping("oss/picture")
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadOssPicture(MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
