package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carolin_violet.travel_system.bean.Notice;
import com.carolin_violet.travel_system.service.NoticeService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    // 根据时间排序分页查询广告
    @GetMapping("pageNotice/{current}/{limit}")
    public R findAllNotice(@PathVariable long current, @PathVariable long limit) {
        // 创建Page对象用于分页
        Page<Notice> pageNotice = new Page<>(current, limit);

        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");

        // 调用方法实现分页查询
        noticeService.page(pageNotice, wrapper);

        // 查询总条数
        long total = pageNotice.getTotal();
        // 查询一页的数据
        List<Notice> records = pageNotice.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

    // 发布公告
    @PostMapping("addNotice")
    public R addNotice(@RequestBody Notice notice) {
        boolean save = noticeService.save(notice);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 更新公告信息
    @PutMapping("updateNotice")
    public R updateNotice(@RequestBody Notice notice) {
        boolean flag = noticeService.updateById(notice);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 删除公告
    @DeleteMapping("{id}")
    public R removeNotice(@PathVariable String id) {
        boolean flag = noticeService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

