package com.winjean.config;

import com.winjean.handler.*;
import com.winjean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 19:20
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

//    @Autowired
//    private LoginAuthenticationFilter loginAuthenticationFilter;

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Web层面的配置
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Request层面的配置
        httpSecurity
                // 禁用 CSRF TODO 到底是什么鬼
                .csrf().disable()
//                .anonymous().disable()
//                .cors().and()
//                .httpBasic().and()
                // 授权异常
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()

                //没有权限的自定义处理类
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()

                //指定前后端分离的时候调用后台登录接口的名称
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/")
                .defaultSuccessUrl("/")
                .failureUrl("/login/error")
                .permitAll()
                //登录成功的自定义处理类
//                .successHandler(authenticationSuccessHandler)
                //登录失败的自定义处理类
//                .failureHandler(authenticationFailureHandler)

                .and()

                //指定前后端分离的时候调用后台注销接口的名称
                .logout()//.logoutUrl("/logout")
//                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()

                // 不创建会话
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 过滤请求
                .authorizeRequests()
//                .antMatchers("/oauth/**", "/logout/**").permitAll()

                // 系统监控
//                .antMatchers("/actuator/**").anonymous()

                // swagger start
//                .antMatchers("/swagger-ui.html").anonymous()
//                .antMatchers("/swagger-resources/**").anonymous()
//                .antMatchers("/webjars/**").anonymous()
//                .antMatchers("/*/api-docs").anonymous()
                // swagger end

                // 接口限流测试
//                .antMatchers("/test/**").anonymous()
//                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
//
//                .antMatchers("/druid/**").permitAll()

                // 所有请求都需要认证
                .anyRequest().authenticated()

                // 防止iframe 造成跨域
//                .and().headers().frameOptions().disable()

//                .and().addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .and().rememberMe()
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
//        return new BCryptPasswordEncoder();

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 身份验证配置
        auth.userDetailsService(userService).passwordEncoder(passwordEncoderBean());
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userService)
//                .passwordEncoder(passwordEncoderBean());
//    }
}
