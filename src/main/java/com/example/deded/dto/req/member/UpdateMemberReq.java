package com.example.deded.dto.req.member;

import lombok.Data;

@Data
public class UpdateMemberReq {
    private Integer id;
    private String phone;
    private String nickname;
    private String avatar;
    private String email;
    private String status;
}
