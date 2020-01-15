package com.winjean.logging.aspect;

import com.winjean.logging.domain.LogRecord;
import com.winjean.logging.service.LogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogRecordAspect {

    @Autowired
    private LogRecordService logRecordService;

    private long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.winjean.annotation.RecordLog)")
    public void logPointcut() {
        // 该方法无方法体,主要为了让同类中其他方法使用此切入点
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint){
        currentTime = System.currentTimeMillis();
        try {
            Object object = joinPoint.proceed();
            LogRecord log = new LogRecord(LogRecord.LogRecordType.INFO.getValue(),System.currentTimeMillis() - currentTime);
            logRecordService.save(joinPoint, log);
            return object;
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 配置异常通知
     *
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LogRecord logRecord = new LogRecord(LogRecord.LogRecordType.ERROR.getValue(),System.currentTimeMillis() - currentTime);
        logRecord.setExceptionDetail(e.getMessage());
        logRecordService.save((ProceedingJoinPoint)joinPoint, logRecord);
    }
}
