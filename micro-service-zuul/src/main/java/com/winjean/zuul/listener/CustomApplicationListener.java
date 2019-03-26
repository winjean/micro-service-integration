package com.winjean.zuul.listener;

import com.winjean.zuul.service.ZuulService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CustomApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private ZuulService zuulService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("********* ContextRefreshedEvent executed ***********");
        zuulService.refreshRoute();
    }
}
