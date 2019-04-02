package com.winjean.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 19:20
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
                .and()
                .formLogin().permitAll();
    }


}
