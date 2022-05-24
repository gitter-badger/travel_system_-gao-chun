package com.carolin_violet.travel_system.controller;

import com.carolin_violet.travel_system.bean.Manager;
import com.carolin_violet.travel_system.service.ManagerService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ManagerInfoController
 * @Description TODO
 * @Author zj
 * @Date 2022/5/22 9:10
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/manager")
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    // 查询所有管理员信息
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping("findAll")
    public R findAllManager() {
        List<Manager> list = managerService.list(null);
        return R.ok().data("items", list);
    }

    // 添加管理员信息
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PostMapping("addManager")
    public R addManager(@RequestBody Manager manager) {
        boolean save = managerService.save(manager);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 修改管理员信息
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @PutMapping("updateManager")
    public R updateManager(@RequestBody Manager manager) {
        boolean flag = managerService.updateById(manager);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 逻辑删除管理员
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @DeleteMapping("{id}")
    public R removeManger(@PathVariable String id) {
        boolean flag = managerService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
