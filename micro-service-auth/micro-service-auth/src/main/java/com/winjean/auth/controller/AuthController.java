package com.winjean.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.auth.feignclient.AuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class AuthController {

    @Resource
    private AuthFeignClient authFeignClient;

    @GetMapping("/")
    public String test() {
        return "authorize";
    }

    @GetMapping("/authorization/code")
    public JSONObject getAuthorizationCode(HttpServletRequest request , String code) {
        log.info(code);
        JSONObject json = new JSONObject();

        String url = "http://localhost:8401/micro-service-auth/oauth/token";
        String client_id = "zuul";
        String client_secret = "123456";
        String grant_type = "authorization_code";
        String redirect_uri = "http://localhost:8401/micro-service-auth/authorization/code";

        json.fluentPut("client_id", client_id)
                .fluentPut("client_secret", client_secret)
                .fluentPut("grant_type", grant_type)
                .fluentPut("redirect_uri", redirect_uri)
                .put("code",code);



//        return authFeignClient.getAuthorizationCode(json);

        request.setAttribute("client_id",client_id);
        request.setAttribute("code",code);
        request.setAttribute("client_secret",client_secret);
        request.setAttribute("grant_type",grant_type);
        request.setAttribute("redirect_uri",redirect_uri);
        return authFeignClient.getAuthorizationCode(request);
//        return json;
    }

    @GetMapping("/api/test")
    public String testa() {
        return "test";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/backReferer")
    public void sendBack(HttpServletRequest request, HttpServletResponse response) {

        try {
            String referer = request.getHeader("referer");
            if (referer != null) {
                int index = referer.indexOf("?");
                if(index != -1)
                    referer = referer.substring(0, index);
                response.sendRedirect(referer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        return new ModelAndView("bbbb-authorize", model);
    }*/

    /*@RequestMapping("/")
    public ModelAndView indexPage(Map<String, Object> model) {
        // 获取用户名
//        String userName = ((UserDetails) SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getPrincipal())
//                .getUsername();
        model.put("userName", "test");
        // 获取全部客户端应用
//        ResponseData responseData = baseClientService.getAllClient();
//        if(ResponseCode.SUCCESS.getCode().equals(responseData.getCode()) && responseData.getData() != null) {
//            model.put("client",responseData.getData());
//        } else {
            model.put("client",new ArrayList<>());
//        }
        return new ModelAndView("bbbb-index", model);
    }*/

}
