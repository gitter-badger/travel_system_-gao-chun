package com.carolin_violet.travel_system.security.handler;

import com.carolin_violet.travel_system.security.TokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 登出
 */
@Component
@Slf4j
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenManager tokenJwtManager;

//    public MyLogoutHandler(TokenJwtManager tokenJwtManager) {
//        this.tokenJwtManager = tokenJwtManager;
//    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        // 1、从header中获取token
        String token = httpServletRequest.getHeader("token");
        log.info("token信息为  {}",token);
        String userName = tokenJwtManager.getUserFromToken(token);
        log.info("从token获取userName信息为  {}",token);

        // redis 移除登录信息等逻辑
        // xxxxx

        // 2、返回请求端
        Map<String,Object> resultMap = new HashMap<>();
        // 保存数据
        resultMap.put("code","200");
        resultMap.put("msg",userName+"登出成功");
        resultMap.put("data",new HashMap<>());

        // 设置返回消息类型
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        // 返回给请求端
        PrintWriter writer = null;
        try {
            writer = httpServletResponse.getWriter();
            writer.write(resultMap.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
           e.printStackTrace();
        }

    }
}
