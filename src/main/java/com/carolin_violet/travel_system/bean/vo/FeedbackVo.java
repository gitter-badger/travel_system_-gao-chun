package com.carolin_violet.travel_system.bean.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FeedbackVo
 * @Description TODO
 * @Author zj
 * @Date 2022/5/22 20:15
 * @Version 1.0
 */
@Data
public class FeedbackVo {

    private String mail;

    private String content;

    // 图片url列表
    private List<String> photoUrlList = new ArrayList<>();
}
