package com.carolin_violet.travel_system.config;

import com.carolin_violet.travel_system.filter.CodeValidateFilter;
import com.carolin_violet.travel_system.filter.JwtLoginFilter;
import com.carolin_violet.travel_system.filter.TokenAuthenticationFilter;
import com.carolin_violet.travel_system.security.DefaultPasswordEncoder;
import com.carolin_violet.travel_system.security.TokenManager;
import com.carolin_violet.travel_system.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * <p>
 * Security配置类
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 未登录handler
    @Autowired
    private MyUnAuthEntryPoint myUnAuthEntryPoint;

    // 无权限
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    //  登出handler处理
    @Autowired
    private MyLogoutHandler myLogoutHandler;

    // 登录失败
    @Autowired
    private LoginFailedHandler loginFailedHandler;

    // 登录成功
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    private UserDetailsService userDetailsService;
    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(myUnAuthEntryPoint) // 未登录 handler
                .accessDeniedHandler(myAccessDeniedHandler) // 无权限


                .and().cors().configurationSource(corsConfigurationSource())   // 解决跨域问题
                .and().csrf().disable() // 关闭 csrf 跨域请求
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/travel_system/oss/picture").permitAll()   // 由于上传图片时报跨域异常所以加上这个配置
                .antMatchers(HttpMethod.POST, "/travel_system/oss/picture/addPhoto/{id}").permitAll()
                .anyRequest().authenticated()

                .and()
                .logout() // logout设定
                .logoutUrl("/travel_system/logout")  //退出请求  /logouts 未定义，交给自定义handler实现功能
                .addLogoutHandler(myLogoutHandler) // 登出 myLogoutHandler 处理

                .and()

                // 前后代码略
                // 添加短信验证码过滤器链
//                .addFilterBefore(new CodeValidateFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtLoginFilter(authenticationManager(), tokenManager, redisTemplate)) // 认证交给 自定义 TokenLoginFilter 实现
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),tokenManager, redisTemplate))
                // basic 方式
                .httpBasic();
    }

    CorsConfigurationSource corsConfigurationSource() {
        // 提供CorsConfiguration实例，并配置跨域信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*", "http://localhost:9528"));   // *或者http://localhost:9528(vuecli的端口)
        corsConfiguration.setMaxAge(360000L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    /**
     * 密码处理
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/travel_system/msm/send/**",   // 忽略短信上传接口
                "/travel_system/feedback/addFeedback",   // 忽略反馈上传接口
                "/travel_system/travel-note/addNote",    // 忽略游记上传接口
                "/swagger-resources/**",
                "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        );
    }
}