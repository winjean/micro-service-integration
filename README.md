* micro-service-gateway 8101
* nacos 8201
* sentinel 8301
* micro-service-auth 8401
* micro-service-admin 8501
* micro-service-Zipkin 8601
* micro-service-turbine 8701
* micro-service-gateway 8801

* micro-service-foundation 7101 
基础服务：user、role、permission、menu、department、dictionary
* micro-service-kafka 7501


## micro-service-integration
* 使用版本  
    + Spring Cloud 2021.0.1
    + Spring Boot 2.6.3
    + spring cloud alibaba  2021.0.1.0
    
* 服务注册与发行  
使用nacos代替eureka

* 服务消费  
open feign

* 负载均衡
使用spring-cloud-starter-loadbalancer代替ribbon
    
* 断路器  
采用Sentinel  
Histrix已经进入维护期  
Sentinel直接使用用户线程进行限制，相比Hystrix的线程池隔离，减少了线程切换的开销。  
Sentinel的DashBoard提供了在线更改限流规则的配置，也更加的优化  

* 智能路由
gateway代替Zuul  
 
* 配置管理
使用nacos代替config

    
...待更新