package com.winjean.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：winjean
 * @date ：Created in 2019/5/17 16:43
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof UserDetails) {
            UserDetails user = (UserDetails) principal;
            log.info("loginUser:"+user.getUsername());
        }

    }
}
