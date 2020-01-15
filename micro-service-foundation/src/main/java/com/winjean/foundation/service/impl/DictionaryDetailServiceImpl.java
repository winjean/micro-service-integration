package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.DictionaryDetail;
import com.winjean.foundation.repository.DictionaryDetailRepository;
import com.winjean.foundation.service.DictionaryDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Resource
    private DictionaryDetailRepository dictionaryDetailRepository;

    @Override
    public DictionaryDetail save(DictionaryDetail dictionaryDetail) {
        dictionaryDetail = dictionaryDetailRepository.save(dictionaryDetail);
        log.info("add dictionary detail success.");

        return dictionaryDetail;
    }

    @Override
    public DictionaryDetail update(DictionaryDetail detail) {
        DictionaryDetail _detail = query(detail.getId());
        _detail.setLabel(detail.getLabel());
        _detail.setValue(detail.getValue());
        _detail.setSort(detail.getSort());
        _detail.setStatus(detail.isStatus());
        _detail.setUpdateUser("update_user");

        _detail = dictionaryDetailRepository.save(_detail);

        log.info("update dictionary detail success.");
        return _detail;
    }

    @Override
    public boolean delete(long id) {
        dictionaryDetailRepository.deleteById(id);

        log.info("delete dictionary detail success.");
        return true;
    }

    @Override
    public DictionaryDetail query(long id) {
        Optional<DictionaryDetail> user = dictionaryDetailRepository.findById(id);
        log.info("query dictionary detail success.");
        return user.get();
    }

    @Override
    public Page<DictionaryDetail> list(JSONObject json, Pageable pageable) {
        Page<DictionaryDetail> page = dictionaryDetailRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(!ObjectUtils.isEmpty(json.getString("name"))){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+json.getString("name")+"%"));
            }

//            if(!ObjectUtils.isEmpty(json.getInteger().getStatus())){
//                list.add(criteriaBuilder.equal(root.get("enabled").as(Integer.class), department.getStatus()));
//            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query dictionary detail list success.");
        return page;
    }
}
