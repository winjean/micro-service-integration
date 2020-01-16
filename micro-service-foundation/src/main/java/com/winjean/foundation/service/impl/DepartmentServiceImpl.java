package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Department;
import com.winjean.foundation.repository.DepartmentRepository;
import com.winjean.foundation.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        department = departmentRepository.save(department);
        log.info("add department success.");

        return department;
    }

    @Override
    public Department update(Department department) {
        Assert.isTrue(department.getId() != department.getPid(),"上级部门和本部门不能为同一个");

        Department _department = query(department.getId());
        _department.setName(department.getName());
        _department.setPid(department.getPid());
        _department.setRoles(department.getRoles());
        _department.setStatus(department.isStatus());
        _department.setUpdateUser("update_user");

        _department = departmentRepository.save(_department);

        log.info("update department success.");
        return _department;
    }

    @Override
    public boolean delete(long id) {
        departmentRepository.deleteById(id);

        log.info("delete department success.");
        return true;
    }

    @Override
    public Department query(long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        Assert.isTrue(optional.isPresent(), "部门信息不存在");
        log.info("query department success.");
        return optional.get();
    }

    @Override
    public Page<Department> list(JSONObject json, Pageable pageable) {

        Page<Department> page = departmentRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(null != json.getString("name")){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%" + json.getString("name") + "%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query department list success.");
        return page;
    }
}
