//package com.winjean.role.service.feignClient;
//
//import com.winjean.role.service.fallback.UserFallback;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
///**
// * 类描述：
// * 创建人：winjean
// * 创建时间：2018/9/21 15:43
// *
// * @version V 1.0
// */
//@FeignClient(value = "user-service",fallback = UserFallback.class)
//public interface UserFeignClient {
//
//    @GetMapping(value = "/user-service-api/getUserNameById")
//    String getUserName();
//}
