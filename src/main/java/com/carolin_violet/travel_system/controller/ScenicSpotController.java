package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.ScenicSpot;
import com.carolin_violet.travel_system.bean.conditionQuery.ScenicSpotQuery;
import com.carolin_violet.travel_system.service.ScenicSpotService;
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
@RequestMapping("/api/scenic-spot")
public class ScenicSpotController {

    @Autowired
    private ScenicSpotService scenicSpotService;

    // 查询所有景点
    @GetMapping("findAll")
    public R findAllScenicSpot() {
        List<ScenicSpot> list = scenicSpotService.list(null);
        return R.ok().data("items", list);
    }

    // 添加景点
    @PostMapping("addScenicSpot")
    public R AddScenicSpot(@RequestBody ScenicSpot scenicSpot) {
        boolean save = scenicSpotService.save(scenicSpot);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 修改景点信息
    @PutMapping("updateScenicSpot")
    public R updateScenicSpot(@RequestBody ScenicSpot scenicSpot) {
        boolean flag = scenicSpotService.updateById(scenicSpot);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 删除景点
    @DeleteMapping("{id}")
    public R removeScenicSpot(@PathVariable String id) {
        boolean flag = scenicSpotService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 根据展示优先级进行条件查询
    @PostMapping("condition")
    public R findCondition(@RequestBody ScenicSpotQuery scenicSpotQuery) {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("popular", scenicSpotQuery.getPopular());
        List<ScenicSpot> scenicSpots = scenicSpotService.list(wrapper);
        return R.ok().data("items", scenicSpots);
    }
}

