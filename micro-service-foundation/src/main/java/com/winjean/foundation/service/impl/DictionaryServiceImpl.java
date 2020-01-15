package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.repository.DictionaryRepository;
import com.winjean.foundation.service.DictionaryService;
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
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryRepository dictionaryRepository;

    @Override
    public Dictionary save(Dictionary dictionary) {
        dictionary = dictionaryRepository.save(dictionary);
        log.info("add dictionary  success.");

        return dictionary;
    }

    @Override
    public Dictionary update(Dictionary dictionary) {
        Dictionary _dictionary = query(dictionary.getId());
        _dictionary.setName(dictionary.getName());
        _dictionary.setRemark(dictionary.getRemark());
        _dictionary.setDictionaryDetails(dictionary.getDictionaryDetails());
        _dictionary.setStatus(dictionary.isStatus());
        _dictionary.setUpdateUser("update_user");

        _dictionary = dictionaryRepository.save(_dictionary);

        log.info("update dictionary  success.");
        return _dictionary;
    }

    @Override
    public boolean delete(long id) {
        dictionaryRepository.deleteById(id);

        log.info("delete dictionary  success.");
        return true;
    }

    @Override
    public Dictionary query(long id) {
        Optional<Dictionary> user = dictionaryRepository.findById(id);
        log.info("query dictionary  success.");
        return user.get();
    }

    @Override
    public Page<Dictionary> list(JSONObject json, Pageable pageable) {
        Page<Dictionary> page = dictionaryRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(!ObjectUtils.isEmpty(json.getString("name"))){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+json.getString("name")+"%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query dictionary list success.");
        return page;
    }
}
