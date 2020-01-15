package com.winjean.foundation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_role")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    // 数据权限类型 全部 、 本级 、 自定义
    @Column(name = "data_scope")
    private String dataScope = "本级";

    /**
     * 是否可用状态
     */
    @Column(columnDefinition = "bit default 1")
    private boolean status = true;

    /**
     * 是否为内置
     */
    @Column(name = "built_in", columnDefinition = "bit default 0 COMMENT '是否为内置'")
    private boolean builtIn = false;

    /**
     * 备注信息
     */
    private String remark;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @OrderBy("id asc")
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "r_role_permission", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    @OrderBy("id asc")
    private Set<Permission> permissions;

    @ManyToMany
    @JoinTable(name = "r_role_menu", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    @OrderBy("id asc")
    private Set<Menu> menus;

    @ManyToMany
    @JoinTable(name = "r_role_department", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "department_id",referencedColumnName = "id")})
    @OrderBy("id asc")
    private Set<Department> departments;

    @Column(name = "create_user")
    @CreatedBy
    private String createUser;

    @Column(name = "create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createTime;

    @Column(name = "update_user")
    @LastModifiedBy
    private String updateUser;

    @Column(name = "update_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updateTime;

    public @interface Update{}

}
