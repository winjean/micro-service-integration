package com.winjean.service;

import com.winjean.model.entity.ModuleEntity;

import java.util.List;

public interface ModuleService {

    ModuleEntity findById(int id);

    ModuleEntity findByName(String name);

    List<ModuleEntity> findAll();

    void save(ModuleEntity entity);

    void delete(int id);

    void update(ModuleEntity entity);

}
