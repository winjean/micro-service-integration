package com.winjean.service;

import com.winjean.model.entity.ModuleEntity;
import org.springframework.data.domain.Page;

public interface ModuleService {

    ModuleEntity findById(int id);

    ModuleEntity findByName(String name);

    Page<ModuleEntity> findAll(int pageNo, int pageSize);

    void save(ModuleEntity entity);

    void delete(int id);

    void update(ModuleEntity entity);

}
