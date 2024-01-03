package com.example.deded.dto.req.loginLog;

import lombok.Data;

@Data
public class LoginLogListReq {
    private Integer pageNum;
    private Integer pageSize;
    public LoginLogListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
