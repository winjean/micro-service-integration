package com.winjean.auth.service;

import org.springframework.stereotype.Service;

@Service
public class PhoneUserDetailService extends BaseUserDetailService {

//    @Override
//    protected BaseUser getUser(String phone) {
//        // 手机验证码调用FeignClient根据电话号码查询用户
//        ResponseData<BaseUser> baseUserResponseData = baseUserService.getUserByPhone(phone);
//        if(baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())){
//            logger.error("找不到该用户，手机号码：" + phone);
//            throw new UsernameNotFoundException("找不到该用户，手机号码：" + phone);
//        }
//        return baseUserResponseData.getData();
//    }
}
