package com.winjean.aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winjean.enums.DateTimeFormat;
import com.winjean.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LogAspect {
    // ..表示包及子包 该方法代表controller层的所有方法
    @Pointcut("execution(public * com.winjean.controller..*.*(..))")
    public void controllerMethod() {
    }

    @AfterThrowing(pointcut = "controllerMethod()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
    }

    @Around("controllerMethod()")
    public Object logRestVOInfo(ProceedingJoinPoint invocation) throws Throwable {
        Date dateBegin = new Date();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer requestInfo = new StringBuffer();
        requestInfo.append("请求信息：")
                .append(DateUtils.getDateTime(dateBegin, DateTimeFormat.dateTime11.getValue()))
                .append(" --> URL = {" + request.getRequestURI() + "}, ")
                .append("HTTP_METHOD = {" + request.getMethod() + "}, ")
                .append("IP = {" + request.getRemoteAddr() + "}, ")
                .append("CLASS_METHOD = {" + invocation.getSignature().getDeclaringTypeName() + "." + invocation.getSignature().getName() + "},\t");

        if(invocation.getArgs().length == 0) {
            requestInfo.append("ARGS = {} ");
        } else {
            requestInfo.append("ARGS = " + new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .writeValueAsString(invocation.getArgs()[0]));
        }

        log.info(requestInfo.toString());

        Object value = invocation.proceed();

        Date dateEnd = new Date();
        StringBuffer responseInfo = new StringBuffer();
        responseInfo.append("请求结果:")
            .append(DateUtils.getDateTime(dateEnd, DateTimeFormat.dateTime11.getValue()))
            .append(",cost : " + (dateEnd.getTime() - dateBegin.getTime()))
            .append(", return value:" + value);

        log.info(responseInfo.toString());

        return value;
    }
}
