package com.example.deded.controller.admin;


import com.example.deded.dto.req.Banner.BannerListReq;
import com.example.deded.dto.req.role.*;
import com.example.deded.dto.req.user.DeleteReq;
import com.example.deded.dto.req.user.UserListReq;
import com.example.deded.pojo.*;
import com.example.deded.service.MenuService;
import com.example.deded.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    //角色列表
    @GetMapping("/list")
    public Result roleList(@ModelAttribute RoleListReq req) {
        return Result.success(roleService.roleList(req));
    }
    // 删除角色
    @PostMapping("/del")
    public Result deleteRole(@RequestBody DeleteReq req) {
        List<Integer> ids = req.getIds();
        roleService.deleteRole(ids);
        return Result.success();
    }

    //
    @PostMapping("/role")
    public Result saveOrUpdateRole(@RequestBody SaveOrUpdateRoleReq req) {
            roleService.saveOrUpdateRole(req);
            return Result.success();
    }
    //
    @PostMapping("/roleMenuAdd")
    public Result roleMenuAdd(@RequestBody RoleMenuAddReq req) {
        roleService.setRoleMenuAdd(req);
        return Result.success();
    }

}
