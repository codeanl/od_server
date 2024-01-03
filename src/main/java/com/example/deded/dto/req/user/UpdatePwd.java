package com.example.deded.dto.req.user;

import lombok.Data;

@Data
public class UpdatePwd {
    private String old_pwd;
    private String password;
}
