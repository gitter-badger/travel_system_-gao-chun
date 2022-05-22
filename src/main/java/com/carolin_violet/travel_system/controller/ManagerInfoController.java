package com.carolin_violet.travel_system.controller;

import com.carolin_violet.travel_system.bean.ManagerInfo;
import com.carolin_violet.travel_system.service.ManagerInfoService;
import com.carolin_violet.travel_system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ManagerInfoController {
    @Autowired
    private ManagerInfoService managerInfoService;

    @GetMapping("findAll")
    public R findAllManager() {
        List<ManagerInfo> list = managerInfoService.list(null);
        return R.ok().data("items", list);
    }

}
