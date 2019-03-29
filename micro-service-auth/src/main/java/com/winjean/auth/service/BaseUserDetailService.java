package com.winjean.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by fp295 on 2018/4/16.
 */
public abstract class BaseUserDetailService implements UserDetailsService {

//    @Autowired
//    protected BaseUserService baseUserService;
//    @Autowired
//    private BaseRoleService baseRoleService;
//    @Autowired
//    private BaseModuleResourceService baseModuleResourceService;
//    @Autowired
//    private RedisTemplate<String, BaseRole> redisTemplate;
//    @Autowired
//    private RedisTemplate<String, BaseModuleResources> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        BaseUser baseUser = getUser(var1);
//
//        // 调用FeignClient查询角色
//        ResponseData<List<BaseRole>> baseRoleListResponseData = baseRoleService.getRoleByUserId(baseUser.getId());
//        List<BaseRole> roles;
//        if(baseRoleListResponseData.getData() == null ||  !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getCode())){
//            logger.error("查询角色失败！");
//            roles = new ArrayList<>();
//        }else {
//            roles = baseRoleListResponseData.getData();
//        }
//
//        //调用FeignClient查询菜单
//        ResponseData<List<BaseModuleResources>> baseModuleResourceListResponseData = baseModuleResourceService.getMenusByUserId(baseUser.getId());
//
//        // 获取用户权限列表
//        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);
//
//        // 存储菜单到redis
//        if( ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode()) && baseModuleResourceListResponseData.getData() != null){
//            resourcesTemplate.delete(baseUser.getId() + "-menu");
//            baseModuleResourceListResponseData.getData().forEach(e -> {
//                resourcesTemplate.opsForList().leftPush(baseUser.getId() + "-menu", e);
//            });
//        }
//
//        // 返回带有用户权限信息的User
//        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(baseUser.getUserName(),
//                baseUser.getPassword(), isActive(baseUser.getActive()), true, true, true, authorities);
//        return new BaseUserDetail(baseUser, user);





        Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();

//        GrantedAuthorityImpl auth2=new GrantedAuthorityImpl("ROLE_ADMIN");
//        GrantedAuthorityImpl auth1=new GrantedAuthorityImpl("ROLE_USER");

        if(username.equals("test")){
            auths=new ArrayList<GrantedAuthority>();
//            auths.add(auth1);
//            auths.add(auth2);
        }

        User user = new User(username, "123456", true, true, true, true, auths);
        return user;
    }

//    protected abstract BaseUser getUser(String var1) ;
//
//    private boolean isActive(int active){
//        return active == 1 ? true : false;
//    }
//
//    private List<GrantedAuthority> convertToAuthorities(BaseUser baseUser, List<BaseRole> roles) {
//        List<GrantedAuthority> authorities = new ArrayList();
//        // 清除 Redis 中用户的角色
//        redisTemplate.delete(baseUser.getId());
//        roles.forEach(e -> {
//            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
//            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
//            authorities.add(authority);
//            //存储角色到redis
//            redisTemplate.opsForList().rightPush(baseUser.getId(), e);
//        });
//        return authorities;
//    }
}
