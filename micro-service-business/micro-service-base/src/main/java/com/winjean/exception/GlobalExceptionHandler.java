package com.winjean.exception;

import com.winjean.common.BaseResponse;
import com.winjean.common.exception.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.management.ReflectionException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;


@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse globalExceptionHandler(Exception e) throws Exception {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse(e.getMessage());
    }

    @ExceptionHandler(ReflectionException.class)
    public Object reflectionExceptionHandler(ReflectionException e) throws Exception {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("数据操作反射异常:" + e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse bindExceptionHandler(BindException e) {
        log.error(ExceptionUtil.getTrace(e));
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return BaseResponse.getFailureResponse("参数绑定失败="+message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error(ExceptionUtil.getTrace(e));

        MethodParameter parameter = e.getParameter();
        String method = parameter.getMethod().getName();

        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("方法:%S, 参数绑定失败: %s=%s", method, field, code);

        return BaseResponse.getFailureResponse(message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("缺少请求参数");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("参数解析失败:"+e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleServiceException(ConstraintViolationException e) {
        log.error(ExceptionUtil.getTrace(e));
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return BaseResponse.getFailureResponse("参数验证失败" + message);
    }

    @ExceptionHandler(ValidationException.class)
    public BaseResponse handleValidationException(ValidationException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("参数验证失败");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse noHandlerFoundException(NoHandlerFoundException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("Not Found="+e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("request_method_not_supported:"+e.getMethod());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error(ExceptionUtil.getTrace(e));
        return BaseResponse.getFailureResponse("content_type_not_supported");
    }

}
