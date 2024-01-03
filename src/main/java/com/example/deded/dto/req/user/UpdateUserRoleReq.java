package com.example.deded.dto.req.user;

import lombok.Data;

import java.util.List;

@Data

public class UpdateUserRoleReq {
    private Integer id;
    private List<Integer> role_id;
}
