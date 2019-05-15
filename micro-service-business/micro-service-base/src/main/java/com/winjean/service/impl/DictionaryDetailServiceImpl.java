package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDictionaryDetail;
import com.winjean.model.entity.EntityUser;
import com.winjean.repository.DictionaryDetailRepository;
import com.winjean.service.DictionaryDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class DictionaryDetailServiceImpl implements DictionaryDetailService {

    @Resource
    private DictionaryDetailRepository dictionaryDetailRepository;

    @Override
    public EntityDictionaryDetail insert(EntityDictionaryDetail dictionaryDetail) {
        dictionaryDetail = dictionaryDetailRepository.save(dictionaryDetail);
        log.info("add dictionary detail success.");

        return dictionaryDetail;
    }

    @Override
    public EntityDictionaryDetail update(EntityDictionaryDetail dictionaryDetail) {
        EntityDictionaryDetail _dictionaryDetail = query(dictionaryDetail.getId());
//        _user.setName(user.getName());
//        _user.setBirthday(user.getBirthday());
//        _user.setTelephone(user.getTelephone());
//        _user.setSex(user.getSex());
//        _user.setEmail(user.getEmail());
//        _user.setEmail(user.getEmail());
//        _user.setDepartment(user.getDepartment());
//        _user.setRoles(user.getRoles());

        _dictionaryDetail = dictionaryDetailRepository.save(_dictionaryDetail);

        log.info("update dictionary detail success.");
        return _dictionaryDetail;
    }

    @Override
    public boolean delete(long id) {
        dictionaryDetailRepository.deleteById(id);

        log.info("delete dictionary detail success.");
        return true;
    }

    @Override
    public EntityDictionaryDetail query(long id) {
        Optional<EntityDictionaryDetail> user = dictionaryDetailRepository.findById(id);
        log.info("query dictionary detail success.");
        return user.get();
    }

    @Override
    public Page<EntityDictionaryDetail> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        EntityUser user = new EntityUser();
        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("status")
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(user, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<EntityDictionaryDetail> list = dictionaryDetailRepository.findAll(example, pageable);

        log.info("query dictionary detail list success.");
        return list;
    }
}
