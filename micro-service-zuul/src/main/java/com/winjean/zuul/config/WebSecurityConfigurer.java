package com.winjean.zuul.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 19:41
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Configuration
//@EnableWebSecurity
@Order(99)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,"/role/**").permitAll()
            .antMatchers(HttpMethod.GET,"/role/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable();
    }
}
