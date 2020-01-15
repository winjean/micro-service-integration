package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.User;
import com.winjean.foundation.repository.UserRepository;
import com.winjean.foundation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        user = userRepository.save(user);
        log.info("add user success.");

        return user;
    }

    @Override
    public User update(User user) {
        User _user = query(user.getId());
        _user.setName(user.getName());
        _user.setBirthday(user.getBirthday());
        _user.setTelephone(user.getTelephone());
        _user.setSex(user.getSex());
        _user.setEmail(user.getEmail());
        _user.setEmail(user.getEmail());
        _user.setDepartment(user.getDepartment());
        _user.setRoles(user.getRoles());
        _user.setStatus(user.isStatus());
        _user.setUpdateUser("update_user");

        _user = userRepository.save(_user);

        log.info("update user success.");
        return _user;
    }

    @Override
    public boolean delete(long id) {
        userRepository.deleteById(id);

        log.info("delete user success.");
        return true;
    }

    @Override
    public User query(long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("query user success.");
        return user.get();
    }

    @Override
    public Page<User> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

//        User user = new User();
//        ExampleMatcher matcher = ExampleMatcher.matching()
////                .withIgnorePaths("status")
//                .withMatcher("status", match -> match.exact());
//        Example example = Example.of(user, matcher);
//
//        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
//        Page<User> list = userRepository.findAll(example, pageable);
//
//        log.info("query user list success.");
//        return list;
        return null;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username) {
        User entityUser = userRepository.findByName(username);

        if(null == entityUser){
            entityUser = userRepository.findByEmail(username);
        }

        if(null == entityUser){
            entityUser = userRepository.findBytelephone(username);
        }

        if (entityUser == null) {
            throw new UsernameNotFoundException(username);
        }

        org.springframework.security.core.userdetails.User user = transverter(entityUser);

        return user;
    }

    private org.springframework.security.core.userdetails.User transverter(User entityUser){

//        Set<Role> roles = entityUser.getRoles();
//        Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//
//        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(entityUser.getName(), entityUser.getPassword(),
//                entityUser.isStatus(),      //是否可用
//                entityUser.isExpired(),     //是否过期
//                true,   //证书不过期为true
//                entityUser.isLocked(),      //账户未锁定为true
//                authorities);
//        return user;
        return null;
    }
}
