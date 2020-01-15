package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.repository.DictionaryRepository;
import com.winjean.foundation.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Page<Dictionary> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        Dictionary dictionary = new Dictionary();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(dictionary, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Dictionary> list = dictionaryRepository.findAll(example, pageable);

        log.info("query dictionary  list success.");
        return list;
    }
}
