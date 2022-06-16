//package com.winjean.auth.feignclient;
//
//import com.alibaba.fastjson.JSONObject;
//import com.winjean.auth.fallback.AuthFallbackFactory;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.servlet.http.HttpServletRequest;
//
//@FeignClient(value = "micro-service-auth",fallbackFactory = AuthFallbackFactory.class)
//public interface AuthFeignClient {
//
//    @PostMapping("oauth/token")
//    //http://localhost:8401/micro-service-auth/oauth/token?client_id=zuul&client_secret=123456&grant_type=authorization_code&redirect_uri=http://localhost:8401/micro-service-auth/api/test&code=86j14k
//    JSONObject getAuthorizationCode(@RequestBody JSONObject json);
//
//    @PostMapping("oauth/token?client_id=zuul&client_secret=123456&grant_type=authorization_code&redirect_uri=http://localhost:8401/micro-service-auth/api/test&code=86j14k")
//    JSONObject getAuthorizationCode(HttpServletRequest request);
//}
