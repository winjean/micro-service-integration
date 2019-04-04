package com.winjean.service.impl;

import com.winjean.model.entity.ModuleEntity;
import com.winjean.repository.ModuleRepository;
import com.winjean.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@CacheConfig // 配置了spring内置缓存来缓存常用查询数据
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    @Transactional
    public void save(ModuleEntity entity) {
        ModuleEntity module = moduleRepository.save(entity);
        log.info("module saved id = {}",module.getId());
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    @CacheEvict(cacheNames="ModuleCache",allEntries = true)
    //delete() 方法用于删除指定数据，由于delete方法删除的是实体，如果传递的参数不是实体那么需要先将待删除实体查询出来再进行删除
    public void delete(int id) {
        Optional<ModuleEntity> optional =  moduleRepository.findById(id);
        if (optional.isPresent()){
            ModuleEntity module =  optional.get();
            moduleRepository.delete(module);
            log.info("module deleted id = {}",id);
        }else{
            log.info("no module exist, id = {}",id);
        }
    }

    @Override
    @Transactional
    @Modifying //定义事务为修改
    @CacheEvict(cacheNames="ModuleCache",allEntries = true)
    //清除所有缓存使新增的数据能够被查到
    //update() 方法用于更新实体信息，spring data jpa对于数据的更新还有query update这种基于类sql的方式，这里我们不采用这种方式，我们采用的方式更简单update方法参数中包含了待更新实体的唯一标识，通过标识将原本的实体查询出来再将需要修改的属性值传递到查询中的实体就完成了数据库数据的更新十分简单
    public void update(ModuleEntity entity) {
        int id = entity.getId();
        Optional<ModuleEntity> optional =  moduleRepository.findById(id);
        if(optional.isPresent()){
            ModuleEntity module = moduleRepository.findById(id).get();
            module.setName(entity.getName());
            log.info("module updated id = {},name = {}", id, module.getName());
        }else{
            log.info("no module exist, id = {}", id);
        }
    }

    @Override
    @Cacheable(value = "ModuleCache", key = "#id")
    public ModuleEntity findById(int id) {
        Optional<ModuleEntity> optional =  moduleRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    @Cacheable(value = "ModuleCache", key = "#name", condition = "#name != null &&  #name != \"\"")
    public ModuleEntity findByName(String name) {
        return moduleRepository.findModuleByName(name);
    }

    @Override
    public Page<ModuleEntity> findAll(int pageNo, int pageSize) {
        PageRequest page= PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("id")));
        return moduleRepository.findAll(page);
    }
}
