package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="t_dictionary_detail")
public class EntityDictionaryDetail extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @NotNull(groups = Update.class)
//    private Long id;

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
     * 排序
     */
    @Column(name = "sort")
    private String sort = "999";

    /**
     * 字典id
     */
    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private EntityDictionary dictionary;

    public @interface Update {}
}