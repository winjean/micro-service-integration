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

@Data
@Entity
@Table(name = "t_permission")
public class EntityPermission extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull(groups = {Update.class})
//    private Long id;

    @NotBlank
    private String name;

    /**
     * 上级类目
     */
    @NotNull
    @Column(name = "pid",nullable = false)
    private Long pid;

    @NotBlank
    private String alias;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<EntityRole> roles;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

//    @Override
//    public String toString() {
//        return "Permission{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", pid=" + pid +
//                ", alias='" + alias + '\'' +
//                ", createTime=" + createTime +
//                '}';
//    }

    public interface Update{}
}
