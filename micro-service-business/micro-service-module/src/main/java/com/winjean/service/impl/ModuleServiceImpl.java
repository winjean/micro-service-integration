package com.winjean.service.impl;

import com.winjean.model.entity.ModuleEntity;
import com.winjean.repository.ModuleRepository;
import com.winjean.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public void save(ModuleEntity entity) {
        ModuleEntity module = moduleRepository.save(entity);
        log.info("module saved id = {}",module.getId());
    }

    @Override
    public void delete(int id) {
        moduleRepository.deleteById(id);
        log.info("module deleted id = {}",id);
    }

    @Override
    public void update(ModuleEntity entity) {
        int id = entity.getId();
        ModuleEntity module = moduleRepository.findById(id).get();
        module.setName(entity.getName());
        log.info("module updated id = {},name = {}", id, entity.getName());
    }

    @Override
    public ModuleEntity findById(int id) {
        return moduleRepository.findById(id).get();
    }

    @Override
    public ModuleEntity findByName(String name) {
        return moduleRepository.findModuleByName(name);
    }

    @Override
    public List<ModuleEntity> findAll() {
        return moduleRepository.findAll();
    }
}
