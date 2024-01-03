package com.example.deded.dto.req.role;

import lombok.Data;

@Data
public class SaveOrUpdateRoleReq {
    private Integer id;
    private String name;
    private String label;
    private String status;
}
