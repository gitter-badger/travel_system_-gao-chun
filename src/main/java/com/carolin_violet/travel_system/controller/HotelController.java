package com.carolin_violet.travel_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carolin_violet.travel_system.bean.Hotel;
import com.carolin_violet.travel_system.bean.conditionQuery.HotelQuery;
import com.carolin_violet.travel_system.service.HotelService;
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
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // 查找所有旅馆
    @GetMapping("findAll")
    public R findAllHotel() {
        List<Hotel> list = hotelService.list(null);
        return R.ok().data("items", list);
    }

    // 添加旅馆
    @PostMapping("addHotel")
    public R addHotel(@RequestBody Hotel hotel) {
        boolean save = hotelService.save(hotel);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 修改旅馆信息
    @PutMapping("updateHotel")
    public R updateHotel(@RequestBody Hotel hotel) {
        boolean flag = hotelService.updateById(hotel);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 逻辑删除旅馆
    @DeleteMapping("{id}")
    public R removeHotel(@PathVariable String id) {
        boolean flag = hotelService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 根据展示优先级进行条件查询
    @PostMapping("condition")
    public R findCondition(@RequestBody HotelQuery hotelQuery) {
        QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
        wrapper.eq("popular", hotelQuery.getPopular());
        List<Hotel> hotels = hotelService.list(wrapper);
        return R.ok().data("items", hotels);
    }
}

