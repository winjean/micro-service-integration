package com.winjean.repository;

import com.winjean.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface UserRepository extends JpaRepository<EntityUser, Long> {

    EntityUser findModuleByName(String name);

//    @Query(value = "select * from t_module where name = ?1"
//            ,nativeQuery = true)
//    List<ModuleEntity> find_SQL_Entity(String name);
//
//    @Query(value = "select * from t_module where name = ?1",
//            countQuery = "select count(*) from t_module where name = ?1",
//            nativeQuery = true)
//    Page<ModuleEntity> find_SQL_Page(String name, Pageable page);

}
