package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Role;
import com.winjean.foundation.repository.RoleRepository;
import com.winjean.foundation.service.RoleService;
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
    public Page<Role> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        Role role = new Role();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(role, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Role> list = roleRepository.findAll(example, pageable);

        log.info("query role list success.");
        return list;
    }
}
