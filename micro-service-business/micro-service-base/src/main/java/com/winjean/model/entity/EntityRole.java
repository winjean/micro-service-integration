package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winjean.common.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "t_role")
public class EntityRole extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull(groups = {Update.class})
//    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    // 数据权限类型 全部 、 本级 、 自定义
    @Column(name = "data_scope")
    private String dataScope = "本级";

    /**
     * 是否为内置
     */
    @Column(name = "built_in")
    private boolean builtIn = false;

    @Column
    private String remark;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<EntityUser> users;

    @ManyToMany
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")})
    private Set<EntityPermission> permissions;

    @ManyToMany
    @JoinTable(name = "t_role_menu", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
    private Set<EntityMenu> menus;

    @ManyToMany
    @JoinTable(name = "t_role_department", joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "department_id",referencedColumnName = "id")})
    private Set<EntityDepartment> departments;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", remark='" + remark + '\'' +
//                ", createDateTime=" + createTime +
//                '}';
//    }

    public interface Update{}
}
