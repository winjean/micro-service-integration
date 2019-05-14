package com.winjean.model.entity;

import com.winjean.common.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Table(name="t_dictionary")
public class EntityDictionary extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    @NotNull(groups = Update.class)
//    private Long id;

    /**
     * 字典名称
     */
    @Column(name = "name",nullable = false,unique = true)
    @NotBlank
    private String name;

    /**
     * 描述
     */
    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy = "dictionary",cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<EntityDictionaryDetail> dictionaryDetails;

    public @interface Update {}
}