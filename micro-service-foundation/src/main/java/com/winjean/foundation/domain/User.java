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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 用户名
     */
    @NotBlank
    @Column(nullable = false, columnDefinition = "varchar(100) COMMENT '用户名'")
    @Size(min = 2,max = 20,message = "用户名长度为[2-20]")
    private String name;

    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 密码加盐
     */
    @JsonIgnore
    @Column(columnDefinition = "varchar(255) COMMENT '密码加盐'")
    private String salt;

    /**
     * 出生日期
     */
    @Past(message = "出生日期必须为过去时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "date COMMENT '出生日期'")
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp birthday;

    /**
     * 联系电话
     */
    @NotBlank
    @Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号格式错误")
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    @NotBlank
    @Email
    private String email;

    /**
     * 是否可用状态
     */
    @Column(columnDefinition = "bit default 1")
    private boolean status = true;

    /**
     * 账号过期
     */
    @Column(columnDefinition = "bit default 1  COMMENT '账号过期'")
    private boolean expired = true;

    /**
     * 账号被锁定
     */
    @Column(columnDefinition = "bit default 1")
    private boolean locked = true;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "r_user_role", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    @OrderBy("id asc")
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

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

    @Column(name = "update_time", columnDefinition = "datetime COMMENT '更新日期'")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updateTime;

    public @interface Update {}
}
