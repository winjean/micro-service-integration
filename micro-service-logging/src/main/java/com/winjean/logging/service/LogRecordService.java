package com.winjean.logging.service;

import com.winjean.logging.domain.LogRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

public interface LogRecordService {

    /**
     * 新增日志
     * @param joinPoint
     * @param log
     */
    @Async
    void save(ProceedingJoinPoint joinPoint, LogRecord log);

    /**
     * 删除days天前的日志
     * @param days
     */
    void delete(int days);

    LogRecord findById(Long id);
}
