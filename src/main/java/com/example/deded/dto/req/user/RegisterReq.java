package com.example.deded.dto.req.user;

import lombok.Data;


@Data
public class RegisterReq {
    private String username;
    private String password;
    private String phone;
}
