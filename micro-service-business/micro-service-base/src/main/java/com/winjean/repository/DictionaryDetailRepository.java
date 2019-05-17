package com.winjean.repository;

import com.winjean.model.entity.EntityDictionaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface DictionaryDetailRepository extends JpaRepository<EntityDictionaryDetail, Long>, JpaSpecificationExecutor<EntityDictionaryDetail> {

}
