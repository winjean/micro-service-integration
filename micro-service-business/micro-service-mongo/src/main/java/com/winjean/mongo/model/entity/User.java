package com.winjean.mongo.model.entity;

import lombok.Data;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/18 18:54
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Data
public class User {
    private Long id;

    private String name;

    private String passWord;
}
