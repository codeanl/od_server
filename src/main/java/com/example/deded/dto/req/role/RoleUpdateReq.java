package com.example.deded.dto.req.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleUpdateReq {
    private Integer id;
    private String name;
    private String label;
    private String status;
    private List<Integer> menu_id;
}
