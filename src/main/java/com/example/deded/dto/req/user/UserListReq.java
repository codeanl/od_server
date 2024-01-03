package com.example.deded.dto.req.user;

import lombok.Data;

@Data
public class UserListReq {
    private Integer pageNum;
    private Integer pageSize;
    public UserListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
