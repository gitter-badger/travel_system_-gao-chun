package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carolin_violet.travel_system.bean.Feedback;
import com.carolin_violet.travel_system.bean.vo.FeedbackVo;
import com.carolin_violet.travel_system.service.FeedbackService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carolin-violet
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // 分页查询反馈信息
    @Secured("feedback")
    @RequestMapping("/pageFeedback/{current}/{limit}")
    public R getPageFeedback(@PathVariable long current, @PathVariable long limit) {
        Page<Feedback> feedbackPage = new Page<>(current, limit);

        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");

        feedbackService.page(feedbackPage, wrapper);

        long total = feedbackPage.getTotal();
        List<Feedback> records = feedbackPage.getRecords();

        return R.ok().data("rows", records).data("total", total);
    }

    // 上传反馈
    @Secured("feedback")
    @PostMapping("addFeedback")
    public R addFeedback(@RequestBody FeedbackVo feedbackVo) {
        feedbackService.addFeedback(feedbackVo);
        return R.ok();
    }

    // 删除反馈
    @Secured("feedback")
    @DeleteMapping("{id}")
    public R removeFeedback(@PathVariable String id) {
        feedbackService.removePhotos(id);
        feedbackService.removeById(id);
        return R.ok();
    }
}

