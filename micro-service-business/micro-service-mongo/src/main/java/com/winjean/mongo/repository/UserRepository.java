package com.winjean.mongo.repository;

import com.winjean.mongo.model.entity.User;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/18 19:01
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface UserRepository {

    void saveUser(User user);

    User findUserByUserName(String userName);

    long updateUser(User user);

    void deleteUserById(Long id);
}
