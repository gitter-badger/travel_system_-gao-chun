package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.Delicacy;
import com.carolin_violet.travel_system.bean.conditionQuery.DelicacyQuery;
import com.carolin_violet.travel_system.service.DelicacyService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/travel_system/delicacy")
public class DelicacyController {
    @Autowired
    private DelicacyService delicacyService;

    // 查询所有美食
    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ROLE_DELICACY')")
    public R findAllDelicacy() {
        QueryWrapper<Delicacy> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("popular");
        List<Delicacy> list = delicacyService.list(wrapper);
        return R.ok().data("items", list);
    }

    // 添加美食信息
    @PreAuthorize("hasAnyAuthority('ROLE_DELICACY')")
    @PostMapping("addDelicacy")
    public R addDelicacy(@RequestBody Delicacy delicacy) {
        System.out.println("-------------------------------");
        System.out.println(delicacy.getDescription());
        boolean save = delicacyService.save(delicacy);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 修改美食信息
    @PreAuthorize("hasAnyAuthority('ROLE_DELICACY')")
    @PutMapping("updateDelicacy")
    public R updateDelicacy(@RequestBody Delicacy delicacy) {
        boolean flag = delicacyService.updateById(delicacy);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 删除美食信息
    @PreAuthorize("hasAnyAuthority('ROLE_DELICACY')")
    @DeleteMapping("{id}")
    public R removeDelicacy(@PathVariable String id) {
        boolean flag = delicacyService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

