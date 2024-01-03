package com.example.deded.dto.req.user;

import lombok.Data;
@Data

public class UpdateProfileReq {
    private Integer id;
    private String phone;
    private String nickname;
    private String email;
}
