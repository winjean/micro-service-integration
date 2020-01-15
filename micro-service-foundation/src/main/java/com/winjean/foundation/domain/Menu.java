package com.winjean.foundation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * 是否可用状态
     */
    @Column(columnDefinition = "bit default 1")
    private boolean status = true;

    @Column(unique = true)
    @NotNull
    private Long sort;

    @Column(name = "path")
    private String path;

    private String component;

    private String icon;

    /**
     * 上级菜单ID
     */
    @Column(name = "pid",nullable = false)
    private Long pid;

    /**
     * 是否为外链 true/false
     */
    @Column(name = "i_frame")
    private Boolean iFrame;

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    @OrderBy("id asc")
    private Set<Role> roles;

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

    public @interface Update {}
}
