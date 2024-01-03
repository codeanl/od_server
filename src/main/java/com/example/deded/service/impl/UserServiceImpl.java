package com.example.deded.service.impl;

import com.example.deded.dto.req.user.SaveOrUpdateUserReq;
import com.example.deded.dto.req.user.UpdateProfileReq;
import com.example.deded.dto.req.user.UpdateUserRoleReq;
import com.example.deded.dto.resp.Menu.MenuListResp;
import com.example.deded.dto.resp.User.UserInfoResp;
import com.example.deded.mapper.UserMapper;
import com.example.deded.pojo.*;
import com.example.deded.service.UserService;
import com.example.deded.utils.Md5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.add(user);
    }

    @Override
    public void updateProfile(UpdateProfileReq updateProfileReq) {
        userMapper.updateProfile(updateProfileReq);
    }
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public void deleteUser(List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
    }

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public  void updatePwd(String password,Integer id){
        User user=new User();
        user.setId(id);
        user.setPassword(Md5Util.getMD5String(password));
        userMapper.updatePwd(user);
    }


    public PageInfo<User> userList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.listUsers();
        return new PageInfo<>(userList);
    }

    @Override
    public void UserRoleAdd(UserRole user_role) {userMapper.UserRoleAdd(user_role);}

    @Override
    public void DeleteByUserID(int user_id) {userMapper.DeleteUserRoleByUserID(user_id);}

    //
    @Override
    public UserInfoResp userInfo(Integer id){
        UserInfoResp userInfoResp=new UserInfoResp();
        //
        User u = userMapper.findByUserID(id);
        userInfoResp.setId(u.getId());
        userInfoResp.setCreated_at(u.getCreated_at());
        userInfoResp.setUpdated_at(u.getUpdated_at());
        userInfoResp.setUsername(u.getUsername());
        userInfoResp.setNickname(u.getNickname());
        userInfoResp.setPhone(u.getPhone());
        userInfoResp.setEmail(u.getEmail());
        userInfoResp.setAvatar(u.getAvatar());
        userInfoResp.setStatus(u.getStatus());
        //
        List<Role> roles=userMapper.getUserRole(id);
        userInfoResp.setRole(roles);
        //
        // 根据 userId 获取 roleIds
        List<Integer> roleId = userMapper.getRoleIdsByUserId(id);
        //
        List<Integer> menuIds = new ArrayList<>();
        for (Integer i : roleId) {
            List<Integer> ids = userMapper.getMenuIdsByRoleIds(i);
            menuIds.addAll(ids);
        }
        // 去重并获取唯一的 menuId
        Set<Integer> uniqueMenuIds = new HashSet<>(menuIds);
        // 根据唯一的 menuId 查询 menu
        List<MenuListResp> menuList = new ArrayList<>();
        for (Integer menuId : uniqueMenuIds) {
            MenuListResp menu = userMapper.getMenuByMenuId(menuId);
            if (menu != null) {
                menuList.add(menu);
            }
        }
        //
        Collections.sort(menuList, new Comparator<MenuListResp>() {
            @Override
            public int compare(MenuListResp menu1, MenuListResp menu2) {
                return Integer.compare(menu1.getSort(), menu2.getSort());
            }
        });
        //
        List<MenuListResp> ddd=buildMenuTree(menuList);
        userInfoResp.setMenu(ddd);
        return userInfoResp;
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

    public void saveOrUpdateUser(SaveOrUpdateUserReq req){
        User user=new User();
        user.setId(req.getId());
        user.setUsername(req.getUsername());
        user.setPhone(req.getPhone());
        user.setStatus(req.getStatus());
        user.setPassword( Md5Util.getMD5String("123456"));
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        if(req.getId()>0){
            userMapper.updateUser(user);
        }else {
            userMapper.addUser(user);
        }
    }


    public void updateUserRole(UpdateUserRoleReq req){
        userMapper.DeleteUserRoleByUserID(req.getId());
        for (Integer id : req.getRole_id()) {
            UserRole userRole = new UserRole();
            userRole.setUser_id(req.getId());
            userRole.setRole_id(id);
            userMapper.UserRoleAdd(userRole);
        }
    }
}
