package com.winjean.repository;

import com.winjean.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface UserRepository extends JpaRepository<EntityUser, Long>, JpaSpecificationExecutor<EntityUser> {

    EntityUser findByName(String name);

    EntityUser findByEmail(String email);

    EntityUser findBytelephone(String telephone);
}
