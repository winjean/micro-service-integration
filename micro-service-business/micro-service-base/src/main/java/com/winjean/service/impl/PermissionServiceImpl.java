package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityPermission;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.PermisssionRepository;
import com.winjean.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermisssionRepository permisssionRepository;

    @Override
    public EntityPermission insert(EntityPermission user) {
        user = permisssionRepository.save(user);
        log.info("add user success.");

        return user;
    }

    @Override
    public EntityPermission update(EntityPermission user) {
        EntityPermission _user = query(user.getId());
//        _user.setName(user.getName());
//        _user.setBirthday(user.getBirthday());
//        _user.setTelephone(user.getTelephone());
//        _user.setSex(user.getSex());
//        _user.setEmail(user.getEmail());
//        _user.setEmail(user.getEmail());
//        _user.setDepartment(user.getDepartment());
//        _user.setRoles(user.getRoles());

        _user = permisssionRepository.save(_user);

        log.info("update user success.");
        return _user;
    }

    @Override
    public boolean delete(long id) {
        permisssionRepository.deleteById(id);

        log.info("delete user success.");
        return true;
    }

    @Override
    public EntityPermission query(long id) {
        Optional<EntityPermission> user = permisssionRepository.findById(id);
        log.info("query user success.");
        return user.get();
    }

    @Override
    public Page<EntityPermission> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        EntityUser user = new EntityUser();
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("status")
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityPermission> list = permisssionRepository.findAll(example, pageable);

        log.info("query user list success.");
        return list;
    }
}
