package com.winjean.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/4 14:24
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Entity
@Table(name = "t_module")
@Data
public class ModuleEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;
}
