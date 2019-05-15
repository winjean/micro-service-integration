package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name="t_department")
public class EntityDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 名称
     */
    @NotBlank
    private String name;

    /**
     * 是否可用状态
     */
    @Column(columnDefinition = "bit default 0")
    private boolean status = true;

    /**
     * 上级部门编号
     */
    @Column(name = "pid",nullable = false)
    @NotNull
    private Long pid = 0l;

    /**
     * 部门拥有的角色
     */
    @ManyToMany(mappedBy = "departments")
    @JsonIgnore
    private Set<EntityRole> roles;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_user")
    private String updateUser;

    @Column(name = "update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Date updateTime;

    public @interface Update {}
}