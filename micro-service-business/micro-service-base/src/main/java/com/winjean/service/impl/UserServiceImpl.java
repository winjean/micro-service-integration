package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.UserRepository;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public EntityUser insert(EntityUser user) {
        user = userRepository.save(user);
        log.info("add user success.");

        return user;
    }

    @Override
    public EntityUser update(EntityUser user) {
        user = userRepository.save(user);

        log.info("update user success.");
        return user;
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
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("status", match -> match.equals(true));
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityUser> list = userRepository.findAll(example, pageable);

        log.info("query user list success.");
        return list;
    }
}
