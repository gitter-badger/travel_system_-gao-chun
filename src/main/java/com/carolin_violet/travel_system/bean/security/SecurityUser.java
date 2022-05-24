package com.carolin_violet.travel_system.bean.security;

import com.carolin_violet.travel_system.bean.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName SecurityUser
 * @Description TODO
 * @Author zj
 * @Date 2022/5/24 10:13
 * @Version 1.0
 */
@Data
public class SecurityUser implements UserDetails {


    private String username;  // 手机号
    private String password;
    private Integer status = 1;

    private List<String> permissionValueList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String permissionValue : permissionValueList) {
            if(StringUtils.isEmpty(permissionValue)) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //下面这四个方法是根据某些条件来判断用户是否可用，方便起见，可以直接设为true
    @Override
    public boolean isAccountNonExpired() { //指示用户的帐户是否已过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   //指示用户是否被锁定或解锁
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //指示用户的凭据（密码）是否已过期
        return true;
    }

    @Override
    public boolean isEnabled() {    //指示用户是否被启用或禁用
        return status==1;
    }
}
