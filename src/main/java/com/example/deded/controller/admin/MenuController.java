package com.example.deded.controller.admin;


import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.req.role.RoleAddReq;
import com.example.deded.dto.req.role.RoleUpdateReq;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.pojo.Menu;
import com.example.deded.pojo.Result;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.RoleMenu;
import com.example.deded.service.MenuService;
import com.example.deded.service.RoleService;
import com.example.deded.utils.MenuTreeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //添加||更新菜单
    @PostMapping("/menu")
    public Result saveOrUpdateMenu(@RequestBody SaveOrUpdateMenuReq req) {
//        Menu u = menuService.findByMenuName(req.getName());
//        if (u == null) {
//            menuService.saveOrUpdateMenu(req);
//            return Result.success();
//        }else{
//            return Result.error("该角色名已被占用");
//        }
        menuService.saveOrUpdateMenu(req);
        return Result.success();
    }

    //菜单列表
    @GetMapping("/list")
    public Result menuTreeList() {
        List<MenuListResp> menuListResp =menuService.menuList();
        List<MenuListResp> ddd=buildMenuTree(menuListResp);
        return Result.success(ddd);
    }

    //菜单列表
    @GetMapping("/llist")
    public Result menuList() {
        List<MenuListResp> menuListResp =menuService.menuList();
        return Result.success(menuListResp);
    }

    // 删除菜单
    @PostMapping("/del")
    public Result deleteMenu(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        menuService.deleteMenu(ids);
        return Result.success();
    }

    public List<MenuListResp> buildMenuTree(List<MenuListResp> menuList) {
            // 使用一个 Map 来存储菜单项，以菜单项的 ID 作为键
            Map<Integer, MenuListResp> menuMap = new HashMap<>();
            // 遍历菜单列表，将菜单项放入 Map 中
            for (MenuListResp menu : menuList) {
                menuMap.put(menu.getId(), menu);
            }
            List<MenuListResp> rootMenus = new ArrayList<>();
            // 遍历菜单列表，构建树状结构
            for (MenuListResp menu : menuList) {
                int parentId = menu.getPid();
                if (parentId == 0) {
                    // 如果父菜单 ID 为 0，则将该菜单项作为根菜单
                    rootMenus.add(menu);
                } else {
                    // 如果父菜单 ID 不为 0，则将菜单项添加到父菜单的 children 列表中
                    MenuListResp parentMenu = menuMap.get(parentId);
                    if (parentMenu != null) {
                        parentMenu.addChild(menu);
                    }
                }
            }

            return rootMenus;
        }

    @GetMapping("/getMenuByRoleId")
    public Result getMenuByRoleId(Integer id) {
        List<MenuListResp> menuListResp=menuService.getMenuByRoleId(id);
        List<MenuListResp> ddd=buildMenuTree(menuListResp);
        return Result.success(ddd);
    }
}
