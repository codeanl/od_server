package com.example.deded.service.impl;

import com.example.deded.dto.req.menu.SaveOrUpdateMenuReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.mapper.MenuMapper;
import com.example.deded.mapper.UserMapper;
import com.example.deded.pojo.LoginLog;
import com.example.deded.pojo.MemberLoginLog;
import com.example.deded.pojo.Menu;
import com.example.deded.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;
    //
    @Override
    public Menu findByMenuName(String name) {
        Menu u = menuMapper.findByMenuName(name);
        return u;
    }
    //
    @Override
    public void saveOrUpdateMenu(SaveOrUpdateMenuReq req) {
        Menu menu = new Menu();
        menu.setId(req.getId());
        menu.setName(req.getName());
        menu.setIcon(req.getIcon());
        menu.setPath(req.getPath());
        menu.setPid(req.getPid());
        menu.setComponent(req.getComponent());
        menu.setSort(req.getSort());
        menu.setStatus(req.getStatus());
        if (req.getId()>0){
            menuMapper.menuUpdate(menu);
        }else{
            menuMapper.menuAdd(menu);
        }
    }

    //
    public List<MenuListResp> menuList() {
        return menuMapper.menuList();
    }

    @Override
    public void deleteMenu(List<Integer> ids) {
        menuMapper.deleteBatchIds(ids);
    }


    public List<MenuListResp> getMenuByRoleId( Integer id){
        List<Integer> menuIds = new ArrayList<>();
        List<Integer> ids = userMapper.getMenuIdsByRoleIds(id);
        menuIds.addAll(ids);
        // 根据唯一的 menuId 查询 menu
        List<MenuListResp> menuList = new ArrayList<>();
        for (Integer menuId : menuIds) {
            MenuListResp menu = userMapper.getMenuByMenuId(menuId);
            if (menu != null) {
                menuList.add(menu);
            }
        }
        return menuList;
    }




}
