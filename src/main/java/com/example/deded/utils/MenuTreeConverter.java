package com.example.deded.utils;

import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTreeConverter {
    public List<MenuListResp> convertToTree(List<MenuListResp> menuList) {
        List<MenuListResp> rootMenus = new ArrayList<>();

        // 构建菜单映射，以菜单ID作为键，菜单对象作为值
        Map<Integer, MenuListResp> menuMap = new HashMap<>();
        for (MenuListResp menu : menuList) {
            menuMap.put(menu.getId(), menu);
        }

        // 遍历菜单列表，将每个菜单添加到其父级菜单的子菜单列表中
        for (MenuListResp menu : menuList) {
            int parentId = menu.getPid();
            if (parentId == 0) {
                // 父级菜单，直接添加到根菜单列表
                rootMenus.add(menu);
            } else {
                // 子菜单，添加到父级菜单的子菜单列表中
                MenuListResp parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    parentMenu.addChild(menu);
                }
            }
        }
        return rootMenus;
    }
}