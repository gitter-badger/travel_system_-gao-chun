package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.RouteDetail;
import com.carolin_violet.travel_system.bean.TouristRoute;
import com.carolin_violet.travel_system.service.RouteDetailService;
import com.carolin_violet.travel_system.service.TouristRouteService;
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
@RequestMapping("/api/tourist-route")
public class TouristRouteController {
    @Autowired
    private TouristRouteService touristRouteService;

    @Autowired
    RouteDetailService routeDetailService;

    // 查询所有线路
    @Secured("tourist_route")
    @GetMapping("findAll")
    public R findAllRoutes() {
        List<TouristRoute> list = touristRouteService.list(null);
        return R.ok().data("items", list);
    }

    // 根据线路id查询线路所有站点信息
    @Secured("tourist_route")
    @GetMapping("{id}/findAll")
    public R findAllSites(@PathVariable String id) {
        QueryWrapper<RouteDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("route_id", id);
        List<RouteDetail> list = routeDetailService.list(wrapper);
        return R.ok().data("items", list);
    }

    // 添加线路并返回线路id
    @Secured("tourist_route")
    @PostMapping("addRoute")
    public R addRoute(@RequestBody TouristRoute touristRoute) {
        boolean save = touristRouteService.save(touristRoute);
        if (save) {
            return R.ok().data("id", touristRoute.getId());
        } else {
            return R.error();
        }
    }

    // 修改线路信息
    @Secured("tourist_route")
    @PutMapping("updateRoute")
    public R updateRoute(@RequestBody TouristRoute touristRoute) {
        boolean flag = touristRouteService.updateById(touristRoute);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 删除线路,并且删除线路对应的站点信息
    @Secured("tourist_route")
    @DeleteMapping("{id}")
    public R removeRoute(@PathVariable String id) {
        // 根据线路id删除
        QueryWrapper<RouteDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("route_id", id);
        routeDetailService.remove(wrapper);

        // 根据线路id删除线路
        touristRouteService.removeById(id);

        return R.ok();
    }
}

