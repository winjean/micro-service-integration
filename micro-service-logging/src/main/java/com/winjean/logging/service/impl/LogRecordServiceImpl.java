package com.winjean.logging.service.impl;

import com.winjean.logging.annotation.RecordLog;
import com.winjean.logging.domain.LogRecord;
import com.winjean.logging.repository.LogRecordRepository;
import com.winjean.logging.service.LogRecordService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Optional;

//import com.winjean.holder.MySecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogRecordServiceImpl implements LogRecordService {

    @Autowired
    private LogRecordRepository logRecordRepository;

    @Override
    public void delete(int days) {
        logRecordRepository.deleteByCreateDate(days);
    }

    @Override
    public LogRecord findById(Long id) {
        Optional<LogRecord> optional = logRecordRepository.findById(id);
        Assert.isTrue(optional.isPresent(), "日志信息不存在");
        return optional.get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProceedingJoinPoint joinPoint, LogRecord logRecord){

        // 获取request
//        HttpServletRequest request = MyRequestContextHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RecordLog recordLog = method.getAnnotation(RecordLog.class);

        // 描述
        if (logRecord != null) {
            logRecord.setDescription(recordLog.value());
        }

        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        // 用户名
        String username = "匿名用户";

        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }

        // 获取IP地址
//        logRecord.setRequestIp(StringUtils.getIP(request));
//
//        if(!LOGINPATH.equals(signature.getName()) && !recordLog.anonymity()){
//            UserDetails userDetails = MySecurityContextHolder.getUserDetails();
//            username = userDetails.getUsername();
//        } else {
//            try {
//                if(argValues.length >0 ){
////                    JSONObject jsonObject = new JSONObject(argValues[0]);
////                    username = jsonObject.getStr("username", username);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        logRecord.setMethod(methodName);
        logRecord.setUsername(username);
        logRecord.setParams(params + " }");
        logRecordRepository.save(logRecord);
    }
}
