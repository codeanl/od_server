package com.example.deded.service.impl;

import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.role.*;
import com.example.deded.mapper.RoleMapper;
import com.example.deded.mapper.UserMapper;
import com.example.deded.pojo.*;
import com.example.deded.service.RoleService;
import com.example.deded.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    //
    @Override
    public Role findByRoleName(String name) {
        Role u = roleMapper.findByRoleName(name);
        return u;
    }

    @Override
    public void addRole(RoleAddReq req) {
        Role role = new Role();
        role.setName(req.getName());
        role.setLabel(req.getLabel());
        roleMapper.roleAdd(role);
    }

    @Override
    public void updateRole(RoleUpdateReq req) {
        Role role = new Role();
        role.setId(req.getId());
        role.setName(req.getName());
        role.setLabel(req.getLabel());
        role.setStatus(req.getStatus());
        roleMapper.roleUpdate(role);
    }
    //
    @Override
    public void DeleteRoleMenuByRoleID(int roleId) {roleMapper.DeleteRoleMenuByRoleID(roleId);}
    //
    @Override
    public void roleMenuAdd(RoleMenu roleMenu) {roleMapper.roleMenuAdd(roleMenu);}

    //

    public PageInfo<Role> roleList(RoleListReq req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<Role> roleList = roleMapper.roleList(req);
        return new PageInfo<>(roleList);
    }
    //
    @Override
    public void deleteRole(List<Integer> ids) {
        roleMapper.deleteBatchIds(ids);
    }

    //
    @Override
    public  void saveOrUpdateRole(SaveOrUpdateRoleReq req){
        Role role=new Role();
        role.setId(req.getId());
        role.setName(req.getName());
        role.setLabel(req.getLabel());
        role.setStatus(req.getStatus());
        if(req.getId()>0){
            roleMapper.roleUpdate(role);
        }else{
            roleMapper.roleAdd(role);
        }
    }

    public void setRoleMenuAdd(RoleMenuAddReq req){
        for (Integer i :  req.getMenuIds()) {
            RoleMenu roleMenu=new RoleMenu();
            roleMenu.setRole_id(req.getRoleId());
            roleMenu.setMenu_id(i);
            roleMapper.roleMenuAdd(roleMenu);
        }
    }

}
