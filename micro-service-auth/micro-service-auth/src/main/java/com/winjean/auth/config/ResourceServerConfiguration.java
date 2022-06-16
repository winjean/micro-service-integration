package com.winjean.auth.config;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/23 13:31
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

/**
 * @author winjean
 * @EnableResourceServer会给Spring Security的FilterChan添加一个OAuth2AuthenticationProcessingFilter，
 * OAuth2AuthenticationProcessingFilter会使用OAuth2AuthenticationManager来验证token。
 * OAuth2AuthenticationManager#authenticate(Authentication authentication)
 *
 */

@Configuration
@EnableResourceServer
@EnableOAuth2Client
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Resource
    private JwtTokenStore jwtTokenStore;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId("WRIGTH")
                .tokenStore(jwtTokenStore);
    }

//    @Bean
//    protected JwtAccessTokenConverter jwtTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("springcloud123");
//        return converter;
//    }

//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtTokenConverter());
//    }

}
