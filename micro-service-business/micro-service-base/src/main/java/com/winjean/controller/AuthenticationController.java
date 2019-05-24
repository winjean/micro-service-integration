package com.winjean.controller;

import com.winjean.common.BaseResponse;
import com.winjean.model.request.AuthorizationUser;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

//    @Value("${jwt.header}")
//    private String tokenHeader;

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    /**
     * 登录授权
     * @param authorizationUser
     * @return
     */
    @PostMapping(value = "login")
    public BaseResponse login(@Validated @RequestBody AuthorizationUser authorizationUser){

        UserDetails user = userService.loadUserByUsername(authorizationUser.getUsername());

        if(!user.getPassword().equals(authorizationUser.getPassword())){
            throw new RuntimeException("密码错误");
        }

        if(!user.isEnabled()){
            throw new RuntimeException("账号已停用，请联系管理员");
        }

        if(!user.isAccountNonLocked()){
            throw new RuntimeException("账号锁定，请联系管理员");
        }

        // 生成令牌
//        final String token = jwtTokenUtil.generateToken(jwtUser);

        return BaseResponse.getSuccessResponse();
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
