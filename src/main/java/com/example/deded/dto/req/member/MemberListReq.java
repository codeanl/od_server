package com.example.deded.dto.req.member;

import lombok.Data;

@Data
public class MemberListReq {
    private Integer pageNum;
    private Integer pageSize;
    private String nickname;
    private String email;
    private String phone;
    private String status;
    public MemberListReq() {
        this.pageNum = 1; // 设置默认值为1
        this.pageSize = 10; // 设置默认值为10
    }
}
