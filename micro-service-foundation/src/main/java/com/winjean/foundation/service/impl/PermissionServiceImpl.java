package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Permission;
import com.winjean.foundation.repository.PermisssionRepository;
import com.winjean.foundation.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
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
    public Page<Permission> list(JSONObject json, Pageable pageable) {
        Page<Permission> page = permisssionRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(null != json.getString("name")){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + json.getString("name") + "%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query permission list success.");
        return page;
    }
}
