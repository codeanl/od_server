package com.example.deded.dto.req.role;

import lombok.Data;

@Data
public class RoleListReq {
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private String label;
    private String status;
    public RoleListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
