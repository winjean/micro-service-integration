//package com.winjean.admin.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///**
// * @author ：winjean
// * @date ：Created in 2019/3/26 19:20
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//
//@EnableWebSecurity(debug = true)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("demoUser1").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("demoUser2").password("123456").authorities("USER").build());
//        return manager;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
////                .passwordEncoder(new BCryptPasswordEncoder())
////                .withUser("guest").password(new BCryptPasswordEncoder().encode("guest")).authorities("WRIGTH_READ")
////                .and()
//                .passwordEncoder(passwordEncoder())
//                .withUser("admin").password(passwordEncoder().encode("admin")).authorities("WRIGTH_READ", "WRIGTH_WRITE");
//
//    }
//
//    @Bean
//    public static BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http
//                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**","/test")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**")
//                .authenticated()
//                .and()
//                .formLogin().permitAll();
//
//        http.rememberMe().rememberMeCookieName("remember");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
//    }
//
//}
