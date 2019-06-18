package com.winjean.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：winjean
 * @date ：Created in 2019/5/17 16:44
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        //username and password not correct
        log.info("authentication failure handler: {}", exception.getMessage(), exception);

//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
