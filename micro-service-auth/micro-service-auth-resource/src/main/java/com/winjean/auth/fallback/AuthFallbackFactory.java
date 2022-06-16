package com.winjean.auth.fallback;

import com.alibaba.fastjson.JSONObject;
import com.winjean.auth.feignclient.AuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author winjean
 */

@Component
@Slf4j
public class AuthFallbackFactory implements FallbackFactory<AuthFeignClient> {

    @Override
    public AuthFeignClient create(Throwable cause) {

        log.error("客户端出错:" + cause.getMessage()==null ? "" : cause.getMessage());

        cause.printStackTrace();

        return new AuthFeignClient() {
            @Override
            public JSONObject getAuthorizationCode(JSONObject json) {
                log.info("invoke error, query user list request json : {} ",json);
                return null;
            }

            @Override
            public JSONObject getAuthorizationCode(HttpServletRequest request) {
                log.info("invoke error, query user list request json : {} ",request);
                return null;
            }
        };
    }
}
