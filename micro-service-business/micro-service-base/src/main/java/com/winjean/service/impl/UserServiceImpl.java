package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityRole;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.UserRepository;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public EntityUser save(EntityUser user) {
        user = userRepository.save(user);
        log.info("add user success.");

        return user;
    }

    @Override
    public EntityUser update(EntityUser user) {
        EntityUser _user = query(user.getId());
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
    public EntityUser query(long id) {
        Optional<EntityUser> user = userRepository.findById(id);
        log.info("query user success.");
        return user.get();
    }

    @Override
    public Page<EntityUser> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        EntityUser user = new EntityUser();
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("status")
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityUser> list = userRepository.findAll(example, pageable);

        log.info("query user list success.");
        return list;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User loadUserByUsername(String username) {
        EntityUser entityUser = userRepository.findByName(username);

        if(null == entityUser){
            entityUser = userRepository.findByEmail(username);
        }

        if(null == entityUser){
            entityUser = userRepository.findBytelephone(username);
        }

        if (entityUser == null) {
            throw new UsernameNotFoundException(username);
        }

        User user = transverter(entityUser);

        return user;
    }

    private User transverter(EntityUser entityUser){

        Set<EntityRole> roles = entityUser.getRoles();
        Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        User user = new User(entityUser.getName(), entityUser.getPassword(),
//                entityUser.isStatus(),      //是否可用
//                entityUser.isExpired(),     //是否过期
//                true,   //证书不过期为true
//                entityUser.isLocked(),      //账户未锁定为true
                authorities);
        return user;
    }
}
