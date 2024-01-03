package com.example.deded.dto.req.user;

import lombok.Data;

@Data
public class UpdateUserReq {
    private Integer id;
    private String phone;
    private String nickname;
    private String avatar;
    private String email;
    private String status;
    private int[] role_id;
}
