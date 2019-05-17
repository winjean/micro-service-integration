package com.winjean.config;

import com.winjean.handler.MyAccessDeniedHandler;
import com.winjean.handler.MyAuthenticationFailureHandler;
import com.winjean.handler.MyAuthenticationSuccessHandler;
import com.winjean.handler.MyLogoutSuccessHandler;
import com.winjean.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    // Request层面的配置
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 禁用 CSRF
                .csrf().disable()

                // 授权异常
                .exceptionHandling().authenticationEntryPoint(null).and()

                //没有权限的自定义处理类
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()

                //指定前后端分离的时候调用后台登录接口的名称
                .formLogin().loginProcessingUrl("/auth/login")
                //登录成功的自定义处理类
                .successHandler(authenticationSuccessHandler)
                //登录失败的自定义处理类
                .failureHandler(authenticationFailureHandler)
                .and()

                //指定前后端分离的时候调用后台注销接口的名称
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()

                // 不创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 过滤请求
                .authorizeRequests()
                .antMatchers("/oauth/**", "/logout/**").permitAll()

                // 系统监控
                .antMatchers("/actuator/**").anonymous()

                // swagger start
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
                // swagger end

                // 接口限流测试
                .antMatchers("/test/**").anonymous()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()

                .antMatchers("/druid/**").permitAll()

                // 所有请求都需要认证
                .anyRequest().authenticated()

                // 防止iframe 造成跨域
                .and().headers().frameOptions().disable()

//                .and().addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                ;
    }
    @Override
    // Web层面的配置
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Override
    // 身份验证配置
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoderBean());
    }

}
