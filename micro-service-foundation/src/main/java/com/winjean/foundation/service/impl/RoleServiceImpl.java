package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.domain.Role;
import com.winjean.foundation.repository.RoleRepository;
import com.winjean.foundation.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        role = roleRepository.save(role);
        log.info("add role success.");

        return role;
    }

    @Override
    public Role update(Role role) {
        Role _role = query(role.getId());
        _role.setName(role.getName());
        _role.setDataScope(role.getDataScope());
        _role.setBuiltIn(role.isBuiltIn());
        _role.setRemark(role.getRemark());
        _role.setUsers(role.getUsers());
        _role.setPermissions(role.getPermissions());
        _role.setMenus(role.getMenus());
        _role.setDepartments(role.getDepartments());
        _role.setStatus(role.isStatus());
        _role.setUpdateUser("update_user");
        _role = roleRepository.save(_role);

        log.info("update role success.");
        return _role;
    }

    @Override
    public boolean delete(long id) {
        roleRepository.deleteById(id);

        log.info("delete role success.");
        return true;
    }

    @Override
    public Role query(long id) {
        Optional<Role> role = roleRepository.findById(id);
        log.info("query role success.");
        return role.get();
    }

    @Override
    public Page<Role> list(JSONObject json, Pageable pageable) {
        Page<Role> page = roleRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(!ObjectUtils.isEmpty(json.getString("name"))){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+json.getString("name")+"%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query role list success.");
        return page;
    }
}
