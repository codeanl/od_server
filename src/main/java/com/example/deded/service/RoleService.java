package com.example.deded.service;

import com.example.deded.dto.req.order.OrderListReq;
import com.example.deded.dto.req.role.*;
import com.example.deded.dto.resp.order.OrderInfoResp;
import com.example.deded.pojo.Role;
import com.example.deded.pojo.RoleMenu;
import com.example.deded.pojo.User;
import com.example.deded.pojo.UserRole;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface RoleService {

    //根据用户名查询角色
    Role findByRoleName(String name);
    //
    void addRole(RoleAddReq req);
    //
    void updateRole(RoleUpdateReq req);
    //
    void roleMenuAdd(RoleMenu roleMenu);
    //
    void DeleteRoleMenuByRoleID(int roleId);

    PageInfo<Role> roleList(RoleListReq req) ;

    //
    void deleteRole( List<Integer> ids);

    void saveOrUpdateRole(SaveOrUpdateRoleReq req);

    void setRoleMenuAdd(RoleMenuAddReq req);

}
