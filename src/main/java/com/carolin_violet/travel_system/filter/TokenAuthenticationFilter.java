package com.carolin_violet.travel_system.filter;

import com.carolin_violet.travel_system.security.TokenManager;
import com.carolin_violet.travel_system.utils.R;
import com.carolin_violet.travel_system.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName JwtVerifyFilter
 * @Description 权限分配
 * @Author zj
 * @Date 2022/5/24 12:38
 * @Version 1.0
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // token置于header里
        String token = request.getHeader("token");
        String userName = tokenManager.getUserFromToken(token);
        List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(userName);
        System.out.println("77777777777777777777777777777777777777777777");
        System.out.println(permissionValueList.toString());

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for(String permissionValue : permissionValueList) {
            if(StringUtils.isEmpty(permissionValue)) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        System.out.println("88888888888888888888888888888888888888888888");
        System.out.println(authorities.toString());
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userName, token, authorities);
        SecurityContextHolder.getContext().setAuthentication(authResult);

        chain.doFilter(request, response);
    }

}
