package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityRole;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.RoleRepository;
import com.winjean.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public EntityRole insert(EntityRole user) {
        user = roleRepository.save(user);
        log.info("add user success.");

        return user;
    }

    @Override
    public EntityRole update(EntityRole user) {
        EntityRole _user = query(user.getId());
//        _user.setName(user.getName());
//        _user.setBirthday(user.getBirthday());
//        _user.setTelephone(user.getTelephone());
//        _user.setSex(user.getSex());
//        _user.setEmail(user.getEmail());
//        _user.setEmail(user.getEmail());
//        _user.setDepartment(user.getDepartment());
//        _user.setRoles(user.getRoles());

        _user = roleRepository.save(_user);

        log.info("update user success.");
        return _user;
    }

    @Override
    public boolean delete(long id) {
        roleRepository.deleteById(id);

        log.info("delete user success.");
        return true;
    }

    @Override
    public EntityRole query(long id) {
        Optional<EntityRole> user = roleRepository.findById(id);
        log.info("query user success.");
        return user.get();
    }

    @Override
    public Page<EntityRole> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        EntityUser user = new EntityUser();
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("status")
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityRole> list = roleRepository.findAll(example, pageable);

        log.info("query user list success.");
        return list;
    }
}
