package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDepartment;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.DepartmentRepository;
import com.winjean.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public EntityDepartment insert(EntityDepartment department) {
        department = departmentRepository.save(department);
        log.info("add department success.");

        return department;
    }

    @Override
    public EntityDepartment update(EntityDepartment department) {
        EntityDepartment _user = query(department.getId());
//        _user.setName(user.getName());
//        _user.setBirthday(user.getBirthday());
//        _user.setTelephone(user.getTelephone());
//        _user.setSex(user.getSex());
//        _user.setEmail(user.getEmail());
//        _user.setEmail(user.getEmail());
//        _user.setDepartment(user.getDepartment());
//        _user.setRoles(user.getRoles());

        _user = departmentRepository.save(_user);

        log.info("update department success.");
        return _user;
    }

    @Override
    public boolean delete(long id) {
        departmentRepository.deleteById(id);

        log.info("delete department success.");
        return true;
    }

    @Override
    public EntityDepartment query(long id) {
        Optional<EntityDepartment> user = departmentRepository.findById(id);
        log.info("query department success.");
        return user.get();
    }

    @Override
    public Page<EntityDepartment> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        EntityUser user = new EntityUser();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityDepartment> list = departmentRepository.findAll(example, pageable);

        log.info("query department list success.");
        return list;
    }
}
