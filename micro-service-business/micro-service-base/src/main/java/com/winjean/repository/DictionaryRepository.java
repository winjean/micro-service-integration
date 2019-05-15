package com.winjean.repository;

import com.winjean.model.entity.EntityDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface DictionaryRepository extends JpaRepository<EntityDictionary, Long> {

}
