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

    /**
     *  主键编号
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单层级
     */
    private int level;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String ico;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 父节点
     */
    @Column(name = "parent_id")
    private int parentId;

    /**
     * 菜单状态
     */
    private int status;

    /**
     * 菜单排序
     */
    private int sorting;
}
