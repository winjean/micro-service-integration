package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.winjean.common.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@Table(name="t_department")
public class EntityDepartment extends BaseEntity {

    /**
     * ID
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @NotNull(groups = Update.class)
//    private Long id;

    /**
     * 名称
     */
    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

    @NotNull
    private Boolean enabled;

    /**
     * 上级部门
     */
    @Column(name = "pid",nullable = false)
    @NotNull
    private Long pid;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @ManyToMany(mappedBy = "departments")
    @JsonIgnore
    private Set<EntityRole> roles;

    @OneToOne
    @JoinColumn(name = "department_id")
    private EntityDepartment departmentId;

    public @interface Update {}
}