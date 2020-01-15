package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Menu;
import com.winjean.repository.MenuRepository;
import com.winjean.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Page<Menu> list(JSONObject json) {
        int page = json.getInteger("page") == null ? 1 : json.getInteger("page");
        int size = json.getInteger("size") == null ? 10 : json.getInteger("size");

        Menu menu = new Menu();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", match -> match.exact());
        Example example = Example.of(menu, matcher);

        PageRequest pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<Menu> list = menuRepository.findAll(example, pageable);

        log.info("query menu list success.");
        return list;
    }
}
