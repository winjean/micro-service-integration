//package com.winjean.gateway.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Order(-1)
//@Component
//@RequiredArgsConstructor
//public class GlobalExceptionConfigration implements ErrorWebExceptionHandler {
//
//    @Override
//    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
//        return null;
//    }
//}
