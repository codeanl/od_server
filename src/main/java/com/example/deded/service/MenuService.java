package com.example.deded.service;

import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Menu;

import java.util.List;

public interface MenuService {
    //根据用户名查询角色
    Menu findByMenuName(String name);
    //
    void saveOrUpdateMenu(SaveOrUpdateMenuReq req);
    //
    List<MenuListResp> menuList();

    //
    void deleteMenu( List<Integer> ids);

    List<MenuListResp> getMenuByRoleId( Integer id);

}
