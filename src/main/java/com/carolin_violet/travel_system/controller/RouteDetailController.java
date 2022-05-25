package com.carolin_violet.travel_system.controller;


import com.carolin_violet.travel_system.bean.RouteDetail;
import com.carolin_violet.travel_system.service.RouteDetailService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carolin-violet
 * @since 2022-05-22
 */
@RestController
@RequestMapping("/travel_system/route-detail")
public class RouteDetailController {

    @Autowired
    private RouteDetailService routeDetailService;

    // 添加站点信息
    @PreAuthorize("hasAnyAuthority('ROLE_ROUTE')")
    @PostMapping("addSite")
    public R addSite(@RequestBody RouteDetail routeDetail) {
        boolean save = routeDetailService.save(routeDetail);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 修改站点信息
    @PreAuthorize("hasAnyAuthority('ROLE_ROUTE')")
    @PutMapping("updateSite")
    public R updateSite(@RequestBody RouteDetail routeDetail) {
        boolean flag = routeDetailService.updateById(routeDetail);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 删除站点信息
    @PreAuthorize("hasAnyAuthority('ROLE_ROUTE')")
    @DeleteMapping("{id}")
    public R removeSite(@PathVariable String id) {
        boolean flag = routeDetailService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

