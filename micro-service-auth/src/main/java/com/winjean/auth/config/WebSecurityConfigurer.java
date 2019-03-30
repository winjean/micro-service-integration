package com.winjean.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/26 19:20
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private PhoneUserDetailService phoneUserDetailService;
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("demoUser1").password("123456").authorities("USER").build());
        manager.createUser(User.withUsername("demoUser2").password("123456").authorities("USER").build());
        return manager;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("guest").password(new BCryptPasswordEncoder().encode("guest")).authorities("WRIGTH_READ")
//                .and()
                .passwordEncoder(passwordEncoder())
                .withUser("admin").password(passwordEncoder().encode("admin")).authorities("WRIGTH_READ", "WRIGTH_WRITE");

//        auth.authenticationProvider(phoneAuthenticationProvider());
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
//                .addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/login.jsp").permitAll()
//                .anyRequest().hasRole("USER")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/login.jsp?authorization_error=true")
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
//                .and()
                .requestMatchers().antMatchers("/oauth/**", "/login/**", "/logout/**","/test")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
//                .authenticated();
                .and()
                .formLogin().permitAll()
                /*.loginPage("/login")
                .defaultSuccessUrl("/confirm").and()
                .logout().deleteCookies(appCookieName)*/;
//                .authorizeRequests()
//                .antMatchers("/oauth/**").permitAll()
//                .anyRequest().authenticated()
////                .and().cors()
//                .and().csrf().disable();

//        http.rememberMe().rememberMeCookieName("remember");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
//    }
//    @Bean
//    public LoginAuthenticationFilter loginAuthenticationFilter(){
//        LoginAuthenticationFilter provider = new LoginAuthenticationFilter();
//        // 设置userDetailsService
////        provider.setUserDetailsService(qrUserDetailService);
//        // 禁止隐藏用户未找到异常
////        provider.setHideUserNotFoundExceptions(false);
//        return provider;
//    }

//    @Bean
//    public PhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter() {
//        PhoneLoginAuthenticationFilter filter = new PhoneLoginAuthenticationFilter();
//        try {
//            filter.setAuthenticationManager(this.authenticationManagerBean());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
//        return filter;
//    }
//
//    @Bean
//    public PhoneAuthenticationProvider phoneAuthenticationProvider(){
//        PhoneAuthenticationProvider provider = new PhoneAuthenticationProvider();
//        // 设置userDetailsService
//        provider.setUserDetailsService(phoneUserDetailService);
//        // 禁止隐藏用户未找到异常
//        provider.setHideUserNotFoundExceptions(false);
//        return provider;
//    }
}
