package com.winjean.foundation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.domain.Menu;
import com.winjean.foundation.repository.MenuRepository;
import com.winjean.foundation.service.MenuService;
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
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public Menu save(Menu menu) {
        menu = menuRepository.save(menu);
        log.info("add menu success.");

        return menu;
    }

    @Override
    public Menu update(Menu menu) {
        Menu _menu = query(menu.getId());
        _menu.setName(menu.getName());
        _menu.setSort(menu.getSort());
        _menu.setPath(menu.getPath());
        _menu.setComponent(menu.getComponent());
        _menu.setIcon(menu.getIcon());
        _menu.setPid(menu.getPid());
        _menu.setIFrame(menu.getIFrame());
        _menu.setRoles(menu.getRoles());
        _menu.setStatus(menu.isStatus());
        _menu.setUpdateUser("update_user");

        _menu = menuRepository.save(_menu);

        log.info("update menu success.");
        return _menu;
    }

    @Override
    public boolean delete(long id) {
        menuRepository.deleteById(id);

        log.info("delete menu success.");
        return true;
    }

    @Override
    public Menu query(long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        log.info("query menu success.");
        return menu.get();
    }

    @Override
    public Page<Menu> list(JSONObject json, Pageable pageable) {
        Page<Menu> page = menuRepository.findAll((root, query, criteriaBuilder) ->{
            List<Predicate> list = new ArrayList<>();

            if(!ObjectUtils.isEmpty(json.getString("name"))){
                list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+json.getString("name")+"%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);

        log.info("query menu list success.");
        return page;
    }
}
