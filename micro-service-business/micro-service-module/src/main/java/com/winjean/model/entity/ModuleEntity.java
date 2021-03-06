package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private boolean enable;

    /**
     * 菜单排序
     */
    private int sorting;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
