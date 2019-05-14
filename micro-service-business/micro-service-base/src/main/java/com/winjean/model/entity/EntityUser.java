package com.winjean.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
public class EntityUser extends BaseEntity {

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

    private byte status = 1;

    @ManyToMany
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<EntityRole> roles;
}
