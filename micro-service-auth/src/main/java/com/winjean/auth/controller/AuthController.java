package com.winjean.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@Slf4j
public class AuthController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
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

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        return new ModelAndView("authorize", model);
    }

    @RequestMapping("/")
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
        return new ModelAndView("index", model);
    }

}
