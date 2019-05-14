package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
public class EntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = EntityDictionaryDetail.Update.class)
    private Long id;

    @Column(name = "user_name")
    private String name;

    private String password;

    /**
     * 密码加盐
     */
    private String salt;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;

    private String telephone;

    private String sex;

    private String email;

    private boolean status = true;

    @ManyToMany
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<EntityRole> roles;

    @OneToOne
    @JoinColumn(name = "department_id")
    private EntityDepartment department;

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
    @CreationTimestamp
    private Date updateTime;
}
