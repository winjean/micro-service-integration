package com.winjean.logging.controller;

import com.winjean.common.BaseResponse;
import com.winjean.logging.annotation.RecordLog;
import com.winjean.logging.domain.LogRecord;
import com.winjean.logging.service.LogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import com.winjean.holder.MySecurityContextHolder;
//import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("logging")
@Slf4j
public class LogRecordController {

    @Autowired
    private LogRecordService logRecordService;

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RecordLog("查询日志详细信息")
    public BaseResponse getLogs(@PathVariable Long id){
        return BaseResponse.getSuccessResponse(logRecordService.findById(id));
    }

    @PostMapping(value = "/logs/user")
    public BaseResponse getUserLogs(LogRecord log, Pageable pageable){
        log.setLogType("INFO");
//        log.setUsername(MySecurityContextHolder.getUserDetails().getUsername());
//        return BaseResponse.getSuccessResponse(logQueryService.queryAll(log,pageable));
        return null;
    }

    @GetMapping(value = "/logs/error")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public BaseResponse getErrorLogs(LogRecord log, Pageable pageable){
        log.setLogType("ERROR");
//        return BaseResponse.getSuccessResponse(logQueryService.queryAll(log,pageable));
        return null;
    }
}
