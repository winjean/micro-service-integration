package com.winjean.controller;

import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
//@RequestMapping("auth")
public class AuthenticationController {

//    @Value("${jwt.header}")
//    private String tokenHeader;

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @RequestMapping("/")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登陆用户：" + name);

        return "home.html";
    }

    /**
     * 登录授权
//     * @param authorizationUser
     * @return
     */
    @RequestMapping(value = "/auth/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(/*@Validated @RequestBody AuthorizationUser authorizationUser*/){

//        UserDetails user = userService.loadUserByUsername(authorizationUser.getUsername());
//
//        if(!user.getPassword().equals(authorizationUser.getPassword())){
//            throw new RuntimeException("密码错误");
//        }
//
//        if(!user.isEnabled()){
//            throw new RuntimeException("账号已停用，请联系管理员");
//        }
//
//        if(!user.isAccountNonLocked()){
//            throw new RuntimeException("账号锁定，请联系管理员");
//        }

//        Authentication authentication = new UsernamePasswordAuthenticationToken(authorizationUser.getUsername(),authorizationUser.getPassword());
//
//        Authentication result = authenticationManager.authenticate(authentication);
//
//        SecurityContextHolder.getContext().setAuthentication(result);
        // 生成令牌
//        final String token = jwtTokenUtil.generateToken(jwtUser);

//        return BaseResponse.getSuccessResponse();
        return "login.html";
    }

    @RequestMapping(value = "/auth/logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(){

        SecurityContextHolder.clearContext();

        return "";
    }


    /**
     * 获取用户信息
     * @return
     */
//    @GetMapping(value = "${jwt.auth.account}")
//    public ResponseEntity getUserInfo(){
//        UserDetails userDetails = SecurityContextHolder.getUserDetails();
//        JwtUser jwtUser = (JwtUser)userDetailsService.loadUserByUsername(userDetails.getUsername());
//        return ResponseEntity.ok(jwtUser);
//    }
}
