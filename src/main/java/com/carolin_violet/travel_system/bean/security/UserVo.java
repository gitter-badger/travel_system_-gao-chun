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
public class UserVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

//	// 短信验证码
//	private String code;
//
//	private String salt;
//
//	private String token;

}



