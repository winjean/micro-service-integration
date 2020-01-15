package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Permission;
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
    public Permission save(Permission permission) {
        permission = permisssionRepository.save(permission);
        log.info("add permission success.");

        return permission;
    }

    @Override
    public Permission update(Permission permission) {
        Permission _permission = query(permission.getId());
        _permission.setName(permission.getName());
        _permission.setPid(permission.getPid());
        _permission.setNickname(permission.getNickname());
        _permission.setRemark(permission.getRemark());
        _permission.setRoles(permission.getRoles());
        _permission.setStatus(permission.isStatus());
        _permission.setUpdateUser("update_user");

        _permission = permisssionRepository.save(_permission);

        log.info("update permission success.");
        return _permission;
    }

    @Override
    public boolean delete(long id) {
        permisssionRepository.deleteById(id);

        log.info("delete permission success.");
        return true;
    }

    @Override
    public Permission query(long id) {
        Optional<Permission> permission = permisssionRepository.findById(id);
        log.info("query permission success.");
        return permission.get();
    }

    @Override
    public Page<Permission> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        Permission permission = new Permission();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(permission, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Permission> list = permisssionRepository.findAll(example, pageable);

        log.info("query permission list success.");
        return list;
    }
}
