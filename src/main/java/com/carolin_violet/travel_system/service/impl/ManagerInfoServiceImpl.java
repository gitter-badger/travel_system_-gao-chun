package com.carolin_violet.travel_system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carolin_violet.travel_system.bean.ManagerInfo;
import com.carolin_violet.travel_system.mapper.ManagerInfoMapper;
import com.carolin_violet.travel_system.service.ManagerInfoService;
import org.springframework.stereotype.Service;

/**
 * @ClassName ManagerInfoServiceImpl
 * @Description TODO
 * @Author zj
 * @Date 2022/5/22 9:01
 * @Version 1.0
 */

@Service
public class ManagerInfoServiceImpl extends ServiceImpl<ManagerInfoMapper, ManagerInfo> implements ManagerInfoService {
}
