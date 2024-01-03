package com.example.deded.dto.req.member;

import lombok.Data;

@Data
public class MemberLoginReq {
    private String phone;
    private String password;
}

