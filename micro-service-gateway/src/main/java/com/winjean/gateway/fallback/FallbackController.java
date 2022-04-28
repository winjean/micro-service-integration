//package com.winjean.gateway.fallback;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
///**
// * 响应超时熔断处理器
// *
// * @author xull
// * @date 2020-10-12
// */
//@RestController
//public class FallbackController {
//
//    @RequestMapping("/fallback")
//    public Mono fallback() {
//        return Mono.just("");
//    }
//}