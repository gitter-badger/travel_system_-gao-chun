package com.carolin_violet.travel_system.bean.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName LoginUser
 * @Description TODO
 * @Author zj
 * @Date 2022/5/24 17:04
 * @Version 1.0
 */

@Data
public class LoginUser implements Serializable {
    private String username;
    private String password;
}
