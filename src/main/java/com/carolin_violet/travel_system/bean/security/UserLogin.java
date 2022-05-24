package com.carolin_violet.travel_system.bean.security;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 */
@Data
public class UserLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;  // 手机号

	private String password;

//	// 短信验证码
//	private String code;

}



