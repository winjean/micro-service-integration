package com.winjean.foundation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="t_dictionary_detail")
public class DictionaryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Update.class)
    private Long id;

    /**
     * 字典标签
     */
    @Column(name = "label",nullable = false)
    private String label;

    /**
     * 字典值
     */
    @Column(name = "value",nullable = false)
    private String value;

    /**
     * 是否可用状态
     */
    @Column(columnDefinition = "bit default 1")
    private boolean status = true;

    /**
     * 排序
     */
    @Column(name = "sort")
    private int sort;

    /**
     * 字典id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id")
    @JsonIgnoreProperties("dictionaryDetails")
    private Dictionary dictionary;

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