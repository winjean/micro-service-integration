package com.winjean.repository;

import com.winjean.model.entity.ModuleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ModuleRepository extends PagingAndSortingRepository<ModuleEntity, Integer> {

    ModuleEntity findModuleByName(String name);

}
