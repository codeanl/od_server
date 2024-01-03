package com.example.deded.dto.req.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuAddReq {
    private Integer roleId;
    private List<Integer> menuIds;
}
