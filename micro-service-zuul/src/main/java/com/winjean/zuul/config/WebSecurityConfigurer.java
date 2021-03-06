package com.winjean.zuul.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 19:41
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER -3)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/oauth/**").permitAll()
            .antMatchers("/micro-service-auth/**").permitAll()
            .antMatchers("/**").authenticated()
            .and()
            .httpBasic();
    }
}
